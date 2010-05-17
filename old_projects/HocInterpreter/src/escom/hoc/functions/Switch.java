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
public class Switch extends Function {

    public Switch(HocInterpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute(IntegerPtr pc) throws HocException {
        Datum d;
        Double data;
        Double zero = new Double(0);
        IntegerPtr savepc = new IntegerPtr(pc.getNum(), pc.getListener());
        int addr;
        int default_addr = -1;
        boolean executed = false;

        addr = Integer.parseInt(interpreter.getProgram().get(savepc.getNum()));
        IntegerPtr casecode = new IntegerPtr(addr, pc.getListener());

        while ("casecode".equals(interpreter.getProgram().get(casecode.getNum()))) {
            if (!"default".equals(interpreter.getProgram().get(casecode.getNum() + 3))) {
                addr = savepc.getNum() + 2;
                interpreter.execute(new IntegerPtr(addr, pc.getListener()));
                addr = casecode.getNum() + 3;
                interpreter.execute(new IntegerPtr(addr, pc.getListener()));

                d = interpreter.getStack().pop();
                data = new Double(d.getValue());

                if (data.compareTo(zero) != 0 || executed) {
                    executed = true;
                    addr = Integer.parseInt(interpreter.getProgram().get(casecode.getNum() + 1));
                    interpreter.execute(new IntegerPtr(addr, pc.getListener()));
                    d = interpreter.getStack().pop();
                    data = new Double(d.getValue());
                    if (data.compareTo(zero) != 0) {
                        break;
                    }

                }

            } else {

                default_addr = Integer.parseInt(interpreter.getProgram().get(casecode.getNum() + 1));
                if (executed) {
                    interpreter.execute(new IntegerPtr(default_addr, pc.getListener()));
                    d = interpreter.getStack().pop();
                    data = new Double(d.getValue());
                    if (data.compareTo(zero) != 0) {
                        break;
                    }
                }

            }



            if (casecode.getNum() + 2 >= interpreter.getProgram().size()) {
                break;
            }
            
            addr = Integer.parseInt(interpreter.getProgram().get(casecode.getNum() + 2));
            casecode.setNum(addr);

        }

        if (!executed && default_addr >= 0) {
            interpreter.execute(new IntegerPtr(default_addr, pc.getListener()));
            d = interpreter.getStack().pop();
        }

        //interpreter.setPc(savepc + 1);

        addr = Integer.parseInt(interpreter.getProgram().get(savepc.getNum() + 1));
        pc.setNum(addr);
        interpreter.resumeExecution();
    }
}
