/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.compiladores;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMessageBox;
import escom.hoc.gui.MainWindow;

/**
 *
 * @author iamedu
 */
public class Main extends QMainWindow {

    private Ui_MainWindow mainWindow;
    private automata.Main automataMain;
    private tablalr0.Main lr0Main;
    private tablalr1.Main lr1Main;
    private tablall1.Main ll1Main;
    private tablalalr.Main lalrMain;
    private escom.hoc.gui.MainWindow hocMain;

    public Main() {
        mainWindow = new Ui_MainWindow();
        mainWindow.setupUi(this);
        mainWindow.treeWidget.doubleClicked.connect(this, "doubleClick(QModelIndex)");
        mainWindow.actionCerrar.triggered.connect(QApplication.instance(), "exit()");
        mainWindow.actionAcerca_de.triggered.connect(this, "acerca()");
    }

    public void acerca() {
        String acercaText =
                "<html>" +
                "<body>" +
                "Este programa fue desarrollado por: " +
                "<ul>" +
                "<li>Eduardo Díaz Real</li>" +
                "<li>Jessica Loyola Diaz</li>" +
                "</ul>" +
                "Escuela Superior de Cómputo<br />" +
                "Grupo: 6CM1<br />" +
                "Profesor: Andrés Ortigoza Campos" +
                "</body>" +
                "</html>";
        QMessageBox.about(this, "Compiladores ESCOM", acercaText);
    }

    public void doubleClick(QModelIndex index) {
        String data = index.data().toString();
        if (data.contains("léxico")) {
            if (automataMain != null) {
                automataMain.close();
            }
            automataMain = new automata.Main();
            automataMain.show();
        } else if (data.contains("LL(1)")) {
            if (ll1Main != null) {
                ll1Main.close();
            }
            ll1Main = new tablall1.Main();
            ll1Main.show();
        } else if (data.contains("LR(0)")) {
            if (lr0Main != null) {
                lr0Main.close();
            }
            lr0Main = new tablalr0.Main();
            lr0Main.show();
        } else if (data.contains("LR(1)")) {
            if (lr1Main != null) {
                lr1Main.close();
            }
            lr1Main = new tablalr1.Main();
            lr1Main.show();
        } else if (data.contains("LALR")) {
            if (lalrMain != null) {
                lalrMain.close();
            }
            lalrMain = new tablalalr.Main();
            lalrMain.show();
        } else if (data.contains("Traduccion")) {
            if (hocMain != null) {
                hocMain.close();
            }
            hocMain = new MainWindow();
            hocMain.show();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QApplication.initialize(args);

        Main main = new Main();
        main.setVisible(true);

        QApplication.exec();
    }
}
