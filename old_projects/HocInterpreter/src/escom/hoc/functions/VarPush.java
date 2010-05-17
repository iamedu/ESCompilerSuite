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
public class VarPush extends Function {

    public VarPush(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        String value = (String) interpreter.getProgram().get(pc.getNum());
        Datum var;

        pc.incr();

        if(!interpreter.getSymbols().containsKey(value)) {
            interpreter.getSymbols().put(value, new Variable(value));
        }

        var = interpreter.getSymbols().get(value);

        interpreter.getStack().push(var);
    }
}
