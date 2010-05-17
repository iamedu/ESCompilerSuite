/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablall1;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QFileDialog;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QTableWidgetItem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author iamedu
 */
public class Main extends QMainWindow {

    private Ui_MainWindow mainWindow;
    private TablaLL1 tablaLL1;

    public Main() {
        mainWindow = new Ui_MainWindow();
        mainWindow.setupUi(this);

        mainWindow.toolButton.clicked.connect(this, "generarTabla()");
        mainWindow.toolButton_2.clicked.connect(this, "analizar()");

        mainWindow.actionNueva_Gramatica.triggered.connect(this, "nuevaGramatica()");
        mainWindow.actionGuardar_Gramatica.triggered.connect(this, "guardarGramatica()");
        mainWindow.actionCargar_Gramatica.triggered.connect(this, "cargarGramatica()");
        mainWindow.actionSalir.triggered.connect(this, "salir()");
        mainWindow.actionTabla_LL1.triggered.connect(this, "acerca()");

    }

    public void acerca() {
        Acerca a = new Acerca();
        a.show();
    }

    public void salir() {
        QApplication.exit();
    }

    public void nuevaGramatica() {

        mainWindow.plainTextEdit.clear();
        mainWindow.plainTextEdit_2.clear();

        mainWindow.tableWidget.clearContents();
        mainWindow.tableWidget.clear();

        while(mainWindow.tableWidget.columnCount() > 0) {
            mainWindow.tableWidget.removeColumn(0);
        }

        while(mainWindow.tableWidget.rowCount() > 0) {
            mainWindow.tableWidget.removeRow(0);
        }

    }

    public void guardarGramatica() {
        String gramatica;
        FileWriter fw;
        BufferedWriter bw;
        gramatica = QFileDialog.getSaveFileName(this);
        try {
            fw = new FileWriter(gramatica);
            bw = new BufferedWriter(fw);
            bw.write(mainWindow.plainTextEdit.toPlainText().trim());
            bw.flush();
        } catch (IOException ex) {
            QMessageBox.information(this, "Error", "No se ha podido guardar");
        }
    }

    public void cargarGramatica() {
        String gramatica;
        String result = "";
        String line;
        FileReader fr;
        BufferedReader br;
        gramatica = QFileDialog.getOpenFileName(this);
        try {
            fr = new FileReader(gramatica);
            br = new BufferedReader(fr);

            while((line = br.readLine()) != null) {
                result += line + "\n";
            }

            mainWindow.plainTextEdit.setPlainText(result);

            generarTabla();

        } catch (IOException ex) {
            QMessageBox.information(this, "Error", "No se ha podido cargar");
        }
    }

    public void analizar() {
        Analisis analisis = new Analisis();
        analisis.setTabla(tablaLL1);
        analisis.analizar(mainWindow.plainTextEdit_2.toPlainText().trim());
        analisis.show();

        /*
        if (tablaLL1.analizar(mainWindow.plainTextEdit_2.toPlainText().trim())) {
            QMessageBox.information(this, "Resultado", "Tu cadena es correcta");
        } else {
            QMessageBox.information(this, "Resultado", "Tu cadena es incorrecta");
        }
         */
    }

    public void generarTabla() {
        Regla r;
        QTableWidgetItem colItem;
        String reglasGramatica;

        int i;
        int j;

        try {
            reglasGramatica = mainWindow.plainTextEdit.toPlainText().trim() + "\n";
            tablaLL1 = new TablaLL1(reglasGramatica);
        } catch (Exception ex) {
            QMessageBox.warning(this, "Error", "Revisar la gramatica");
            ex.printStackTrace();
            return;
        }

        mainWindow.tableWidget.clear();
        mainWindow.tableWidget.setColumnCount(tablaLL1.getGramatica().getTerminales().size() + 1);


        i = 0;

        for (String s : tablaLL1.getGramatica().getTerminales()) {
            colItem = new QTableWidgetItem();
            colItem.setText(s);
            mainWindow.tableWidget.setHorizontalHeaderItem(i++, colItem);
        }

        colItem = new QTableWidgetItem();
        colItem.setText("$");
        mainWindow.tableWidget.setHorizontalHeaderItem(i, colItem);

        mainWindow.tableWidget.setRowCount(tablaLL1.getGramatica().getNoTerminales().size());
        i = 0;

        for (String s : tablaLL1.getGramatica().getNoTerminales()) {
            colItem = new QTableWidgetItem();
            colItem.setText(s);
            mainWindow.tableWidget.setVerticalHeaderItem(i++, colItem);
        }


        i = 0;
        for (String f : tablaLL1.getGramatica().getNoTerminales()) {
            j = 0;
            for (String c : tablaLL1.getGramatica().getTerminales()) {
                if ((r = tablaLL1.get(f, c)) != null) {
                    colItem = new QTableWidgetItem();
                    colItem.setText(Gramatica.formatRegla(r.getValor()));
                    mainWindow.tableWidget.setItem(i, j, colItem);
                //form.tableWidget.set
                }
                j++;
            }
            if ((r = tablaLL1.get(f, "$")) != null) {
                colItem = new QTableWidgetItem();
                colItem.setText(Gramatica.formatRegla(r.getValor()));
                mainWindow.tableWidget.setItem(i, j, colItem);
            }
            i++;
        }


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m;
        QApplication.initialize(args);
        m = new Main();

        m.show();

        QApplication.exec();
    }
}
