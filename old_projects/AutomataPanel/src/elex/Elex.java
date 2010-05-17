package elex;

import java.util.List;
import java.util.ArrayList;

public abstract class Elex {
	private int index;
	private List<Automata> a;

	private String cadena;
	protected String text;
	private List<Estado> estados;

	public static final int ERROR = -1;
	public static final int FIN   = 0;

	public Elex() {
		a = automata();
		cadena = null;
		text = null;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public abstract List<Automata> automata();

	private List<Estado> iniciales() {
		estados = new ArrayList<Estado>();

		for(Automata a : this.a) {
			estados.addAll(a.getInicialList());
		}

		return estados;
	}

	public void iniciar(String cadena) {
		this.cadena = cadena;

		index = 0;
	}

	private int getClase(List<Estado> estados) {
		for(Automata a : this.a)
			if(a.esAceptacion(estados))
				return a.getClase();
		
		return ERROR;
	}

	public YytokenElement getToken() {
		int lastToken = FIN;
		int lastIndex = index;
		int startIndex = index;
		int t = FIN;
		if(cadena == null)
			return new YytokenElement(ERROR, "");

		text = "";

		estados = iniciales();
	
		while(index < cadena.length()) {
			estados = Automata.irA(estados, cadena.charAt(index));
			text += cadena.charAt(index++);
			t = getClase(estados);
			if(t == ERROR && lastToken == FIN)
				return new YytokenElement(ERROR, "");
			if(t > 0) {
				if(t == lastToken) {
					lastIndex = index;
					continue;
				}
				if(lastToken == FIN) {
					lastToken = t;
					lastIndex = index;
					continue;
				}
				if(lastToken != t) {
					text = cadena.substring(startIndex,
                                                                lastIndex);
					index = lastIndex;
					return new YytokenElement(lastToken, text);
				}
			}
		}

		text = cadena.substring(startIndex, lastIndex);
		index = lastIndex;

		return new YytokenElement(lastToken, text);

	}

	public String getText() {
		return text;
	}

}

