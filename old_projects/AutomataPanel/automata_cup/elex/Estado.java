package elex;

public class Estado implements Comparable<Estado> {
	private int id;

	private static int cuenta;
	
	private Transicion transicion1;
	private Transicion transicion2;

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


}

