package elex;

public class Transicion {
	public static final char EPSILON = 0;
	
	private char clave;
	private Estado estado;

	public Transicion(char clave, Estado siguiente) {
		setClave(clave);
		setEstado(siguiente);
	}

	public void setClave(char clave) {
		this.clave = clave;
	}

	public char getClave() {
		return clave;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Estado getEstado() {
		return estado;
	}

}

