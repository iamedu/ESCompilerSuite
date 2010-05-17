/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.hoc.symbol;

import escom.hoc.exceptions.HocException;
import java.io.Serializable;

/**
 *
 * @author iamedu
 */
public interface Datum extends Serializable {

    public double getValue() throws HocException;

}
