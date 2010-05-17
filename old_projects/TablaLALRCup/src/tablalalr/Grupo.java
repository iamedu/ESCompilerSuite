package tablalalr;

import java.util.ArrayList;

public class Grupo extends ArrayList<Token> {
	private String nombre;

	public Grupo(Grupo other) {
		for(Token t : other) {
			add(new Token(t));
		}
		this.nombre = other.nombre;
	}

	public Grupo() {
		super();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void compact() {
		ArrayList<Token> revisar = new ArrayList<Token>();
		revisar.addAll(this);
		Token aux;
		while(revisar.size() > 0) {
			aux = revisar.remove(0);
			for(int i = 0; i < revisar.size(); i++) {
				if(aux.getRegla().equals(revisar.get(i).getRegla()) && aux.getPunto() == revisar.get(i).getPunto()) {
					for(String sig : revisar.get(i).
							getSiguientes()) {
						if(!aux.getSiguientes().
							contains(sig)) {
							aux.getSiguientes().add(sig);
						}
					}
					remove(revisar.remove(i--));
				}
			}
		}
	}

	public Grupo unir(Grupo other) {
		setNombre(getNombre() + other.getNombre());
		for(Token t1 : this) {
			for(Token t2 : other) {
				if(t1.getRegla().equals(t2.getRegla()) &&
				   t1.getPunto() == t1.getPunto()) {
					for(String s : t2.getSiguientes()) {
						if(!t1.getSiguientes().contains(s)) {
							t1.getSiguientes().add(s);
						}
					}
				}
			}
		}
		return this;
	}

	public boolean sameHeart(Grupo other) {
		boolean isIn;

		if(other == this)
			return false;

		if(size() != other.size()) {
			return false;
		}

		for(Token t1 : other) {
			isIn = false;
			for(Token t2 : this) {
				if(t1.getRegla().equals(t2.getRegla()) &&
				   t1.getPunto() == t2.getPunto()) {
					isIn = true;
					break;
				}
			}
			if(!isIn)
				return false;
		}

		return true;
	}

}

