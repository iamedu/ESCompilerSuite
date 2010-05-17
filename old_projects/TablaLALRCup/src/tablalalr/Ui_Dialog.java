package tablalalr;

/********************************************************************************
** Form generated from reading ui file 'Acerca.jui'
**
** Created: Sun May 24 16:48:27 2009
**      by: Qt User Interface Compiler version 4.5.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_Dialog implements com.trolltech.qt.QUiForm<QDialog>
{
    public QGridLayout gridLayout_2;
    public QFrame frame;
    public QGridLayout gridLayout;
    public QLabel label;

    public Ui_Dialog() { super(); }

    public void setupUi(QDialog Dialog)
    {
        Dialog.setObjectName("Dialog");
        Dialog.resize(new QSize(484, 182).expandedTo(Dialog.minimumSizeHint()));
        gridLayout_2 = new QGridLayout(Dialog);
        gridLayout_2.setObjectName("gridLayout_2");
        frame = new QFrame(Dialog);
        frame.setObjectName("frame");
        frame.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.StyledPanel);
        frame.setFrameShadow(com.trolltech.qt.gui.QFrame.Shadow.Raised);
        gridLayout = new QGridLayout(frame);
        gridLayout.setObjectName("gridLayout");
        label = new QLabel(frame);
        label.setObjectName("label");

        gridLayout.addWidget(label, 0, 0, 1, 1);


        gridLayout_2.addWidget(frame, 0, 0, 1, 1);

        retranslateUi(Dialog);

        Dialog.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog Dialog)
    {
        Dialog.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Dialog", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"+
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-size:16pt;\">Tabla LL(1)</span></p>\n"+
"<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-size:16pt;\">Este programa ilustra es la generaci\u00f3n de una Tabla LL(1)</span></p>\n"+
"<p align=\"center\" style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px; font-size:16pt;\"></p>\n"+
"<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-size:16pt;\">Versi\u00f3n 0.0</span></p>\n"+
"<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-size:16pt;\">Grupo 6CM1</span></p>\n"+
"<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-size:16pt;\">Escuela ESCOM</span></p></body></html>", null));
    } // retranslateUi

}

