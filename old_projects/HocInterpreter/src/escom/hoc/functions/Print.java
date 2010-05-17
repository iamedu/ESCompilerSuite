/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.functions;

import escom.hoc.exceptions.HocException;
import escom.hoc.interpreter.HocInterpreter;
import escom.hoc.symbol.Datum;
import escom.hoc.symbol.Function;
import escom.hoc.IntegerPtr;

/**
 *
 * @author iamedu
 */
public class Print extends Function {

    public Print(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d;
        d = interpreter.getStack().pop();

        interpreter.getPrinter().printf("\t%.8g\n", d.getValue());
        //System.out.printf("\t%.8g\n", d.getValue());
    }
}
