package elex;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Automata {
	
	private Estado inicial;
	private Estado ultimo;
	private int clase;

	public Automata() {
		inicial = null;
		ultimo  = null;
	}

	public int getClase() {
		return clase;
	}

	public void setClase(int clase) {
		this.clase = clase;
	}

	public boolean esAceptacion(List<Estado> edos) {
		return edos.contains(ultimo);
	}

	public Automata(String clave) {
		automataBasico(clave.charAt(0));
	}

	public Automata(char clave) {
		automataBasico(clave);
	}

	public List<Estado> getInicialList() {
		List<Estado> result = new ArrayList<Estado>();
		result.add(inicial);
        result = getCerraduraEpsilon(result);
		return result;
	}

	public static List<Estado> mover(List<Estado> revisar, char siguiente) {
		List<Estado> nuevos   = new ArrayList<Estado>();
		List<Estado> actuales = new ArrayList<Estado>();
		Transicion t1;
		Transicion t2;

		actuales = getCerraduraEpsilon(revisar);

		for(Estado e : actuales) {
			t1 = e.getTransicion1();
			t2 = e.getTransicion2();

			if(t1 != null && t1.getClave() == siguiente)
				nuevos.add(t1.getEstado());
			if(t2 != null && t2.getClave() == siguiente)
				nuevos.add(t2.getEstado());

		}

		

		return nuevos;
	}

	public static List<Estado> irA(List<Estado> revisar, char siguiente) {
		return getCerraduraEpsilon(mover(revisar, siguiente));
	}

	public static List<Estado> getCerraduraEpsilon(List<Estado> revisar) {
		List<Estado> cerradura = new ArrayList<Estado>();
		List<Estado> revisados = new ArrayList<Estado>();

		Estado e;
		Transicion t1;
		Transicion t2;
		
		while(revisar.size() > 0) {
			e = revisar.remove(0);
			revisados.add(e);
			if(!cerradura.contains(e)) {
				cerradura.add(e);
			}
			t1 = e.getTransicion1();
			t2 = e.getTransicion2();

			if(t1 != null && t1.getClave() == Transicion.EPSILON &&
                           !revisados.contains(t1.getEstado()))
				revisar.add(t1.getEstado());
			
			if(t2 != null && t2.getClave() == Transicion.EPSILON &&
                           !revisados.contains(t2.getEstado()))
				revisar.add(t2.getEstado());
		}

		return cerradura;
	}

	public void automataBasico(char clave) {
		inicial = new Estado();
		ultimo  = new Estado();
		inicial.setTransicion1(new Transicion(clave, ultimo));
	}

	public Automata concat(Automata o) {
		ultimo.setTransicion1(o.inicial.getTransicion1());
		ultimo.setTransicion2(o.inicial.getTransicion2());
		ultimo = o.ultimo;
		o.inicial = null;
		return this;
	}

	public Automata unir(Automata o) {
		Estado inicial;
		Estado ultimo;

		inicial = new Estado();
		ultimo  = new Estado();

		inicial.setTransicion1(new Transicion(Transicion.EPSILON, 
                                       this.inicial));
		inicial.setTransicion2(new Transicion(Transicion.EPSILON, 
                                       o.inicial));
		this.ultimo.setTransicion1(new Transicion(Transicion.EPSILON,
                                           ultimo));
		o.ultimo.setTransicion1(new Transicion(Transicion.EPSILON,
                                           ultimo));

		this.inicial = inicial;
		this.ultimo  = ultimo;


		o.inicial = null;
		return this;
	}

	public boolean analizar(String cadena) {
		List<Estado> estados = getInicialList();

                for(int i = 0; i < cadena.length(); i++)
			estados = irA(estados, cadena.charAt(i));

		return esAceptacion(estados);

	}

	public Automata cerraduraPositiva() {
		Estado inicial;
		Estado ultimo;

		inicial = new Estado();
		ultimo  = new Estado();

		inicial.setTransicion1(new Transicion(Transicion.EPSILON, 
				       this.inicial));
		this.ultimo.setTransicion1(new Transicion(Transicion.EPSILON, 
                                       this.inicial));
		this.ultimo.setTransicion2(new Transicion(Transicion.EPSILON,
				       ultimo));
		this.inicial = inicial;
		this.ultimo = ultimo;
		return this;
	}

	public Automata cerraduraEstrella() {
		cerraduraPositiva();
		inicial.setTransicion2(new Transicion(Transicion.EPSILON,
				       ultimo));
		return this;
	}

	public Automata interrogacion() {
		Estado inicialLocal;
		Estado ultimoLocal;

		inicialLocal = new Estado();
		ultimoLocal  = new Estado();

		inicialLocal.setTransicion1(new Transicion(Transicion.EPSILON,
				       this.inicial));

		inicialLocal.setTransicion2(new Transicion(Transicion.EPSILON,
				       ultimoLocal));

		this.ultimo.setTransicion1(new Transicion(Transicion.EPSILON,
					   ultimoLocal));


		this.inicial = inicialLocal;
		this.ultimo  = ultimoLocal;

		return this;
	}

}


