/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.hoc.exceptions;

/**
 *
 * @author iamedu
 */
public class HocException extends Exception {

    /**
     * Creates a new instance of <code>HocException</code> without detail message.
     */
    public HocException() {
    }


    /**
     * Constructs an instance of <code>HocException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public HocException(String msg) {
        super(msg);
    }
}
