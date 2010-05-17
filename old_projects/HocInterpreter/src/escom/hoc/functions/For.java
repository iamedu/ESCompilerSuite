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
public class For extends Function {

    public For(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d;
        Double zero = new Double(0);
        IntegerPtr savepc = new IntegerPtr(pc.getNum(), pc.getListener());
        int addr;

	if(!"stop".equals(interpreter.getProgram().get(savepc.getNum()))) {
		addr = Integer.parseInt(interpreter.getProgram().
						get(savepc.getNum()));
		interpreter.execute(new IntegerPtr(addr, pc.getListener()));
	}

        interpreter.execute(new IntegerPtr(savepc.getNum() + 4,
						pc.getListener()));
        d = interpreter.getStack().pop();

        while (zero.compareTo(d.getValue()) != 0) {
            addr = Integer.parseInt(interpreter.getProgram().
						get(savepc.getNum() + 1));
            interpreter.execute(new IntegerPtr(addr, pc.getListener()));

            addr = Integer.parseInt(interpreter.getProgram().
                                                get(savepc.getNum() + 2));
            interpreter.execute(new IntegerPtr(addr, pc.getListener()));

            interpreter.execute(new IntegerPtr(savepc.getNum() + 4,
						pc.getListener()));
            d = interpreter.getStack().pop();
        }

        addr = Integer.parseInt(interpreter.getProgram().get(savepc.getNum() + 3));
        pc.setNum(addr);
        interpreter.resumeExecution();
    }
}
