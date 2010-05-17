package tablalr1;

import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java_cup.runtime.Symbol;

public class TablaLR1 {

    private Gramatica g;
    private List<List<String>> reglas;
    private String[][] tabla;
    private int filas;
    private int columnas;
    private String cadena;
    private List<Transition> C;
    private String[] headers;

    public TablaLR1(String reglasGramatica) throws Exception {
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

    private void initTabla() throws Exception {
        int dest, source;

        for (Transition t : C) {
            if (t.getSource() == null) {
                continue;
            }

            source = g.getConjuntos().indexOf(t.getSource());
            dest = g.getConjuntos().indexOf(t.getDest());

            if (g.getTerminales().contains(t.getSymbol())) {
                for (Token tk : t.getSource()) {
                    if (tk.getPunto() == tk.getRegla().size()) {
                        continue;
                    }
                    if(get(String.valueOf(source), t.getSymbol()) == null ||
                       get(String.valueOf(source), t.getSymbol()).equals("s" + dest)) {
                        set(String.valueOf(source), t.getSymbol(), "s" + dest);
                    } else {
                        throw new Exception("Se encontro un conflicto entre s" + dest + " y " + get(String.valueOf(source), t.getSymbol()));
                    }
                }
            } else {
                if(get(String.valueOf(source), t.getSymbol()) == null ||
                   get(String.valueOf(source), t.getSymbol()).equals(String.valueOf(dest))) {
                    set(String.valueOf(source), t.getSymbol(), String.valueOf(dest));
                } else {
                    throw new Exception("Se encontro un conflicto entre " + dest + " y " + get(String.valueOf(source), t.getSymbol()));
                }
            }

        }

        String column;
        int i = 0;
        for (List<Token> t : g.getConjuntos()) {
            for (Token tk : t) {
                if (tk.getPunto() == tk.getRegla().size()) {
                    column = tk.getSiguientes().get(0);
                    if (g.getReglas().get(0).equals(tk.getRegla())) {
                        if(get(String.valueOf(i), column) == null ||
                           get(String.valueOf(i), column).equals("accept")) {
                            set(String.valueOf(i), column, "accept");
                        } else {
                            throw new Exception("Se encontro un conflicto entre accept y " + get(String.valueOf(i), column));
                        }
                    } else {
                        if(get(String.valueOf(i), column) == null ||
                           get(String.valueOf(i), column).equals("r" + g.getReglas().indexOf(tk.getRegla()))) {
                            set(String.valueOf(i), column, "r" + g.getReglas().indexOf(tk.getRegla()));
                        } else {
                            throw new Exception("Se encontro un conflicto entre r" +  g.getReglas().indexOf(tk.getRegla()) + " y " + get(String.valueOf(i), column));
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
