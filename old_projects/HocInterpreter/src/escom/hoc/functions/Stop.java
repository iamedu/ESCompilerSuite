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
public class Stop extends Function {

    public Stop(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        interpreter.stopExecution();
    }
}
