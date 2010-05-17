/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablalalr;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author iamedu
 */
public class Token {

    private int punto;
    private List<String> regla;
    private List<String> siguientes;

    public Token(Token otro) {
	punto = otro.punto;
	regla = new ArrayList<String>(otro.regla);
	siguientes = new ArrayList<String>(otro.siguientes);
    }

    public Token() {
        punto = 1;
    }

    /**
     * @return the punto
     */
    public int getPunto() {
        return punto;
    }

    /**
     * @param punto the punto to set
     */
    public void setPunto(int punto) {
        this.punto = punto;
    }

    /**
     * @return the regla
     */
    public List<String> getRegla() {
        return regla;
    }

    /**
     * @param regla the regla to set
     */
    public void setRegla(List<String> regla) {
        this.regla = regla;
    }

    /**
     * @return the siguientes
     */
    public List<String> getSiguientes() {
        return siguientes;
    }

    /**
     * @param siguientes the siguientes to set
     */
    public void setSiguientes(List<String> siguientes) {
        this.siguientes = siguientes;
    }

    @Override
    public String toString() {
        String res;
        res = "[";
        res += regla.get(0);
        res += "->";
        for (int i = 1; i < regla.size(); i++) {
            if (punto == i) {
                res += ". ";
            }
            res += regla.get(i) + " ";
        }
        if(punto == regla.size()) {
            res += ". ";
        }
        res += ",{";
        for (int i = 0; i < siguientes.size(); i++) {
            res += siguientes.get(i) + ", ";
        }
        res += "}]";
        return res;
    }

    @Override
    public boolean equals(Object o) {
        Token other;
        if (!(o instanceof Token)) {
            return false;
        }
        other = (Token) o;
        if (!other.regla.equals(regla)) {
            return false;
        }
        if (!other.siguientes.equals(siguientes)) {
            return false;
        }
        if (other.punto != punto) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.punto;
        hash = 97 * hash + (this.regla != null ? this.regla.hashCode() : 0);
        hash = 97 * hash + (this.siguientes != null ? this.siguientes.hashCode() : 0);
        return hash;
    }
}
