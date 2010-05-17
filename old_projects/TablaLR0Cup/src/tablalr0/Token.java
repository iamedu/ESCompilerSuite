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
public class Token {

    private int punto;
    private List<String> regla;

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
        res += "]";
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
        return hash;
    }
}
