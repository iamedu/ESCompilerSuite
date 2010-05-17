/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablalr1;

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
    private TablaLR1 tablaLR1;

    public Main() {
        mainWindow = new Ui_MainWindow();
        mainWindow.setupUi(this);

        mainWindow.toolButton.clicked.connect(this, "generarTabla()");
        mainWindow.toolButton_2.clicked.connect(this, "analizar()");

        mainWindow.actionNueva_Gramatica.triggered.connect(this, "nuevaGramatica()");
        mainWindow.actionGuardar_Gramatica.triggered.connect(this, "guardarGramatica()");
        mainWindow.actionCargar_Gramatica.triggered.connect(this, "cargarGramatica()");
        mainWindow.actionSalir.triggered.connect(this, "salir()");
        mainWindow.actionTabla_LR1.triggered.connect(this, "acerca()");

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

        while (mainWindow.tableWidget.columnCount() > 0) {
            mainWindow.tableWidget.removeColumn(0);
        }

        while (mainWindow.tableWidget.rowCount() > 0) {
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

            while ((line = br.readLine()) != null) {
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
        analisis.setTabla(tablaLR1);
        analisis.analizar(mainWindow.plainTextEdit_2.toPlainText().trim());
        analisis.show();
    }

    public void generarTabla() {
        QTableWidgetItem colItem;
        String reglasGramatica;
        String r;

        int i;
        int j;

        try {
            reglasGramatica = mainWindow.plainTextEdit.toPlainText().trim() + "\n";
            tablaLR1 = new TablaLR1(reglasGramatica);
        } catch (Exception ex) {
            QMessageBox.warning(this, "Error", "Revisar la gramatica");
            ex.printStackTrace();
            return;
        }

        mainWindow.tableWidget.clear();
        mainWindow.tableWidget.setColumnCount(tablaLR1.getHeaders().length);


        i = 0;

        for (String s : tablaLR1.getHeaders()) {
            colItem = new QTableWidgetItem();
            colItem.setText(s);
            mainWindow.tableWidget.setHorizontalHeaderItem(i++, colItem);
        }


        mainWindow.tableWidget.setRowCount(tablaLR1.rowCount());

        for (i = 0; i < tablaLR1.rowCount(); i++) {
            colItem = new QTableWidgetItem();
            colItem.setText(String.valueOf(i));
            mainWindow.tableWidget.setVerticalHeaderItem(i, colItem);
        }

        i = 0;
        for (String s : tablaLR1.getHeaders()) {
            for (j = 0; j < tablaLR1.rowCount(); j++) {
                if ((r = tablaLR1.get(String.valueOf(j), s)) != null) {
                    colItem = new QTableWidgetItem();
                    colItem.setText(r);
                    mainWindow.tableWidget.setItem(j, i, colItem);
                }
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
