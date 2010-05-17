/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablalr0;

import java.util.List;

/**
 *
 * @author iamedu
 */
public class Transition {

    private List<Token> source;
    private List<Token> dest;
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
    public List<Token> getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(List<Token> source) {
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
    public List<Token> getDest() {
        return dest;
    }

    /**
     * @param dest the dest to set
     */
    public void setDest(List<Token> dest) {
        this.dest = dest;
    }
}
