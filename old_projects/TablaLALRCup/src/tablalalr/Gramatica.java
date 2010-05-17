package tablalalr;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class Gramatica {

    private List<List<String>> reglas;
    private List<String> noTerminales;
    private List<String> terminales;
    private Map<String, List<String>> primero;
    private Map<Integer, Grupo> grupos_lalr;
    private Map<String, List<String>> siguiente;
    private List<Grupo> conjuntos;
    private List<Grupo> conjuntoslr1;
    private List<Transition> elementoslr1;

    public List<Grupo> getConjuntos() {
        return conjuntos;
    }

    public Map<Integer, Grupo> getGruposLALR() {
        return grupos_lalr;
    }

    public List<Grupo> getConjuntosLR1() {
        return conjuntoslr1;
    }

    public Gramatica(List<List<String>> reglas) {
        this.reglas = reglas;

        terminales = new ArrayList<String>();
        noTerminales = new ArrayList<String>();

        primero = new HashMap<String, List<String>>();
        siguiente = new HashMap<String, List<String>>();

        calcularNoTerminales();
        calcularTerminales();



        calcularPrimeros();
    /*
    conjuntos = elementos();

    for(Transition t : conjuntos) {
    System.out.println(t.getDest());
    }
     */

    }

    public List<String> getPrimero(String c) {
        return primero.get(c);
    }

    public List<String> getPrimero(List<String> cs) {
        List<String> res = new ArrayList<String>();
        List<String> tmp;

        for (String c : cs) {
            tmp = getPrimero(c);
            if (!tmp.contains("EPSILON")) {
                tmp.remove("EPSILON");
                return tmp;
            }
            tmp.remove("EPSILON");
            for (String r : tmp) {
                if (!res.contains(r)) {
                    res.add(r);
                }
            }
        }

        return res;
    }

    public List<String> getSiguiente(String c) {
        return siguiente.get(c);
    }

    public static String formatRegla(List<String> regla) {
        String res;

        res = regla.get(0);
        res += "->";
        for (int i = 1; i < regla.size(); i++) {
            res += regla.get(i);
        }

        return res;
    }

    public List<String> getTerminales() {
        return terminales;
    }

    public List<String> getNoTerminales() {
        return noTerminales;
    }

    public List<List<String>> getReglas() {
        return reglas;
    }

    public Grupo irA(List<Token> I, String X) {
        Grupo J = new Grupo();
        Token t;

        for (Token elemento : I) {
            if (elemento.getPunto() == elemento.getRegla().size()) {
                continue;
            }
            if (elemento.getRegla().get(elemento.getPunto()).equals(X)) {
                t = new Token();
                t.setPunto(elemento.getPunto() + 1);
                t.setRegla(elemento.getRegla());
                t.setSiguientes(elemento.getSiguientes());
                J.add(t);
            }
        }


        return cerradura(J);
    }


    public List<Transition> elementos() {
        List<Grupo> C = new ArrayList<Grupo>();
        List<Grupo> revisar = new ArrayList<Grupo>();
        List<Grupo> lalr_C = new ArrayList<Grupo>();
        List<Transition> result = new ArrayList<Transition>();
        List<Transition> lalr_result = new ArrayList<Transition>();
        Transition trans;
        List<String> simbolos;
        Grupo ir;
        Grupo aux;
        List<String> siguientes;
	int id = 0;

	grupos_lalr = new HashMap<Integer, Grupo>();

        siguientes = new ArrayList<String>();
        siguientes.add("$");

        Token t = new Token();
        t.setRegla(new ArrayList(reglas.get(0)));
        t.setSiguientes(siguientes);

        aux = new Grupo();
        aux.add(t);

	aux = cerradura(aux);
	aux.setNombre(String.valueOf(id++));

        C.add(aux);
        trans = new Transition();
        trans.setSource(null);
        trans.setDest(C.get(0));

        result.add(trans);

        simbolos = new ArrayList<String>(getNoTerminales());
        simbolos.addAll(getTerminales());

        for (int i = 0; i < C.size(); i++) {
            for (String X : simbolos) {
                ir = irA(C.get(i), X);
                if (ir.size() > 0) {
                    trans = new Transition();
                    trans.setSource(C.get(i));
                    trans.setDest(ir);
                    trans.setSymbol(X);
                    result.add(trans);
                    if (!C.contains(ir)) {
			ir.setNombre(String.valueOf(id++));
                        C.add(ir);
                    }
                }
            }
        }

	for(Grupo gi : C) {
		revisar.add(new Grupo(gi));
	}

	int i = 0;
	for(Grupo r : revisar) {
		r.compact();
		grupos_lalr.put(i++, r);
		lalr_C.add(r);
	}

	while(revisar.size() > 0) {
		aux = revisar.remove(0);
		for(i = 0; i < revisar.size(); i++) {
			if(aux.sameHeart(revisar.get(i))) {
				int u1, u2;
				u1 = lalr_C.indexOf(aux);
				u2 = lalr_C.indexOf(revisar.get(i));
				aux.unir(revisar.get(i));
				lalr_C.set(u1, aux);
				lalr_C.set(u2, aux);
				grupos_lalr.put(u1, aux);
				grupos_lalr.put(u2, aux);
			}
		}
	}

	lalr_C.clear();
	
	for(Grupo r : grupos_lalr.values()) {
		if(!lalr_C.contains(r))
			lalr_C.add(r);
	}

	for(Transition tr : result) {
		trans = new Transition();
		if(tr.getSource() == null) {
			trans.setSource(null);
		} else {
			trans.setSource(grupos_lalr.get(C.indexOf(tr.getSource())));
		}
		trans.setDest(grupos_lalr.get(C.indexOf(tr.getDest())));
		trans.setSymbol(tr.getSymbol());
		lalr_result.add(trans);
	}

        conjuntoslr1 = C;
        elementoslr1 = result;

        this.conjuntos = lalr_C;

        return lalr_result;
    }

    public Grupo cerradura(List<Token> I) {
        Grupo res = new Grupo();
        List<String> primeros;
        List<String> siguientes;
        Token elemento;
        Token tmp;

        res.addAll(I);

        for (int i = 0; i < res.size(); i++) {
            elemento = res.get(i);

            if (elemento.getPunto() == elemento.getRegla().size()) {
                continue;
            }

            for (List<String> produccion : reglas) {
                if (!produccion.get(0).equals(elemento.getRegla().
                        get(elemento.getPunto()))) {
                    continue;
                }

                primeros = new ArrayList<String>();
                for (int j = elemento.getPunto() + 1; j < elemento.getRegla().size(); j++) {
                    primeros.add(elemento.getRegla().get(j));
                }
                primeros.addAll(elemento.getSiguientes());

                primeros = getPrimero(primeros);

                for (String terminal : primeros) {
                    if (!terminales.contains(terminal)) {
                        continue;
                    }

                    tmp = new Token();
                    siguientes = new ArrayList<String>();
                    siguientes.add(terminal);

                    tmp.setPunto(1);
                    tmp.setRegla(produccion);
                    tmp.setSiguientes(siguientes);
                    if (!res.contains(tmp)) {
                        res.add(tmp);
                    }
                }
            }
        }

        return res;
    }

    public List<String> getSimbolos() {
        List<String> simbolos = new ArrayList<String>();
        simbolos.addAll(noTerminales);
        simbolos.addAll(terminales);
        return simbolos;
    }

    public void calcularPrimeros() {
        for (String simbolo : getSimbolos()) {
            primero.put(simbolo, calcPrimero(simbolo));
        }
        primero.put("EPSILON", calcPrimero("EPSILON"));
    }

    public void printPrimeros() {
        print(primero);
    }

    public void printSiguientes() {
        print(siguiente);
    }

    public void print(Map<String, List<String>> datos) {
        for (Entry<String, List<String>> e : datos.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

    public List<String> calcPrimero(String simb) {
        List<String> simbolos = new ArrayList<String>();
        List<String> simbolosTemp;
        List<String> simbolosVar;
        String val;
        boolean contieneEpsilon = false;

        if (simb == null) {
            return null;
        }

        if (terminales.contains(simb) || "EPSILON".equals(simb)) {
            simbolos.add(simb);
            return simbolos;
        }

        for (List<String> regla : reglas) {
            simbolosVar = new ArrayList<String>();

            if (!regla.get(0).equals(simb)) {
                continue;
            }


            for (int i = 1; i < regla.size(); i++) {
                val = regla.get(i);
                if (simb.equals(val)) {
                    contieneEpsilon = false;
                    break;
                }

                simbolosTemp = calcPrimero(val);
                for (String var : simbolosTemp) {
                    if (!simbolosVar.contains(var)) {
                        simbolosVar.add(var);
                    }
                }
                if (simbolosTemp.contains("EPSILON")) {
                    contieneEpsilon = true;
                } else {
                    contieneEpsilon = false;
                    break;
                }
            }
            if (!contieneEpsilon) {
                simbolosVar.remove("EPSILON");
            }
            for (String var : simbolosVar) {
                if (!simbolos.contains(var)) {
                    simbolos.add(var);
                }
            }
        }

        return simbolos;
    }

    public void print() {
        System.out.println("Terminales");
        printList(terminales);
        System.out.println("No Terminales");
        printList(noTerminales);
    }

    public void printList(List<String> simbolos) {
        for (String s : simbolos) {
            System.out.println(s);
        }
    }

    private void calcularNoTerminales() {
        String ladoIzq;
        for (List<String> regla : reglas) {
            if (regla.size() == 0) {
                continue;
            }
            ladoIzq = regla.get(0);
            if (!noTerminales.contains(ladoIzq)) {
                noTerminales.add(ladoIzq);
            }
        }
    }

    private void calcularTerminales() {
        String simb;
        for (List<String> regla : reglas) {
            if (regla.size() < 2) {
                continue;
            }
            for (int i = 1; i < regla.size(); i++) {
                simb = regla.get(i);
                if (!terminales.contains(simb) &&
                        !noTerminales.contains(simb)) {
                    terminales.add(simb);
                }
            }
        }
        terminales.add("$");
        terminales.remove("EPSILON");
    }
}

