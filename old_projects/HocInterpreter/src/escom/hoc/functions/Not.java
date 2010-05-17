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
public class Not extends Function {

    public Not(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d;
        Datum result;
        Double zero = new Double(0);
        d = interpreter.getStack().pop();
	
	if(zero.compareTo(d.getValue()) == 0) {
        	result = new Constant(1);
	} else {
        	result = new Constant(0);
	}
        interpreter.getStack().push(result);
    }
}
