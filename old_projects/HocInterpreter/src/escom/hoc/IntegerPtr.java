package escom.hoc;

import escom.hoc.interpreter.PCListener;

public class IntegerPtr {

    private int num;
    private PCListener listener;

    public IntegerPtr(int num) {
        this.num = num;
    }

    public IntegerPtr(int num, PCListener listener) {
        this.num = num;
        this.listener = listener;
        /*
        if(listener != null) {
            listener.pcChanged(this);
        }
         */
    }


    public IntegerPtr() {
        num = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (listener != null) {
            listener.pcChanged(this);
        }
    }

    public int incr() {
        num++;
        if (listener != null) {
            listener.pcChanged(this);
        }
        return num - 1;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    /**
     * @return the listener
     */
    public PCListener getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(PCListener listener) {
        this.listener = listener;
    }
}
