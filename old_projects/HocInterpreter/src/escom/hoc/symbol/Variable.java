/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.hoc.symbol;

import escom.hoc.exceptions.HocException;
import escom.hoc.exceptions.UndefinedVarException;

/**
 *
 * @author iamedu
 */
public class Variable implements Datum {

    private String name;
    private boolean unset;
    private double value;

    public Variable(String name) {
        unset = true;
        this.name = name;
    }

    public boolean isUnset() {
        return unset;
    }

    public String getName() {
        return name;
    }

    public double getValue() throws HocException {
        if(unset) {
            throw new UndefinedVarException(name + " var unset");
        }
        return value;
    }

    public void setValue(double value) {
        unset = false;
        this.value = value;
    }

        @Override
    public String toString() {
        String result;
        result = "Variable " + name + ":";
        if(unset) {
            result += "UNSET";
        } else {
            result += String.valueOf(value);
        }
        return result;
    }


}
