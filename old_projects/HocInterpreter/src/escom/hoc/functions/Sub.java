/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.hoc.functions;

import escom.hoc.exceptions.HocException;
import escom.hoc.interpreter.HocInterpreter;
import escom.hoc.symbol.Constant;
import escom.hoc.symbol.Datum;
import escom.hoc.symbol.Function;
import escom.hoc.IntegerPtr;

/**
 *
 * @author iamedu
 */
public class Sub extends Function {

    public Sub(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d1;
        Datum d2;
        Datum result;

        d2 = interpreter.getStack().pop();
        d1 = interpreter.getStack().pop();
        result = new Constant(d1.getValue() - d2.getValue());
        interpreter.getStack().push(result);
    }
}
