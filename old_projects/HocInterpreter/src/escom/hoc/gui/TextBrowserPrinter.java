/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.gui;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QTextBrowser;
import escom.hoc.OutputPrinter;

/**
 *
 * @author iamedu
 */
public class TextBrowserPrinter extends OutputPrinter {

    private QTextBrowser browser;

    public TextBrowserPrinter(QTextBrowser browser) {
        this.browser = browser;
    }

    @Override
    public void printf(String format, Object... varargs) {
        super.printf(format, varargs);
        QApplication.invokeLater(new Runnable() {

            public void run() {

                browser.setPlainText(browser.toPlainText() + getLines());
                browser.verticalScrollBar().setValue(browser.verticalScrollBar().maximum());
            }
        });

    }
}
