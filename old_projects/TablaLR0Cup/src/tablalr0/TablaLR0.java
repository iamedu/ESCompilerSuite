package tablalr0;

import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java_cup.runtime.Symbol;

public class TablaLR0 {

    private Gramatica g;
    private List<List<String>> reglas;
    private String[][] tabla;
    private int filas;
    private int columnas;
    private String cadena;
    private List<Transition> C;
    private String[] headers;

    public TablaLR0(String reglasGramatica) throws Exception {
        Symbol s = (new parser(new Yylex(new StringReader(reglasGramatica)))).parse();

        reglas = (List<List<String>>) s.value;

        g = new Gramatica(reglas);

        columnas = g.getTerminales().size() + g.getNoTerminales().size() - 1;

        headers = new String[columnas];
        int i;

        for (i = 0; i < g.getTerminales().size(); i++) {
            headers[i] = g.getTerminales().get(i);
        }

        for (int j = 0; j < g.getNoTerminales().size(); j++, i++) {
            if (reglas.get(0).get(0).equals(g.getNoTerminales().get(j))) {
                i--;
                continue;
            }
            headers[i] = g.getNoTerminales().get(j);
        }

        C = g.elementos();

        filas = g.getConjuntos().size();

        tabla = new String[filas][columnas];
        initTabla();
    }

    public String[] getHeaders() {
        return headers;
    }

    public int rowCount() {
        return filas;
    }

    public String getCadena() {
        return cadena;
    }

    public boolean analizar(String cad) {
        cadena = cad;

        return true;
    }

    private void initTabla() {
        int dest, source;
        List<String> siguiente;


        for (Transition t : C) {
            if (t.getSource() == null) {
                continue;
            }

            source = g.getConjuntos().indexOf(t.getSource());
            dest = g.getConjuntos().indexOf(t.getDest());

            if (g.getTerminales().contains(t.getSymbol())) {
                for (Token tk : t.getSource()) {
                    //if (tk.getPunto() != tk.getRegla().size()) {
                    set(String.valueOf(source), t.getSymbol(), "s" + dest);
                //}
                }
            } else {
                set(String.valueOf(source), t.getSymbol(), String.valueOf(dest));
            }
        }

        int i = 0;
        for (List<Token> t : g.getConjuntos()) {
            for (Token tk : t) {
                if (tk.getPunto() == tk.getRegla().size()) {
                    siguiente = g.getSiguiente(tk.getRegla().get(0));
                    dest = g.getReglas().indexOf(tk.getRegla());
                    for (String s : siguiente) {
                        if (dest == 0) {
                            set(String.valueOf(i), s, "accept");
                        } else {
                            set(String.valueOf(i), s, "r" + dest);
                        }
                    }
                }
            }
            i++;
        }

    }

    private void set(String f, String c, String value) {
        int i, j;
        List<String> tmpHeader = new ArrayList<String>();
        for (String h : headers) {
            tmpHeader.add(h);
        }
        i = Integer.parseInt(f);
        j = tmpHeader.indexOf(c);
        tabla[i][j] = value;
    }

    public String get(String f, String c) {
        int i, j;
        List<String> tmpHeader = new ArrayList<String>();
        for (String h : headers) {
            tmpHeader.add(h);
        }

        i = Integer.parseInt(f);
        j = tmpHeader.indexOf(c);

        return tabla[i][j];
    }

    public Gramatica getGramatica() {
        return g;
    }
}
