/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.functions;

import escom.hoc.exceptions.HocException;
import escom.hoc.interpreter.HocInterpreter;
import escom.hoc.symbol.Datum;
import escom.hoc.IntegerPtr;
import escom.hoc.symbol.Function;

/**
 *
 * @author iamedu
 */
public class If extends Function {

    public If(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d;
        Double zero = new Double(0);
        IntegerPtr savepc = new IntegerPtr(pc.getNum(), pc.getListener());
        int addr;

        interpreter.execute(new IntegerPtr(savepc.getNum() + 3, pc.getListener()));

        d = interpreter.getStack().pop();
        if (zero.compareTo(d.getValue()) != 0) {
            addr = Integer.parseInt(interpreter.getProgram().
                    get(savepc.getNum()));
            interpreter.execute(new IntegerPtr(addr, pc.getListener()));
        } else if (!"stop".equals(interpreter.getProgram().
                get(savepc.getNum() + 1))) {
            addr = Integer.parseInt(interpreter.getProgram().get(
                    savepc.getNum() + 1));
            interpreter.execute(new IntegerPtr(addr, pc.getListener()));
        }
        //interpreter.setPc(savepc + 2);
        addr = Integer.parseInt(interpreter.getProgram().get(
                savepc.getNum() + 2));
        pc.setNum(addr);
        interpreter.resumeExecution();
    }
}
