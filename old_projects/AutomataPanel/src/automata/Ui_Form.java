package automata;

/********************************************************************************
** Form generated from reading ui file 'generador.jui'
**
** Created: Tue May 12 08:36:34 2009
**      by: Qt User Interface Compiler version 4.5.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.gui.QPalette.ColorRole;
import elex.Automata;

public class Ui_Form implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout_2;
    public QVBoxLayout verticalLayout;
    public QHBoxLayout horizontalLayout;
    public QToolButton botonNuevo;
    public QToolButton botonBorrar;
    public QToolButton botonConcat;
    public QToolButton botonUnir;
    public QToolButton botonEstrella;
    public QToolButton cerraduraPositiva;
    public QToolButton botonInterrogacion;
    public QToolButton botonAutomatico;
    public QToolButton botonAnalizar;
    public QScrollArea scrollArea;
    public QWidget scrollAreaWidgetContents;
    public QGridLayout gridLayout;
    public AutomataWidget automataWidget;

    public Ui_Form() { super(); }

    public void setupUi(QWidget Form)
    {
        Form.setObjectName("Form");
        Form.resize(new QSize(568, 351).expandedTo(Form.minimumSizeHint()));
        Form.setWindowIcon(new QIcon(new QPixmap("classpath:icons/nuevo.png")));
        gridLayout_2 = new QGridLayout(Form);
        gridLayout_2.setObjectName("gridLayout_2");
        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        botonNuevo = new QToolButton(Form);
        botonNuevo.setObjectName("botonNuevo");
        botonNuevo.setIcon(new QIcon(new QPixmap("classpath:icons/nuevo.png")));
        botonNuevo.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(botonNuevo);

        botonBorrar = new QToolButton(Form);
        botonBorrar.setObjectName("botonBorrar");
        botonBorrar.setIcon(new QIcon(new QPixmap("classpath:icons/borrar.png")));
        botonBorrar.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(botonBorrar);

        botonConcat = new QToolButton(Form);
        botonConcat.setObjectName("botonConcat");
        botonConcat.setIcon(new QIcon(new QPixmap("classpath:icons/concat.png")));
        botonConcat.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(botonConcat);

        botonUnir = new QToolButton(Form);
        botonUnir.setObjectName("botonUnir");
        botonUnir.setIcon(new QIcon(new QPixmap("classpath:icons/or.png")));
        botonUnir.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(botonUnir);

        botonEstrella = new QToolButton(Form);
        botonEstrella.setObjectName("botonEstrella");
        botonEstrella.setIcon(new QIcon(new QPixmap("classpath:icons/estre.png")));
        botonEstrella.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(botonEstrella);

        cerraduraPositiva = new QToolButton(Form);
        cerraduraPositiva.setObjectName("cerraduraPositiva");
        cerraduraPositiva.setIcon(new QIcon(new QPixmap("classpath:icons/positi.png")));
        cerraduraPositiva.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(cerraduraPositiva);

        botonInterrogacion = new QToolButton(Form);
        botonInterrogacion.setObjectName("botonInterrogacion");
        botonInterrogacion.setIcon(new QIcon(new QPixmap("classpath:icons/inter.png")));
        botonInterrogacion.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(botonInterrogacion);

        botonAutomatico = new QToolButton(Form);
        botonAutomatico.setObjectName("botonAutomatico");
        botonAutomatico.setIcon(new QIcon(new QPixmap("classpath:icons/regex.png")));
        botonAutomatico.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(botonAutomatico);

        botonAnalizar = new QToolButton(Form);
        botonAnalizar.setObjectName("botonAnalizar");
        botonAnalizar.setIcon(new QIcon(new QPixmap("classpath:icons/analizar.png")));
        botonAnalizar.setIconSize(new QSize(48, 48));

        horizontalLayout.addWidget(botonAnalizar);


        verticalLayout.addLayout(horizontalLayout);

        scrollArea = new QScrollArea(Form);
        scrollArea.setObjectName("scrollArea");
        //scrollArea.setWidgetResizable(true);
        /*
        scrollAreaWidgetContents = new QWidget();
        scrollAreaWidgetContents.setObjectName("scrollAreaWidgetContents");
        scrollAreaWidgetContents.setGeometry(new QRect(0, 0, 544, 265));
        gridLayout = new QGridLayout(scrollAreaWidgetContents);
        gridLayout.setObjectName("gridLayout");
         */
        automataWidget = new AutomataWidget(scrollArea);
        //graphicsView.setObjectName("graphicsView");

        scrollArea.setWidget(automataWidget);

        scrollArea.setBackgroundRole(ColorRole.Light);

        //gridLayout.addWidget(graphicsView, 0, 0, 1, 1);

        scrollArea.setWidget(scrollAreaWidgetContents);

        verticalLayout.addWidget(scrollArea);


        gridLayout_2.addLayout(verticalLayout, 0, 0, 1, 1);

        retranslateUi(Form);

        Form.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget Form)
    {
        Form.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Form", "Generador Automata", null));
        botonNuevo.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Nuevo Automata", null));
        botonNuevo.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Nuevo", null));
        botonBorrar.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Borrar Automata", null));
        botonBorrar.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Borrar Automata", null));
        botonConcat.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Concatenar Automatas", null));
        botonConcat.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Concatenar Automatas", null));
        botonUnir.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Unir Automatas", null));
        botonUnir.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Unir Automatas", null));
        botonEstrella.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Cerradura Estrella", null));
        botonEstrella.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Cerradura Estrella", null));
        cerraduraPositiva.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Cerradura Positiva", null));
        cerraduraPositiva.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Cerradura Positiva", null));
        botonInterrogacion.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Interrogacion", null));
        botonInterrogacion.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Interrogacion", null));
        botonAutomatico.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Generar aut\u00f3maticamente", null));
        botonAutomatico.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Generar autom\u00e1ticamente", null));
        botonAnalizar.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Form", "Analizar Cadena", null));
        botonAnalizar.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Analizar Cadena", null));
    } // retranslateUi

}

