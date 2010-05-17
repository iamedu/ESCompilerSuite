/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablall1;

import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QWidget;

/**
 *
 * @author iamedu
 */
public class Analisis extends QWidget {

    private Ui_AnalisisForm form;
    private TablaLL1 tabla;
    private int rowCount;
    private boolean analizar;

    public Analisis() {
        form = new Ui_AnalisisForm();
        form.setupUi(this);

        rowCount = 0;
        analizar = true;

        setup();
    }

    public void setup() {
        QTableWidgetItem colItem;

        form.tableWidget.setColumnCount(3);

        colItem = new QTableWidgetItem();
        colItem.setText("Pila");
        form.tableWidget.setHorizontalHeaderItem(0, colItem);

        colItem = new QTableWidgetItem();
        colItem.setText("Cadena");
        form.tableWidget.setHorizontalHeaderItem(1, colItem);

        colItem = new QTableWidgetItem();
        colItem.setText("Accion");
        form.tableWidget.setHorizontalHeaderItem(2, colItem);

        form.pasosButton.clicked.connect(this, "paso()");
        form.completoButton.clicked.connect(this, "completo()");
    }

    public void paso() {
        String pila;
        String cadena;
        String accion;
        QTableWidgetItem item;

        if(!analizar) {
            return;
        }

        pila = tabla.getPila();
        cadena = tabla.getCadena() + "$";

        if(pila == null || pila.equals("")) {
            analizar = false;
            return;
        }


        if(cadena == null || cadena.equals("")) {
            analizar = false;
            return;
        }

        form.tableWidget.setRowCount(++rowCount);

        item = new QTableWidgetItem();
        item.setText(pila);
        form.tableWidget.setItem(rowCount - 1, 0, item);

        item = new QTableWidgetItem();
        item.setText(cadena);
        form.tableWidget.setItem(rowCount - 1, 1, item);

        if (tabla.step()) {
            accion = tabla.getAccion();
            item = new QTableWidgetItem();
            item.setText(accion);
            form.tableWidget.setItem(rowCount - 1, 2, item);

            if("$".equals(pila) && "$".equals(cadena)) {
                analizar = false;
            }

        } else {
            analizar = false;
        }

    }

    public void completo() {
        while(analizar) {
            paso();
        }
    }

    public void analizar(String cad) {
        tabla.analizar(cad);
    }

    public void setTabla(TablaLL1 tabla) {
        this.tabla = tabla;
    }
}
