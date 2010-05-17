package tablall1;

import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java_cup.runtime.Symbol;

public class TablaLL1 {

    private Gramatica g;
    private List<List<String>> reglas;
    private Regla[][] tabla;
    private int filas;
    private int columnas;
    private Stack<String> pila;
    private String cadena;
    private String simbolo;
    private Regla r;

    public TablaLL1(String reglasGramatica) throws Exception {
        Symbol s = (new parser(new Yylex(new StringReader(reglasGramatica)))).parse();

        reglas = (List<List<String>>) s.value;

        g = new Gramatica(reglas);

        System.out.println("Terminal");
        for(String terminal : g.getTerminales()) {
            System.out.println(terminal);
        }

        filas = g.getNoTerminales().size();
        columnas = g.getTerminales().size() + 1;

        //g.printPrimeros();

        tabla = new Regla[filas][columnas];//new List<String>[filas][columnas];
        initTabla();
    }

    public String getPila() {
        String result = "";
        for (String val : pila) {
            result += val;
        }
        return result;
    }

    public String getCadena() {
        return cadena;
    }

    public String getAccion() {
        if (r == null) {
            return "";
        }
        if (r.getValor().get(0).equals("pop()")) {
            return "pop()";
        }
        if (r.getValor().get(0).equals("accept()")) {
            return "accept()";
        }
        return Gramatica.formatRegla(r.getValor());
    }

    public boolean step() {
        String next;

        if (cadena.length() == 0) {
            simbolo = "$";
        } else {
            simbolo = null;
        }

        for (String nt : g.getTerminales()) {
            if (cadena.startsWith(nt)) {
                simbolo = nt;
                break;
            }
        }

        if (simbolo == null) {
            return false;
        }

        next = pila.pop();
        if (next.equals(simbolo)) {
            List<String> tmp = new ArrayList<String>();
            if (!"$".equals(simbolo)) {
                cadena = cadena.substring(simbolo.length());
            }
            if ("$".equals(simbolo)) {
                tmp.add("accept()");
            } else {
                tmp.add("pop()");
            }
            r = new Regla(tmp);
            return true;
        }

        r = get(next, simbolo);
        if (r == null) {
            return false;
        }

        for (int i = r.getValor().size() - 1; i > 0; i--) {
            if (!"EPSILON".equals(r.getValor().get(i))) {
                pila.push(r.getValor().get(i));
            }
        }

        return true;
    }

    public boolean analizar(String cad) {


        cadena = cad;

        pila = new Stack<String>();
        pila.push("$");
        pila.push(reglas.get(0).get(0));

        /*
        while (pila.size() > 0) {
        next = pila.pop();
        if (next.equals(simbolo)) {
        if (!"$".equals(simbolo)) {
        cadena = cadena.substring(simbolo.length());
        }
        continue;
        }
        r = get(next, simbolo);
        if (r == null) {
        return false;
        }
        for (int i = r.getValor().size() - 1; i > 0; i--) {
        if (!"EPSILON".equals(r.getValor().get(i))) {
        pila.push(r.getValor().get(i));
        System.out.print(r.getValor().get(i) + " ");
        }
        }
        System.out.println();
        }
         */

        return true;
    }


    /*
     * public boolean analizar(String cadena) {
    String simbolo;
    String next;
    Regla r;

    pila = new Stack<String>();
    pila.push("$");
    pila.push(reglas.get(0).get(0));

    while (pila.size() > 0) {
    if (cadena.length() == 0) {
    simbolo = "$";
    } else {
    simbolo = null;
    }
    for (String nt : g.getTerminales()) {

    if (cadena.startsWith(nt)) {
    simbolo = nt;

    break;
    }
    }
    if(simbolo == null) {
    return false;
    }
    next = pila.pop();
    if (next.equals(simbolo)) {
    if (!"$".equals(simbolo)) {
    cadena = cadena.substring(simbolo.length());
    }
    continue;
    }
    r = get(next, simbolo);
    if (r == null) {
    return false;
    }
    for (int i = r.getValor().size() - 1; i > 0; i--) {
    if (!"EPSILON".equals(r.getValor().get(i))) {
    pila.push(r.getValor().get(i));
    System.out.print(r.getValor().get(i) + " ");
    }
    }
    System.out.println();
    }

    return true;
    }
     */
    private void initTabla() {
        String val;
        String f;
        List<String> first;
        List<String> next;
        for (List<String> regla : reglas) {
            f = regla.get(0);
            val = regla.get(1);
            //formattedRegla = g.formatRegla(regla);
            r = new Regla(regla);
            first = g.getPrimero(f);
            for (String c : first) {
                if (g.getTerminales().contains(c)) {
                    if (get(f, c) == null) {
                        if (g.getTerminales().contains(val)) {
                            if (val.equals(c)) {
                                set(f, c, r);
                            }
                        } else {
                            set(f, c, r);
                        }
                    }
                }
            }
            if (g.getPrimero(val).contains("EPSILON")) {
                next = g.getSiguiente(f);
                for (String c : next) {
                    set(f, c, r);
                }
                if (next.contains("$")) {
                    set(f, "$", r);
                }
            }
        }
    }

    private void set(String f, String c, Regla value) {
        int i, j;
        i = g.getNoTerminales().indexOf(f);
        if ("$".equals(c)) {
            j = columnas - 1;
        } else {
            j = g.getTerminales().indexOf(c);
        }
        tabla[i][j] = value;
    }

    public Regla get(String f, String c) {
        int i, j;
        i = g.getNoTerminales().indexOf(f);
        if ("$".equals(c)) {
            j = columnas - 1;
        } else {
            j = g.getTerminales().indexOf(c);
        }

        if (i < 0 || j < 0) {
            System.out.println("FC " + f + " " + c);
        }

        return tabla[i][j];
    }

    private void printReglas(List<List<String>> reglas) {
        for (List<String> regla : reglas) {

            System.out.print(regla.get(0) + "->");
            for (int i = 1; i < regla.size(); i++) {
                System.out.print(regla.get(i));
            }
            System.out.println();
        }
    }

    public void print() {
        System.out.print("NT,");
        for (String s : g.getTerminales()) {
            System.out.print(s + ",");
        }
        System.out.println("$");
        for (String f : g.getNoTerminales()) {
            System.out.print(f + ",");
            for (String c : g.getTerminales()) {
                if (get(f, c) != null) {
                    System.out.print(get(f, c) + ",");
                } else {
                    System.out.print("-,");
                }

            }
            if (get(f, "$") != null) {
                System.out.println(get(f, "$"));
            } else {
                System.out.println("-");
            }
        }
    }

    public Gramatica getGramatica() {
        return g;
    }

    /*
    public static void main(String []args) {
    try {
    (new TablaLL1()).print();
    } catch(Exception ex) {
    System.out.println("Gramatica Incorrecta");
    ex.printStackTrace();
    }
    }
     */
}
