/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablalalr;

import java.util.List;

/**
 *
 * @author iamedu
 */
public class Transition {

    private Grupo source;
    private Grupo dest;
    private String symbol;
    private int id;

    private static int idTransition;

    static {
        idTransition = 0;
    }

    public Transition() {
        id = idTransition++;
    }

    public int getId() {
        return id;
    }


    /**
     * @return the source
     */
    public Grupo getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(Grupo source) {
        this.source = source;
    }

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the dest
     */
    public Grupo getDest() {
        return dest;
    }

    /**
     * @param dest the dest to set
     */
    public void setDest(Grupo dest) {
        this.dest = dest;
    }
}
