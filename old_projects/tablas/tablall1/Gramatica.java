package tablall1;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class Gramatica {

    private List<List<String>> reglas;
    private List<String> noTerminales;
    private List<String> terminales;
    private String simboloInicial;
    private Map<String, List<String>> primero;
    private Map<String, List<String>> siguiente;

    public Gramatica(List<List<String>> reglas) {
        this.reglas = reglas;

        if (reglas.size() > 0) {
            simboloInicial = reglas.get(0).get(0);
        }

        terminales = new ArrayList<String>();
        noTerminales = new ArrayList<String>();

        primero = new HashMap<String, List<String>>();
        siguiente = new HashMap<String, List<String>>();

        calcularNoTerminales();
        calcularTerminales();

        calcularPrimeros();
        calcularSiguientes();

    }

    public List<String> getPrimero(String c) {
        return primero.get(c);
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

    public void calcularSiguientes() {
        for (String simbolo : noTerminales) {
            siguiente.put(simbolo, calcSiguiente(simbolo));
        }
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

    public List<String> calcSiguiente(String simb) {
        List<String> simbolos = new ArrayList<String>();
        List<String> sig;
        String val;
        String key;
        boolean calcSig;

        if (simboloInicial.equals(simb)) {
            simbolos.add("$");
        }

        for (List<String> regla : reglas) {
            key = regla.get(0);
            for (int i = 1; i < regla.size(); i++) {
                val = regla.get(i);
                calcSig = false;
                if (val.equals(simb)) {
                    if (i == regla.size() - 1) {
                        calcSig = true;
                    } else {
                        sig = new ArrayList<String>(
                                primero.get(
                                regla.get(i + 1)));
                        if (sig.contains("EPSILON")) {
                            calcSig = true;
                            sig.remove("EPSILON");
                        }
                        for (String t : sig) {
                            if (!simbolos.contains(t)) {
                                simbolos.add(t);
                            }
                        }
                    }
                    if (calcSig && !key.equals(simb)) {
                        sig = calcSiguiente(key);
                        for (String t : sig) {
                            if (!simbolos.contains(t)) {
                                simbolos.add(t);
                            }
                        }
                    }
                }
            }
        }

        return simbolos;
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
        terminales.remove("EPSILON");
    }
}

