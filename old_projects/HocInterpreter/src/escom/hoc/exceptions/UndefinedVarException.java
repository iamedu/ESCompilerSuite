/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.hoc.exceptions;

/**
 *
 * @author iamedu
 */
public class UndefinedVarException extends HocException {

    /**
     * Creates a new instance of <code>UndefinedVarException</code> without detail message.
     */
    public UndefinedVarException() {
    }


    /**
     * Constructs an instance of <code>UndefinedVarException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UndefinedVarException(String msg) {
        super(msg);
    }
}
