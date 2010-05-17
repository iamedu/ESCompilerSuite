package tablalr0;

/********************************************************************************
** Form generated from reading ui file 'MainWindow.jui'
**
** Created: Sun May 24 17:03:54 2009
**      by: Qt User Interface Compiler version 4.5.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_MainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QAction actionNueva_Gramatica;
    public QAction actionGuardar_Gramatica;
    public QAction actionTabla_LR0;
    public QAction actionCargar_Gramatica;
    public QAction actionSalir;
    public QWidget centralwidget;
    public QGridLayout gridLayout_2;
    public QHBoxLayout horizontalLayout_2;
    public QGroupBox groupBox;
    public QHBoxLayout horizontalLayout;
    public QVBoxLayout verticalLayout;
    public QLabel label;
    public QPlainTextEdit plainTextEdit;
    public QToolButton toolButton;
    public QLabel label_2;
    public QPlainTextEdit plainTextEdit_2;
    public QToolButton toolButton_2;
    public QGroupBox groupBox_2;
    public QGridLayout gridLayout;
    public QTableWidget tableWidget;
    public QMenuBar menubar;
    public QMenu menuArchivo;
    public QMenu menuAcerca_de;
    public QStatusBar statusbar;

    public Ui_MainWindow() { super(); }

    public void setupUi(QMainWindow MainWindow)
    {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(798, 570).expandedTo(MainWindow.minimumSizeHint()));
        actionNueva_Gramatica = new QAction(MainWindow);
        actionNueva_Gramatica.setObjectName("actionNueva_Gramatica");
        actionGuardar_Gramatica = new QAction(MainWindow);
        actionGuardar_Gramatica.setObjectName("actionGuardar_Gramatica");
        actionTabla_LR0 = new QAction(MainWindow);
        actionTabla_LR0.setObjectName("actionTabla_LR0");
        actionCargar_Gramatica = new QAction(MainWindow);
        actionCargar_Gramatica.setObjectName("actionCargar_Gramatica");
        actionSalir = new QAction(MainWindow);
        actionSalir.setObjectName("actionSalir");
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout_2 = new QGridLayout(centralwidget);
        gridLayout_2.setObjectName("gridLayout_2");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2.setObjectName("horizontalLayout_2");
        groupBox = new QGroupBox(centralwidget);
        groupBox.setObjectName("groupBox");
        groupBox.setMaximumSize(new QSize(250, 16777195));
        horizontalLayout = new QHBoxLayout(groupBox);
        horizontalLayout.setObjectName("horizontalLayout");
        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        label = new QLabel(groupBox);
        label.setObjectName("label");

        verticalLayout.addWidget(label);

        plainTextEdit = new QPlainTextEdit(groupBox);
        plainTextEdit.setObjectName("plainTextEdit");

        verticalLayout.addWidget(plainTextEdit);

        toolButton = new QToolButton(groupBox);
        toolButton.setObjectName("toolButton");

        verticalLayout.addWidget(toolButton);

        label_2 = new QLabel(groupBox);
        label_2.setObjectName("label_2");

        verticalLayout.addWidget(label_2);

        plainTextEdit_2 = new QPlainTextEdit(groupBox);
        plainTextEdit_2.setObjectName("plainTextEdit_2");

        verticalLayout.addWidget(plainTextEdit_2);

        toolButton_2 = new QToolButton(groupBox);
        toolButton_2.setObjectName("toolButton_2");

        verticalLayout.addWidget(toolButton_2);


        horizontalLayout.addLayout(verticalLayout);


        horizontalLayout_2.addWidget(groupBox);

        groupBox_2 = new QGroupBox(centralwidget);
        groupBox_2.setObjectName("groupBox_2");
        gridLayout = new QGridLayout(groupBox_2);
        gridLayout.setObjectName("gridLayout");
        tableWidget = new QTableWidget(groupBox_2);
        tableWidget.setObjectName("tableWidget");

        gridLayout.addWidget(tableWidget, 0, 0, 1, 1);


        horizontalLayout_2.addWidget(groupBox_2);


        gridLayout_2.addLayout(horizontalLayout_2, 0, 0, 1, 1);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 798, 21));
        menuArchivo = new QMenu(menubar);
        menuArchivo.setObjectName("menuArchivo");
        menuAcerca_de = new QMenu(menubar);
        menuAcerca_de.setObjectName("menuAcerca_de");
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);

        menubar.addAction(menuArchivo.menuAction());
        menubar.addAction(menuAcerca_de.menuAction());
        menuArchivo.addAction(actionNueva_Gramatica);
        menuArchivo.addAction(actionGuardar_Gramatica);
        menuArchivo.addAction(actionCargar_Gramatica);
        menuArchivo.addSeparator();
        menuArchivo.addAction(actionSalir);
        menuAcerca_de.addAction(actionTabla_LR0);
        retranslateUi(MainWindow);

        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "An\u00e1lisis LR(0)", null));
        actionNueva_Gramatica.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Nueva Gramatica", null));
        actionGuardar_Gramatica.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Guardar Gramatica", null));
        actionTabla_LR0.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Acerca de...", null));
        actionCargar_Gramatica.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Cargar Gramatica", null));
        actionSalir.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Salir", null));
        groupBox.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "An\u00e1lisis LR(0)", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Escriba las Reglas", null));
        toolButton.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Pintar la Tabla", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Analizar cadena", null));
        toolButton_2.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Analizar cadena", null));
        groupBox_2.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Tabla LR(0)", null));
        tableWidget.clear();
        tableWidget.setColumnCount(0);
        tableWidget.setRowCount(0);
        menuArchivo.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Archivo", null));
        menuAcerca_de.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Ayuda", null));
    } // retranslateUi

}

