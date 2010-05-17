package elex;

public class Estado implements Comparable<Estado> {
	private int id;

	private static int cuenta;
	
	private Transicion transicion1;
	private Transicion transicion2;

    private boolean arriba;

    private Estado padre;

    private int x;
    private int y;

    private int width;
    private int height;

	static {
		cuenta = 0;
	}

    public int getId() {
        return id;
    }

	public Estado() {
		id = Estado.cuenta++;
		transicion1 = null;
		transicion2 = null;
        x = -1000;
        y = -1000;
	}

	public void setTransicion1(Transicion transicion1) {
		this.transicion1 = transicion1;
	}

	public void setTransicion2(Transicion transicion2) {
		this.transicion2 = transicion2;
	}

	public Transicion getTransicion1() {
		return transicion1;
	}

	public Transicion getTransicion2() {
		return transicion2;
	}

	public int compareTo(Estado other) {
		return id - other.id;
	}

	public boolean equals(Object other) {
		Estado e = (Estado)other;
		return compareTo(e) == 0;
	}

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the padre
     */
    public Estado getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Estado padre) {
        this.padre = padre;
    }

    /**
     * @return the arriba
     */
    public boolean isArriba() {
        return arriba;
    }

    /**
     * @param arriba the arriba to set
     */
    public void setArriba(boolean arriba) {
        this.arriba = arriba;
    }


}

