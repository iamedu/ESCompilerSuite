/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.functions;

import escom.hoc.exceptions.HocException;
import escom.hoc.interpreter.HocInterpreter;
import escom.hoc.symbol.Datum;
import escom.hoc.symbol.Function;
import escom.hoc.symbol.Variable;
import escom.hoc.IntegerPtr;

/**
 *
 * @author iamedu
 */
public class Asgn extends Function {

    public Asgn(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d1;
        Datum d2;

        Variable var;

        d1 = interpreter.getStack().pop();
        d2 = interpreter.getStack().pop();

        var = (Variable) d1;

        var.setValue(d2.getValue());

        interpreter.getStack().push(d2);
    }
}
