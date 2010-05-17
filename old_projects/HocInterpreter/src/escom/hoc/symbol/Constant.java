/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.symbol;

import escom.hoc.exceptions.HocException;

/**
 *
 * @author iamedu
 */
public class Constant implements Datum {

    private double value;

    public Constant(double value) {
        this.value = value;
    }

    public double getValue() throws HocException {
        return value;
    }

    @Override
    public String toString() {
        return "Constante " + String.valueOf(value);
    }


}
