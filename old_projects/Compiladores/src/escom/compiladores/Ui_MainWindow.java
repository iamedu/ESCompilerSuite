package escom.compiladores;

/********************************************************************************
** Form generated from reading ui file 'menu_comp.jui'
**
** Created: Wed Jun 17 23:36:50 2009
**      by: Qt User Interface Compiler version 4.5.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_MainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QAction actionAcerca_de;
    public QAction actionCerrar;
    public QWidget centralwidget;
    public QGridLayout gridLayout;
    public QTreeWidget treeWidget;
    public QMenuBar menubar;
    public QMenu menuArchivo;
    public QMenu menuAyuda;
    public QStatusBar statusbar;

    public Ui_MainWindow() { super(); }

    public void setupUi(QMainWindow MainWindow)
    {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(418, 326).expandedTo(MainWindow.minimumSizeHint()));
        actionAcerca_de = new QAction(MainWindow);
        actionAcerca_de.setObjectName("actionAcerca_de");
        actionCerrar = new QAction(MainWindow);
        actionCerrar.setObjectName("actionCerrar");
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout = new QGridLayout(centralwidget);
        gridLayout.setObjectName("gridLayout");
        treeWidget = new QTreeWidget(centralwidget);
        treeWidget.setObjectName("treeWidget");
        QFont font = new QFont();
        font.setPointSize(16);
        treeWidget.setFont(font);

        gridLayout.addWidget(treeWidget, 0, 0, 1, 1);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 418, 24));
        menuArchivo = new QMenu(menubar);
        menuArchivo.setObjectName("menuArchivo");
        menuAyuda = new QMenu(menubar);
        menuAyuda.setObjectName("menuAyuda");
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);

        menubar.addAction(menuArchivo.menuAction());
        menubar.addAction(menuAyuda.menuAction());
        menuArchivo.addAction(actionCerrar);
        menuAyuda.addAction(actionAcerca_de);
        retranslateUi(MainWindow);

        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "MainWindow", null));
        actionAcerca_de.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Acerca de", null));
        actionCerrar.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Cerrar", null));
        treeWidget.headerItem().setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Compiladores", null));
        treeWidget.clear();

        QTreeWidgetItem __item = new QTreeWidgetItem(treeWidget);
        __item.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Analisis léxico", null));

        QTreeWidgetItem __item1 = new QTreeWidgetItem(treeWidget);
        __item1.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Analisis sintactico", null));

        QTreeWidgetItem __item2 = new QTreeWidgetItem(__item1);
        __item2.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Descendente", null));

        QTreeWidgetItem __item3 = new QTreeWidgetItem(__item2);
        __item3.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "LL(1)", null));

        QTreeWidgetItem __item4 = new QTreeWidgetItem(__item1);
        __item4.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Ascendente", null));

        QTreeWidgetItem __item5 = new QTreeWidgetItem(__item4);
        __item5.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "LR(0)", null));

        QTreeWidgetItem __item6 = new QTreeWidgetItem(__item4);
        __item6.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "LR(1)", null));

        QTreeWidgetItem __item7 = new QTreeWidgetItem(__item4);
        __item7.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "LALR", null));

        QTreeWidgetItem __item8 = new QTreeWidgetItem(treeWidget);
        __item8.setText(0, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Traduccion dirigada por sintaxis", null));
        menuArchivo.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Archivo", null));
        menuAyuda.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Ayuda", null));
    } // retranslateUi

}

