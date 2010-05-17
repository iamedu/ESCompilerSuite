/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.gui;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import escom.hoc.IntegerPtr;
import escom.hoc.interpreter.PCListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iamedu
 */
public class GuiPCListener extends PCListener {

    private MainWindow main;
    private boolean pausado;
    private boolean continuar;

    public GuiPCListener(IntegerPtr integerPtr, MainWindow widgets) {
        super(integerPtr);
        this.main = widgets;
        pausado = false;
    }

    @Override
    @SuppressWarnings("empty-statement")
    protected void localChanged(final IntegerPtr ptr) {
        super.localChanged(ptr);

        //QMessageBox.information(main, "Test", "Test");

        QApplication.invokeLater(new Runnable() {

            public void run() {


                if (ptr.getNum() - 1 < main.getWidgets().intermedioTableWidget.rowCount()) {
                    main.actualizarEstado();
                    main.getWidgets().intermedioTableWidget.scrollToItem(main.getWidgets().intermedioTableWidget.item(ptr.getNum() - 1, 0));
                    main.getWidgets().intermedioTableWidget.item(ptr.getNum() - 1, 0).setBackground(new QBrush(QColor.magenta));
                    main.repaint();
                    main.getWidgets().pilaTableWidget.repaint();
                    main.getWidgets().intermedioTableWidget.repaint();
                    main.getWidgets().datosTableWidget.repaint();
                    main.getWidgets().salidaTextBrowser.repaint();
                }

            }
        });


        while (!continuar);
        continuar = false;



    }

    @Override
    @SuppressWarnings("empty-statement")
    protected void globalChanged(final IntegerPtr ptr) {
        super.globalChanged(ptr);

        //QMessageBox.information(main, "Test", "Test");

        QApplication.invokeLater(new Runnable() {

            public void run() {

                if (ptr.getNum() - 1 < main.getWidgets().intermedioTableWidget.rowCount()) {
                    main.actualizarEstado();
                    //main.getWidgets().intermedioTableWidget.setCurrentCell(ptr.getNum() - 1, 0);
                    main.getWidgets().intermedioTableWidget.scrollToItem(main.getWidgets().intermedioTableWidget.item(ptr.getNum() - 1, 0));
                    main.getWidgets().intermedioTableWidget.item(ptr.getNum() - 1, 0).setBackground(new QBrush(QColor.blue));
                    main.repaint();
                    main.getWidgets().pilaTableWidget.repaint();
                    main.getWidgets().intermedioTableWidget.repaint();
                    main.getWidgets().datosTableWidget.repaint();
                    main.getWidgets().salidaTextBrowser.repaint();



                }

            }
        });


        while (!continuar);
        continuar = false;



    }

    /**
     * @return the pausado
     */
    public boolean isPausado() {
        return pausado;
    }

    /**
     * @param pausado the pausado to set
     */
    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    /**
     * @return the continuar
     */
    public boolean isContinuar() {
        return continuar;
    }

    /**
     * @param continuar the continuar to set
     */
    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }
}
