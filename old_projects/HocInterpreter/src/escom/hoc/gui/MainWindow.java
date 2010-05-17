/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.gui;

import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.gui.QFileDialog;
import com.trolltech.qt.gui.QFileDialog.Filter;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QTableWidgetItem;
import escom.hoc.exceptions.HocException;
import escom.hoc.interpreter.HocInterpreter;
import escom.hoc.symbol.Datum;
import escom.hoc.symbol.Variable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iamedu
 */
public class MainWindow extends QMainWindow {

    private Ui_Hoc5MainWindow widgets;
    private HocInterpreter interpreter;
    private GuiPCListener listener;
    private boolean started;
    private Thread t;

    public MainWindow() {
        widgets = new Ui_Hoc5MainWindow();
        interpreter = new HocInterpreter();
        widgets.setupUi(this);
        setupActions();
        initTables();

        listener = new GuiPCListener(interpreter.getPc(), this);
        interpreter.getPc().setListener(listener);
        interpreter.setPrinter(new TextBrowserPrinter(widgets.salidaTextBrowser));
        started = false;
    }

    @Override
    protected void disposed() {
        super.disposed();

        if(t != null) {
            t.stop();
        }

    }

    

    public Ui_Hoc5MainWindow getWidgets() {
        return widgets;
    }

    public void abrir() {

        String filename = QFileDialog.getOpenFileName(this,
                "Abrir programa HOC", System.getProperty("user.dir"),
                new Filter("*.hoc"));
        FileInputStream fis;
        BufferedReader br = null;
        File fin = new File(filename);
        String line;

        if (!fin.exists()) {
            return;
        }

        try {
            fis = new FileInputStream(fin);
            br = new BufferedReader(new InputStreamReader(fis));
            widgets.codigoTextEdit.clear();

            while ((line = br.readLine()) != null) {
                widgets.codigoTextEdit.setPlainText(widgets.codigoTextEdit.toPlainText() + line + "\n");
            }
        } catch (FileNotFoundException ex) {
            QMessageBox.warning(this, "Error", ex.getMessage());
        } catch (IOException ex) {
            QMessageBox.warning(this, "Error", ex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                QMessageBox.warning(this, "Error", ex.getMessage());
            }
        }

    }

    public void nuevo() {
        widgets.codigoTextEdit.clear();
        interpreter.getPc().setNum(0);
        interpreter.getProgram().clear();
        interpreter.getStack().clear();
        interpreter.getSymbols().clear();
        started = false;
        actualizarEstado();
    }

    public void guardar() {
        String filename = QFileDialog.getSaveFileName(this, "Guardar programa HOC", System.getProperty("user.dir"), new Filter("*.hoc"));
        FileOutputStream fos = null;
        BufferedWriter bw;


        if (filename == null || "".equals(filename)) {
            return;
        }

        if (!filename.endsWith(".hoc")) {
            filename += ".hoc";
        }
        File fout = new File(filename);


        try {
            fos = new FileOutputStream(fout);
            bw = new BufferedWriter(new FileWriter(fout));
            bw.write(widgets.codigoTextEdit.toPlainText().trim());
            bw.flush();

        } catch (FileNotFoundException ex) {
            QMessageBox.warning(this, "Error", ex.getMessage());
        } catch (IOException ex) {
            QMessageBox.warning(this, "Error", ex.getMessage());
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                QMessageBox.warning(this, "Error", ex.getMessage());
            }
        }
    }

    public void actualizarEstado() {
        QTableWidgetItem item;


        //Actualizar tabla de codigo
        int i = 0;
        int j;

        widgets.intermedioTableWidget.clearContents();
        widgets.intermedioTableWidget.setRowCount(interpreter.getProgram().size());

        for (String instr : interpreter.getProgram()) {
            item = new QTableWidgetItem();
            item.setFlags(ItemFlag.ItemIsSelectable, ItemFlag.ItemIsEnabled);
            item.setText(instr);

            widgets.intermedioTableWidget.setItem(i, 0, item);

            item = new QTableWidgetItem();
            item.setText(String.valueOf(i));
            widgets.intermedioTableWidget.setVerticalHeaderItem(i++, item);

        }

        //Actualizar tabla de pila

        widgets.pilaTableWidget.clearContents();
        widgets.pilaTableWidget.setRowCount(interpreter.getStack().size());

        j = interpreter.getStack().size();
        i = 0;

        for (j -= 1; j >= 0; j--) {
            Datum dato = interpreter.getStack().get(j);
            item = new QTableWidgetItem();
            item.setFlags(ItemFlag.ItemIsSelectable, ItemFlag.ItemIsEnabled);
            item.setText(dato.toString());

            widgets.pilaTableWidget.setItem(i++, 0, item);
        }

        widgets.datosTableWidget.clearContents();
        widgets.datosTableWidget.setRowCount(interpreter.getSymbols().size());

        i = 0;
        for (Datum dato : interpreter.getSymbols().values()) {
            Variable v = (Variable) dato;
            item = new QTableWidgetItem();
            item.setFlags(ItemFlag.ItemIsSelectable, ItemFlag.ItemIsEnabled);
            item.setText(v.getName());

            widgets.datosTableWidget.setItem(i, 0, item);

            item = new QTableWidgetItem();
            item.setFlags(ItemFlag.ItemIsSelectable, ItemFlag.ItemIsEnabled);

            if (v.isUnset()) {
                item.setText("Unset");
            } else {
                try {
                    item.setText(String.valueOf(v.getValue()));
                } catch (HocException ex) {
                    //Revise si era unset, nunca vamos a llegar aqui
                }
            }

            widgets.datosTableWidget.setItem(i++, 1, item);

            widgets.intermedioTableWidget.repaint();

        }
    }

    public void generar() {
        interpreter.getProgram().clear();
        interpreter.getStack().clear();

        try {
            if (widgets.codigoTextEdit.toPlainText().length() > 0) {
                interpreter.load(widgets.codigoTextEdit.toPlainText());

            }

        } catch (Exception ex) {
            QMessageBox.warning(this, "Error", "Revise su código: " + ex.getMessage());
        }
    }

    public void completo() {
        if (t != null) {
            t.stop();
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        listener.setPausado(false);
        interpreter.getPc().setListener(null);
        interpreter.getPc().setNum(0);
        generar();

        try {
            interpreter.executeAll();
            actualizarEstado();
        } catch (Exception e) {
            QMessageBox.information(this, "Error", e.getMessage());
        }

    }

    public void pausado() {
        if (t == null || !t.isAlive()) {
            System.out.println("HOLA");
            listener.setPausado(true);
            interpreter.getProgram().clear();
            interpreter.getPc().setListener(listener);
            generar();


            Runnable r = new Runnable() {

                public void run() {
                    try {
                        interpreter.getPc().setNum(0);
                        interpreter.executeAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                        QMessageBox.information(null, "Error", e.getMessage());
                    }
                }
            };
            t = new Thread(r);

            listener.setContinuar(false);
            started = true;

            actualizarEstado();

            t.start();
            return;
        }

        listener.setContinuar(true);


        repaint();



    }

    private void setupActions() {
        widgets.completoPushButton.clicked.connect(this, "completo()");
        widgets.pausadoPushButton.clicked.connect(this, "pausado()");
        widgets.actionAbrir.triggered.connect(this, "abrir()");
        widgets.actionGuardar.triggered.connect(this, "guardar()");
        widgets.actionNuevo.triggered.connect(this, "nuevo()");
        
    }

    private void initTables() {
        //Inicializacion tabla de codigo
        widgets.intermedioTableWidget.setColumnCount(1);
        QTableWidgetItem item;
        item = new QTableWidgetItem();
        item.setText("Instrucción");
        widgets.intermedioTableWidget.setHorizontalHeaderItem(0, item);

        //Inicializacion tabla de pila
        widgets.pilaTableWidget.setColumnCount(1);
        item = new QTableWidgetItem();
        item.setText("Dato");
        widgets.pilaTableWidget.setHorizontalHeaderItem(0, item);

        //Inicializacion tabla de datos
        widgets.datosTableWidget.setColumnCount(2);
        item = new QTableWidgetItem();
        item.setText("Nombre");
        widgets.datosTableWidget.setHorizontalHeaderItem(0, item);
        item = new QTableWidgetItem();
        item.setText("Valor");
        widgets.datosTableWidget.setHorizontalHeaderItem(1, item);

    }
}
