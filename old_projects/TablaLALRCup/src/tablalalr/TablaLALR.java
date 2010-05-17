package tablalalr;

import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java_cup.runtime.Symbol;

public class TablaLALR {

    private Gramatica g;
    private List<List<String>> reglas;
    private String[][] tabla;
    private int filas;
    private int columnas;
    private String cadena;
    private List<Transition> C;
    private String[] headers;

    public TablaLALR(String reglasGramatica) throws Exception {
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
                       get(String.valueOf(source), t.getSymbol()).equals("s" + t.getDest().getNombre())) {
                        set(String.valueOf(source), t.getSymbol(), "s" + t.getDest().getNombre());
                    } else {
                        throw new Exception("Se encontro un conflicto entre s" + dest + " y " + get(String.valueOf(source), t.getSymbol()));
                    }
                }
            } else {
                if(get(String.valueOf(source), t.getSymbol()) == null ||
                   get(String.valueOf(source), t.getSymbol()).equals(t.getDest().getNombre())) {
                    set(String.valueOf(source), t.getSymbol(), t.getDest().getNombre());
                } else {
                    throw new Exception("Se encontro un conflicto entre " + dest + " y " + get(String.valueOf(source), t.getSymbol()));
                }
            }

        }

        String column;
	String row;
        int i = 0;
        for (List<Token> t : g.getConjuntosLR1()) {
            row = String.valueOf(
		g.getConjuntos().indexOf(g.getGruposLALR().get(i)));
            for (Token tk : t) {
                if (tk.getPunto() == tk.getRegla().size()) {
                    column = tk.getSiguientes().get(0);
                    if (g.getReglas().get(0).equals(tk.getRegla())) {
                        if(get(row, column) == null ||
                           get(row, column).equals("accept")) {
                            set(row, column, "accept");
                        } else {
                            throw new Exception("Se encontro un conflicto entre accept y " + get(row, column));
                        }
                    } else {
                        if(get(row, column) == null ||
                           get(row, column).equals("r" + g.getReglas().indexOf(tk.getRegla()))) {
                            set(row, column, "r" + g.getReglas().indexOf(tk.getRegla()));
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
