/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.functions;

import escom.hoc.exceptions.HocException;
import escom.hoc.interpreter.HocInterpreter;
import escom.hoc.interpreter.HocStack;
import escom.hoc.symbol.Constant;
import escom.hoc.symbol.Datum;
import escom.hoc.symbol.Function;
import escom.hoc.IntegerPtr;
/**
 *
 * @author iamedu
 */
public class And extends Function {

    public And(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d1;
        Datum d2;
        Datum result;
	Double zero = new Double(0);

        d2 = interpreter.getStack().pop();
        d1 = interpreter.getStack().pop();

	if(zero.compareTo(d1.getValue()) != 0 &&
	   zero.compareTo(d2.getValue()) != 0) {
		result = new Constant(1);
	} else {
		result = new Constant(0);
	}

        interpreter.getStack().push(result);
    }
}
