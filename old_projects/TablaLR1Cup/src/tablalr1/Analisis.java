/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablalr1;

import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QWidget;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author iameduAnalisis
 */
public class Analisis extends QWidget {

    private Ui_AnalisisForm form;
    private TablaLR1 tabla;
    private boolean analizar;
    private Stack<Integer> pila;
    private Stack<String> simbolos;
    private Stack<String> entrada;
    private String cadena;

    public Analisis() {
        form = new Ui_AnalisisForm();
        form.setupUi(this);

        analizar = true;

        setup();
    }

    public void setup() {
        QTableWidgetItem colItem;

        form.tableWidget.setColumnCount(4);

        colItem = new QTableWidgetItem();
        colItem.setText("Estados");
        form.tableWidget.setHorizontalHeaderItem(0, colItem);

        colItem = new QTableWidgetItem();
        colItem.setText("Pila");
        form.tableWidget.setHorizontalHeaderItem(1, colItem);

        colItem = new QTableWidgetItem();
        colItem.setText("Cadena");
        form.tableWidget.setHorizontalHeaderItem(2, colItem);

        colItem = new QTableWidgetItem();
        colItem.setText("Accion");
        form.tableWidget.setHorizontalHeaderItem(3, colItem);

        form.pasosButton.clicked.connect(this, "paso()");
        form.completoButton.clicked.connect(this, "completo()");
    }

    public void paso() {
        QTableWidgetItem colItem;
        String a = entrada.lastElement();
        String regla;
        List<String> r;
        String accion;
        int id;
        int e = pila.lastElement();
        int row;

        if (!analizar) {
            return;
        }

        row = form.tableWidget.rowCount();
        form.tableWidget.setRowCount(row + 1);

        colItem = new QTableWidgetItem();
        colItem.setText(getPilaText());
        form.tableWidget.setItem(row, 0, colItem);

        colItem = new QTableWidgetItem();
        colItem.setText(getSimbolosText());
        form.tableWidget.setItem(row, 1, colItem);

        colItem = new QTableWidgetItem();
        colItem.setText(getEntradaText());
        form.tableWidget.setItem(row, 2, colItem);

        try {
            regla = tabla.get(String.valueOf(e), a);
            if (regla == null) {
                analizar = false;
                return;
            }
        } catch (Exception ex) {
            analizar = false;
            return;
        }

        colItem = new QTableWidgetItem();
        colItem.setText(regla);
        form.tableWidget.setItem(row, 3, colItem);

        if ("accept".equals(regla)) {
            analizar = false;
            return;
        }

        accion = String.valueOf(regla.charAt(0));
        regla = regla.substring(1);
        id = Integer.parseInt(regla);

        if ("s".equals(accion)) {
            simbolos.add(entrada.pop());
            pila.add(id);
        } else if ("r".equals(accion)) {
            r = tabla.getGramatica().getReglas().get(id);
            for (int i = 1; i < r.size(); i++) {
                simbolos.pop();
                pila.pop();
            }
            simbolos.push(r.get(0));
            id = Integer.parseInt(tabla.get(String.valueOf(pila.lastElement()), simbolos.lastElement()));
            pila.push(id);
        }

    }

    public void completo() {
        while (analizar) {
            paso();
        }
    }

    private String nextToken() {
        String result = "";
        for (String g : tabla.getGramatica().getTerminales()) {
            if (cadena.startsWith(g)) {
                result = g;
            }
        }
        if(result.length() == 0 && cadena.length() > 0) {
            result = String.valueOf(cadena.charAt(0));
        }
        cadena = cadena.substring(result.length());
        return result;
    }

    public void analizar(String cad) {
        cadena = cad;

        simbolos = new Stack<String>();
        pila = new Stack<Integer>();
        entrada = new Stack<String>();

        pila.add(0);


        while (cadena.length() > 0) {
            entrada.add(nextToken());
        }
        entrada.add("$");

        Collections.reverse(entrada);

    }

    public String getSimbolosText() {
        StringBuffer buf = new StringBuffer();
        for (String p : simbolos) {
            buf.append(p + " ");
        }
        return buf.toString();
    }

    public String getEntradaText() {
        Stack<String> real = new Stack<String>();
        real.addAll(entrada);

        Collections.reverse(real);

        StringBuffer buf = new StringBuffer();
        for (String e : real) {
            buf.append(e + " ");
        }
        return buf.toString();
    }

    public String getPilaText() {
        StringBuffer buf = new StringBuffer();
        for (int e : pila) {
            buf.append(e + " ");
        }
        return buf.toString();
    }

    public void setTabla(TablaLR1 tabla) {
        this.tabla = tabla;
    }
}
