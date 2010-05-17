/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.hoc.interpreter;

import escom.hoc.IntegerPtr;

/*
 *
 * @author iamedu
 * 
 */
public class PCListener {

    private int lastNum;
    private IntegerPtr interpreterPtr;
    private IntegerPtr currentPtr;
    private boolean cambio;

    public PCListener(IntegerPtr interpreterPtr) {
        this.interpreterPtr = interpreterPtr;
        lastNum = -1;
        cambio = false;
    }

    public boolean getCambio() {
        boolean cambioLocal = this.cambio;
        this.cambio = false;
        return cambioLocal;
    }

    public void pcChanged(IntegerPtr ptr) {
        setCurrentPtr(ptr);
        if(lastNum == ptr.getNum()) {
            cambio = false;
            return;
        }
        if(getCurrentPtr() == getInterpreterPtr()) {
            globalChanged(ptr);
        } else {
            localChanged(ptr);
        }
        cambio = true;
    }

    protected void globalChanged(IntegerPtr ptr) {
        System.out.println("PC Global en " + ptr.getNum());
    }

    protected void localChanged(IntegerPtr ptr) {
        System.out.println("PC Local en " + ptr.getNum());
    }

    /**
     * @return the interpreterPtr
     */
    public IntegerPtr getInterpreterPtr() {
        return interpreterPtr;
    }

    /**
     * @param interpreterPtr the interpreterPtr to set
     */
    public void setInterpreterPtr(IntegerPtr interpreterPtr) {
        this.interpreterPtr = interpreterPtr;
    }

    /**
     * @return the currentPtr
     */
    public IntegerPtr getCurrentPtr() {
        return currentPtr;
    }

    /**
     * @param currentPtr the currentPtr to set
     */
    public void setCurrentPtr(IntegerPtr currentPtr) {
        this.currentPtr = currentPtr;
    }

}
