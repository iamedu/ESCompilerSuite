/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iamedu
 */
public class OutputPrinter {

    private final List<String> output;

    public OutputPrinter() {
        output = new ArrayList<String>();
    }

    public void printf(String format, Object... varargs) {
        String line = String.format(format, varargs);
        System.out.println(line);
        synchronized (output) {
            output.add(line);
        }
    }

    public int size() {
        return output.size();
    }

    public String getLines() {
        StringBuffer res = new StringBuffer();
        synchronized (output) {
            for(String line : output) {
                res.append(line);
            }
            output.clear();
        }
        return res.toString();
    }
}
