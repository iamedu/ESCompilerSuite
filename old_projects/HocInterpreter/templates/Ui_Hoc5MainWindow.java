/********************************************************************************
** Form generated from reading ui file 'Hoc5.jui'
**
** Created: Sun Jun 14 09:03:24 2009
**      by: Qt User Interface Compiler version 4.5.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_Hoc5MainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QAction actionAbrir;
    public QAction actionGuardar;
    public QAction actionSalir;
    public QAction actionCodigo_Intermedio;
    public QAction actionPila;
    public QAction actionDatos;
    public QAction actionControl;
    public QAction actionManual;
    public QAction actionAcerca_de;
    public QAction actionNuevo;
    public QAction actionCerrar;
    public QAction actionSalida;
    public QWidget centralwidget;
    public QGridLayout gridLayout_2;
    public QGroupBox codigoGroupBox;
    public QGridLayout gridLayout;
    public QPlainTextEdit codigoTextEdit;
    public QMenuBar menubar;
    public QMenu menuArchivo;
    public QMenu menuVer;
    public QMenu menuAyuda;
    public QStatusBar statusbar;
    public QDockWidget controlDockWidget;
    public QWidget controlDockWidgetContents;
    public QGridLayout gridLayout_3;
    public QHBoxLayout horizontalLayout;
    public QSpacerItem horizontalSpacer;
    public QVBoxLayout verticalLayout;
    public QPushButton completoPushButton;
    public QPushButton pausadoPushButton;
    public QPushButton pasoPushButton;
    public QSpacerItem horizontalSpacer_2;
    public QDockWidget intermedioDockWidget;
    public QWidget intermedioDockWidgetContents;
    public QGridLayout gridLayout_4;
    public QTableWidget intermedioTableWidget;
    public QDockWidget pilaDockWidget;
    public QWidget pilaDockWidgetContents;
    public QGridLayout gridLayout_5;
    public QTableWidget pilaTableWidget;
    public QDockWidget datosDockWidget;
    public QWidget datosDockWidgetContents;
    public QGridLayout gridLayout_6;
    public QTableWidget datosTableWidget;
    public QDockWidget salidaDockWidget;
    public QWidget salidaDockWidgetContents;
    public QGridLayout gridLayout_7;
    public QTextBrowser salidaTextBrowser;

    public Ui_Hoc5MainWindow() { super(); }

    public void setupUi(QMainWindow Hoc5MainWindow)
    {
        Hoc5MainWindow.setObjectName("Hoc5MainWindow");
        Hoc5MainWindow.setEnabled(true);
        Hoc5MainWindow.resize(new QSize(655, 481).expandedTo(Hoc5MainWindow.minimumSizeHint()));
        Hoc5MainWindow.setDockNestingEnabled(false);
        Hoc5MainWindow.setUnifiedTitleAndToolBarOnMac(false);
        actionAbrir = new QAction(Hoc5MainWindow);
        actionAbrir.setObjectName("actionAbrir");
        actionGuardar = new QAction(Hoc5MainWindow);
        actionGuardar.setObjectName("actionGuardar");
        actionSalir = new QAction(Hoc5MainWindow);
        actionSalir.setObjectName("actionSalir");
        actionCodigo_Intermedio = new QAction(Hoc5MainWindow);
        actionCodigo_Intermedio.setObjectName("actionCodigo_Intermedio");
        actionCodigo_Intermedio.setCheckable(false);
        actionCodigo_Intermedio.setChecked(false);
        actionPila = new QAction(Hoc5MainWindow);
        actionPila.setObjectName("actionPila");
        actionPila.setCheckable(false);
        actionPila.setChecked(false);
        actionDatos = new QAction(Hoc5MainWindow);
        actionDatos.setObjectName("actionDatos");
        actionDatos.setCheckable(false);
        actionDatos.setChecked(false);
        actionControl = new QAction(Hoc5MainWindow);
        actionControl.setObjectName("actionControl");
        actionControl.setCheckable(false);
        actionControl.setChecked(false);
        actionManual = new QAction(Hoc5MainWindow);
        actionManual.setObjectName("actionManual");
        actionAcerca_de = new QAction(Hoc5MainWindow);
        actionAcerca_de.setObjectName("actionAcerca_de");
        actionNuevo = new QAction(Hoc5MainWindow);
        actionNuevo.setObjectName("actionNuevo");
        actionCerrar = new QAction(Hoc5MainWindow);
        actionCerrar.setObjectName("actionCerrar");
        actionSalida = new QAction(Hoc5MainWindow);
        actionSalida.setObjectName("actionSalida");
        actionSalida.setCheckable(false);
        actionSalida.setChecked(false);
        centralwidget = new QWidget(Hoc5MainWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout_2 = new QGridLayout(centralwidget);
        gridLayout_2.setObjectName("gridLayout_2");
        codigoGroupBox = new QGroupBox(centralwidget);
        codigoGroupBox.setObjectName("codigoGroupBox");
        gridLayout = new QGridLayout(codigoGroupBox);
        gridLayout.setObjectName("gridLayout");
        codigoTextEdit = new QPlainTextEdit(codigoGroupBox);
        codigoTextEdit.setObjectName("codigoTextEdit");

        gridLayout.addWidget(codigoTextEdit, 0, 0, 1, 1);


        gridLayout_2.addWidget(codigoGroupBox, 0, 0, 1, 1);

        Hoc5MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(Hoc5MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 655, 24));
        menuArchivo = new QMenu(menubar);
        menuArchivo.setObjectName("menuArchivo");
        menuVer = new QMenu(menubar);
        menuVer.setObjectName("menuVer");
        menuAyuda = new QMenu(menubar);
        menuAyuda.setObjectName("menuAyuda");
        Hoc5MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(Hoc5MainWindow);
        statusbar.setObjectName("statusbar");
        Hoc5MainWindow.setStatusBar(statusbar);
        controlDockWidget = new QDockWidget(Hoc5MainWindow);
        controlDockWidget.setObjectName("controlDockWidget");
        controlDockWidget.setMaximumSize(new QSize(524287, 135));
        controlDockWidgetContents = new QWidget();
        controlDockWidgetContents.setObjectName("controlDockWidgetContents");
        gridLayout_3 = new QGridLayout(controlDockWidgetContents);
        gridLayout_3.setObjectName("gridLayout_3");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        horizontalLayout.addItem(horizontalSpacer);

        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        completoPushButton = new QPushButton(controlDockWidgetContents);
        completoPushButton.setObjectName("completoPushButton");

        verticalLayout.addWidget(completoPushButton);

        pausadoPushButton = new QPushButton(controlDockWidgetContents);
        pausadoPushButton.setObjectName("pausadoPushButton");

        verticalLayout.addWidget(pausadoPushButton);

        pasoPushButton = new QPushButton(controlDockWidgetContents);
        pasoPushButton.setObjectName("pasoPushButton");

        verticalLayout.addWidget(pasoPushButton);


        horizontalLayout.addLayout(verticalLayout);

        horizontalSpacer_2 = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        horizontalLayout.addItem(horizontalSpacer_2);


        gridLayout_3.addLayout(horizontalLayout, 0, 0, 1, 1);

        controlDockWidget.setWidget(controlDockWidgetContents);
        Hoc5MainWindow.addDockWidget(com.trolltech.qt.core.Qt.DockWidgetArea.resolve(2), controlDockWidget);
        intermedioDockWidget = new QDockWidget(Hoc5MainWindow);
        intermedioDockWidget.setObjectName("intermedioDockWidget");
        intermedioDockWidgetContents = new QWidget();
        intermedioDockWidgetContents.setObjectName("intermedioDockWidgetContents");
        gridLayout_4 = new QGridLayout(intermedioDockWidgetContents);
        gridLayout_4.setObjectName("gridLayout_4");
        intermedioTableWidget = new QTableWidget(intermedioDockWidgetContents);
        intermedioTableWidget.setObjectName("intermedioTableWidget");

        gridLayout_4.addWidget(intermedioTableWidget, 0, 0, 1, 1);

        intermedioDockWidget.setWidget(intermedioDockWidgetContents);
        Hoc5MainWindow.addDockWidget(com.trolltech.qt.core.Qt.DockWidgetArea.resolve(2), intermedioDockWidget);
        pilaDockWidget = new QDockWidget(Hoc5MainWindow);
        pilaDockWidget.setObjectName("pilaDockWidget");
        pilaDockWidgetContents = new QWidget();
        pilaDockWidgetContents.setObjectName("pilaDockWidgetContents");
        gridLayout_5 = new QGridLayout(pilaDockWidgetContents);
        gridLayout_5.setObjectName("gridLayout_5");
        pilaTableWidget = new QTableWidget(pilaDockWidgetContents);
        pilaTableWidget.setObjectName("pilaTableWidget");

        gridLayout_5.addWidget(pilaTableWidget, 0, 0, 1, 1);

        pilaDockWidget.setWidget(pilaDockWidgetContents);
        Hoc5MainWindow.addDockWidget(com.trolltech.qt.core.Qt.DockWidgetArea.resolve(8), pilaDockWidget);
        datosDockWidget = new QDockWidget(Hoc5MainWindow);
        datosDockWidget.setObjectName("datosDockWidget");
        datosDockWidgetContents = new QWidget();
        datosDockWidgetContents.setObjectName("datosDockWidgetContents");
        gridLayout_6 = new QGridLayout(datosDockWidgetContents);
        gridLayout_6.setObjectName("gridLayout_6");
        datosTableWidget = new QTableWidget(datosDockWidgetContents);
        datosTableWidget.setObjectName("datosTableWidget");

        gridLayout_6.addWidget(datosTableWidget, 0, 0, 1, 1);

        datosDockWidget.setWidget(datosDockWidgetContents);
        Hoc5MainWindow.addDockWidget(com.trolltech.qt.core.Qt.DockWidgetArea.resolve(8), datosDockWidget);
        salidaDockWidget = new QDockWidget(Hoc5MainWindow);
        salidaDockWidget.setObjectName("salidaDockWidget");
        salidaDockWidgetContents = new QWidget();
        salidaDockWidgetContents.setObjectName("salidaDockWidgetContents");
        gridLayout_7 = new QGridLayout(salidaDockWidgetContents);
        gridLayout_7.setObjectName("gridLayout_7");
        salidaTextBrowser = new QTextBrowser(salidaDockWidgetContents);
        salidaTextBrowser.setObjectName("salidaTextBrowser");

        gridLayout_7.addWidget(salidaTextBrowser, 0, 0, 1, 1);

        salidaDockWidget.setWidget(salidaDockWidgetContents);
        Hoc5MainWindow.addDockWidget(com.trolltech.qt.core.Qt.DockWidgetArea.resolve(8), salidaDockWidget);

        menubar.addAction(menuArchivo.menuAction());
        menubar.addAction(menuVer.menuAction());
        menubar.addAction(menuAyuda.menuAction());
        menuArchivo.addAction(actionNuevo);
        menuArchivo.addSeparator();
        menuArchivo.addAction(actionAbrir);
        menuArchivo.addAction(actionGuardar);
        menuArchivo.addSeparator();
        menuArchivo.addAction(actionCerrar);
        menuArchivo.addAction(actionSalir);
        menuVer.addAction(actionControl);
        menuVer.addAction(actionCodigo_Intermedio);
        menuVer.addAction(actionPila);
        menuVer.addAction(actionDatos);
        menuVer.addAction(actionSalida);
        menuAyuda.addAction(actionManual);
        menuAyuda.addAction(actionAcerca_de);
        retranslateUi(Hoc5MainWindow);
        actionControl.triggered.connect(controlDockWidget, "show()");
        actionCodigo_Intermedio.triggered.connect(intermedioDockWidget, "show()");
        actionPila.triggered.connect(pilaDockWidget, "show()");
        actionDatos.triggered.connect(datosDockWidget, "show()");
        actionSalir.triggered.connect(Hoc5MainWindow, "close()");
        actionSalida.triggered.connect(salidaDockWidget, "raise()");

        Hoc5MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow Hoc5MainWindow)
    {
        Hoc5MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Interprete Hoc5", null));
        actionAbrir.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Abrir", null));
        actionGuardar.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Guardar", null));
        actionSalir.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Salir", null));
        actionCodigo_Intermedio.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Codigo Intermedio", null));
        actionPila.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Pila", null));
        actionDatos.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Datos", null));
        actionControl.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Control", null));
        actionManual.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Manual", null));
        actionAcerca_de.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Acerca de", null));
        actionNuevo.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Nuevo", null));
        actionCerrar.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Cerrar", null));
        actionSalida.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Salida", null));
        codigoGroupBox.setTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "C\u00f3digo", null));
        menuArchivo.setTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Archivo", null));
        menuVer.setTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Ver", null));
        menuAyuda.setTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Ayuda", null));
        controlDockWidget.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Control", null));
        completoPushButton.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Completo", null));
        pausadoPushButton.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Pausado", null));
        pasoPushButton.setText(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Paso", null));
        intermedioDockWidget.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "C\u00f3digo Intermedio", null));
        intermedioTableWidget.clear();
        intermedioTableWidget.setColumnCount(0);
        intermedioTableWidget.setRowCount(0);
        pilaDockWidget.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Pila", null));
        pilaTableWidget.clear();
        pilaTableWidget.setColumnCount(0);
        pilaTableWidget.setRowCount(0);
        datosDockWidget.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Datos", null));
        datosTableWidget.clear();
        datosTableWidget.setColumnCount(0);
        datosTableWidget.setRowCount(0);
        salidaDockWidget.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Hoc5MainWindow", "Salida", null));
    } // retranslateUi

}

