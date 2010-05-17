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
public class Gt extends Function {

    public Gt(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d1;
        Datum d2;
	Double db1;
	Double db2;
        Datum result;

        d2 = interpreter.getStack().pop();
        d1 = interpreter.getStack().pop();

	db1 = new Double(d1.getValue());
	db2 = new Double(d2.getValue());

	if(db1.compareTo(db2) > 0) {
		result = new Constant(1);
	} else {
		result = new Constant(0);
	}

        interpreter.getStack().push(result);
    }
}
