/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QInputDialog;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QWidget;
import elex.*;
import elex.Automata;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iamedu
 */
public class Main extends QWidget {

    private Ui_Form form;
    private Automata automatas[];
    private String cadena,  temp;
    private String indices,  indices2,  opcion;
    private int index,  index2,  num_automatas = 0;
    private List<Estado> actuales;

    public Main() {
        form = new Ui_Form();
        form.setupUi(this);
        setupActions();

        automatas = new Automata[3];
        index = 0;
        num_automatas = 0;

    }

    private void setupActions() {
        form.botonNuevo.clicked.connect(this, "nuevo()");
        form.botonConcat.clicked.connect(this, "concat()");
        form.botonBorrar.clicked.connect(this, "borrar()");
        form.botonUnir.clicked.connect(this, "unir()");
        form.botonEstrella.clicked.connect(this, "estrella()");
        form.cerraduraPositiva.clicked.connect(this, "positiva()");
        form.botonInterrogacion.clicked.connect(this, "interrogacion()");
        form.botonAutomatico.clicked.connect(this, "automatico()");
        form.botonAnalizar.clicked.connect(this, "analizar()");
    }

    private void nuevo() {

        List<String> opciones = new ArrayList<String>();

        opciones.add("Si");
        opciones.add("No");

        do {
            index = QInputDialog.getInt(this, "Generacion automatica", "Donde quiere guardar el automata (el numero puede ser 0, 1 y 2)", 0, 0, 2);


        } while ((int) index < 0 || (int) index > 2);

        if (automatas[index] != null) {
            temp = QInputDialog.getItem(this, "Generacion Automatica", "Seguro que quiere borrar el automata", opciones);
            if (!"Si".equalsIgnoreCase(temp)) {
                return;
            } else {
                num_automatas--;
            }
        }

        cadena = QInputDialog.getText(this, "Nuevo automata", "Escribe un solo caracter");

        char c = cadena.charAt(0);
        automatas[index] = new Automata(c);
        num_automatas++;

        form.automataWidget.clear();
        form.automataWidget.addAutomatas(automatas);
    }

    private void borrar() {
        List<String> opciones = new ArrayList<String>();

        opciones.add("Si");
        opciones.add("No");

        index = QInputDialog.getInt(this, "Borrar", "Que automata quiere borrar (el numero puede ser 0, 1 y 2)", 0, 0, 2);

        if (automatas[index] != null) {
            temp = QInputDialog.getItem(this, "Borrar", "Seguro que quiere borrar el automata", opciones);
            if ("Si".equalsIgnoreCase(temp)) {
                automatas[index] = null;
                num_automatas--;
            }
        } else {
            QMessageBox.warning(this, "Borrar", "El automata no existe");
        }


        form.automataWidget.clear();
        form.automataWidget.addAutomatas(automatas);

    }

    private void concat() {
        if (num_automatas > 1) {

            index = QInputDialog.getInt(this, "Concatenacion", "Que automata quiere concatenar: 0, 1 o 2)", 0, 0, 2);

            if (index >= 0 && index < 3 && automatas[index] != null) {
                index2 = QInputDialog.getInt(this, "Concatenacion", "Con que automata quiere concatenar: 0, 1 o 2)", 0, 0, 2);

                if (index2 >= 0 && index2 < 3 && index2 != index && automatas[index2] != null) {
                    automatas[index].concat(automatas[index2]);
                    automatas[index2] = null;
                    num_automatas--;

                    form.automataWidget.clear();
                    form.automataWidget.addAutomatas(automatas);

                    return;
                }
            }
            QMessageBox.warning(this, "Concatenacion", "Automata invalido, favor de escoger otro");
        } else {
            QMessageBox.warning(this, "Concatenacion", "No hay automatas suficientes, favor de crear mas");
        }
    }

    private void unir() {
        if (num_automatas > 1) {

            index = QInputDialog.getInt(this, "Union", "Que automata quiere unir: 0, 1 o 2)", 0, 0, 2);

            if (index >= 0 && index < 3 && automatas[index] != null) {
                index2 = QInputDialog.getInt(this, "Union", "Con que automata quiere unir: 0, 1 o 2)", 0, 0, 2);

                if (index2 >= 0 && index2 < 3 && index2 != index && automatas[index2] != null) {
                    automatas[index].unir(automatas[index2]);
                    automatas[index2] = null;
                    num_automatas--;

                    form.automataWidget.clear();
                    form.automataWidget.addAutomatas(automatas);

                    return;
                }
            } else {
                QMessageBox.warning(this, "Union", "Automata invalido, favor de escoger otro");
            }
        } else {
            QMessageBox.warning(this, "Union", "No hay automatas suficientes, favor de crear mas");
        }
    }

    private void estrella() {
        if (num_automatas > 0) {

            index = QInputDialog.getInt(this, "Cerradura Estrella", "Que automata quiere realizar la cerradura Estrella: 0, 1 o 2 ", 0, 0, 2);
            if (index >= 0 && index < 3 && automatas[index] != null) {
                automatas[index].cerraduraEstrella();

                form.automataWidget.clear();
                form.automataWidget.addAutomatas(automatas);

                return;
            }
            QMessageBox.warning(this, "Cerradura Estrella", "Automata invalido, favor de escoger otro");
        } else {
            QMessageBox.warning(this, "Cerradura Estrella", "No hay automatas suficientes, favor de crear mas");
        }
    }

    private void positiva() {
        if (num_automatas > 0) {

            index = QInputDialog.getInt(this, "Cerradura Positiva", "Que automata quiere realizar la cerradura positiva: 0, 1 o 2 ", 0, 0, 2);
            if (index >= 0 && index < 3 && automatas[index] != null) {
                automatas[index].cerraduraPositiva();

                form.automataWidget.clear();
                form.automataWidget.addAutomatas(automatas);

                return;
            }
            QMessageBox.warning(this, "Positiva", "Automata invalido, favor de escoger otro");
        } else {
            QMessageBox.warning(this, "Positiva", "No hay automatas suficientes, favor de crear mas");
        }
    }

    private void interrogacion() {
        if (num_automatas > 0) {

            index = QInputDialog.getInt(this, "Interrogacion", "Que automata quiere realizar la Interrogacion: 0, 1 o 2 ", 0, 0, 2);
            if (index >= 0 && index < 3 && automatas[index] != null) {
                automatas[index].interrogacion();

                form.automataWidget.clear();
                form.automataWidget.addAutomatas(automatas);

                return;
            }
            QMessageBox.warning(this, "Interrogacion", "Automata invalido, favor de escoger otro");
        } else {
            QMessageBox.warning(this, "Interrogacion", "No hay automatas suficientes, favor de crear mas");
        }
    }

    private void automatico() {
        parser p;
        String regex;
        Automata a;

        List<String> opciones = new ArrayList<String>();

        opciones.add("Si");
        opciones.add("No");

        do {
            index = QInputDialog.getInt(this, "Generacion automatica", "Donde quiere guardar el automata (el numero puede ser 0, 1 y 2)", 0, 0, 2);


        } while ((int) index < 0 || (int) index > 2);

        if (automatas[index] != null) {
            temp = QInputDialog.getItem(this, "Generacion Automatica", "Seguro que quiere borrar el automata", opciones);
            if (!"Si".equalsIgnoreCase(temp)) {
                return;
            } else {
                num_automatas--;
            }
        }

        regex = QInputDialog.getText(this, "Generacion automática", "Escribe tu expresion regular");

        p = new parser(new Yylex(new StringReader(regex)));
	try {
        	a = (Automata)p.parse().value;
	} catch(Exception ex) {
		a = null;
		ex.printStackTrace();
	}

        if (a == null) {
            QMessageBox.warning(this, "Error", "Revisa tu expresion regular");
            num_automatas++;
            return;
        }

        automatas[index] = a;
        num_automatas++;

        form.automataWidget.clear();
        form.automataWidget.addAutomatas(automatas);

    }

    private void analizar() {
        if (num_automatas > 0) {

            index = QInputDialog.getInt(this, "Analizar", "Que automata quiere probar: 0, 1 o 2", 0, 0, 2);


            if (index >= 0 && index < 3 && automatas[index] == null) {
                QMessageBox.warning(this, "Analizar", "Automata inválido favor de escoger otra vez");
                return;
            }
            cadena = QInputDialog.getText(this, "Analizar", "Digite la cadena ");
            if (index >= 0 && index < 3) {
                actuales = automatas[index].getInicialList();
                form.automataWidget.setActuales(actuales);
                form.automataWidget.repaint();
 
                for(int i = 0; i < cadena.length(); i++) {
                    QMessageBox.information(this, "Analizar", "Siguiente caracter : " + cadena.charAt(i));
                    actuales = Automata.irA(actuales, cadena.charAt(i));
                    form.automataWidget.setActuales(actuales);
                    form.automataWidget.repaint();
                }

                if(automatas[index].esAceptacion(actuales)) {
                    QMessageBox.information(this, "Analizar", "Cadena valida");
                } else {
                    QMessageBox.information(this, "Analizar", "Cadena invalida");
                }
            } else {
                QMessageBox.information(this, "Analizar", "Elegir automata valido");
            }
        } else {
            QMessageBox.warning(this, "Analizar", "No hay automatas suficientes favor de crear mas");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QApplication.initialize(args);


        Main m = new Main();
        m.show();

        QApplication.exec();
    }
}
