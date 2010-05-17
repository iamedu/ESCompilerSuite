/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.hoc.symbol;

import escom.hoc.exceptions.HocException;
import escom.hoc.interpreter.HocInterpreter;
import escom.hoc.IntegerPtr;
import java.io.Serializable;

/**
 *
 * @author iamedu
 */
public abstract class Function implements Serializable {

    protected HocInterpreter interpreter;

    public Function(HocInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public abstract void execute(IntegerPtr pc) throws HocException;

}
