package tablalr0;

/********************************************************************************
** Form generated from reading ui file 'Analisis.jui'
**
** Created: Mon May 25 13:23:59 2009
**      by: Qt User Interface Compiler version 4.5.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_AnalisisForm implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout_3;
    public QHBoxLayout horizontalLayout;
    public QTableWidget tableWidget;
    public QVBoxLayout verticalLayout_2;
    public QGroupBox groupBox;
    public QGridLayout gridLayout;
    public QVBoxLayout verticalLayout;
    public QPushButton pasosButton;
    public QPushButton completoButton;
    public QGroupBox groupBox_2;
    public QGridLayout gridLayout_2;
    public QPushButton cerrarButton;

    public Ui_AnalisisForm() { super(); }

    public void setupUi(QWidget AnalisisForm)
    {
        AnalisisForm.setObjectName("AnalisisForm");
        AnalisisForm.resize(new QSize(568, 351).expandedTo(AnalisisForm.minimumSizeHint()));
        gridLayout_3 = new QGridLayout(AnalisisForm);
        gridLayout_3.setObjectName("gridLayout_3");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        tableWidget = new QTableWidget(AnalisisForm);
        tableWidget.setObjectName("tableWidget");

        horizontalLayout.addWidget(tableWidget);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2.setObjectName("verticalLayout_2");
        groupBox = new QGroupBox(AnalisisForm);
        groupBox.setObjectName("groupBox");
        groupBox.setMinimumSize(new QSize(0, 0));
        gridLayout = new QGridLayout(groupBox);
        gridLayout.setObjectName("gridLayout");
        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        pasosButton = new QPushButton(groupBox);
        pasosButton.setObjectName("pasosButton");

        verticalLayout.addWidget(pasosButton);

        completoButton = new QPushButton(groupBox);
        completoButton.setObjectName("completoButton");

        verticalLayout.addWidget(completoButton);


        gridLayout.addLayout(verticalLayout, 0, 0, 1, 1);


        verticalLayout_2.addWidget(groupBox);

        groupBox_2 = new QGroupBox(AnalisisForm);
        groupBox_2.setObjectName("groupBox_2");
        groupBox_2.setMaximumSize(new QSize(16777215, 80));
        gridLayout_2 = new QGridLayout(groupBox_2);
        gridLayout_2.setObjectName("gridLayout_2");
        cerrarButton = new QPushButton(groupBox_2);
        cerrarButton.setObjectName("cerrarButton");

        gridLayout_2.addWidget(cerrarButton, 0, 0, 1, 1);


        verticalLayout_2.addWidget(groupBox_2);


        horizontalLayout.addLayout(verticalLayout_2);


        gridLayout_3.addLayout(horizontalLayout, 0, 0, 1, 1);

        retranslateUi(AnalisisForm);
        cerrarButton.clicked.connect(AnalisisForm, "close()");

        AnalisisForm.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget AnalisisForm)
    {
        AnalisisForm.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("AnalisisForm", "Form", null));
        tableWidget.clear();
        tableWidget.setColumnCount(0);
        tableWidget.setRowCount(0);
        groupBox.setTitle(com.trolltech.qt.core.QCoreApplication.translate("AnalisisForm", "Acciones", null));
        pasosButton.setText(com.trolltech.qt.core.QCoreApplication.translate("AnalisisForm", "Pasos", null));
        completoButton.setText(com.trolltech.qt.core.QCoreApplication.translate("AnalisisForm", "Completo", null));
        groupBox_2.setTitle("");
        cerrarButton.setText(com.trolltech.qt.core.QCoreApplication.translate("AnalisisForm", "Cerrar", null));
    } // retranslateUi

}

