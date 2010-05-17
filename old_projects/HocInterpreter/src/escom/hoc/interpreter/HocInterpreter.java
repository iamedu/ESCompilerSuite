/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escom.hoc.interpreter;

import com.trolltech.qt.gui.QApplication;
import escom.hoc.exceptions.HocException;
import escom.hoc.functions.*;
import escom.hoc.symbol.Datum;
import escom.hoc.symbol.Function;
import escom.hoc.IntegerPtr;
import escom.hoc.OutputPrinter;
import escom.hoc.Utils;
import escom.hoc.gui.MainWindow;
import escom.hoc.parser.Yylex;
import escom.hoc.parser.parser;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author iamedu
 */
public class HocInterpreter {

    private List<String> program;
    private HocStack stack;
    private Map<String, Function> functions;
    private Map<String, Datum> symbols;
    private boolean executing;
    private IntegerPtr pc;
    private parser p;
    private OutputPrinter printer;
    private boolean continuar;

    public HocInterpreter() {
        program = new ArrayList<String>();
        stack = new HocStack();
        symbols = new HashMap<String, Datum>();
        initFunctions();
        pc = new IntegerPtr();
        printer = new OutputPrinter();

    }

    public void setPrinter(OutputPrinter printer) {
        this.printer = printer;
    }

    public OutputPrinter getPrinter() {
        return printer;
    }

    public HocStack getStack() {
        return stack;
    }

    public Map<String, Datum> getSymbols() {
        return symbols;
    }

    public List<String> getProgram() {
        return program;
    }

    public void initFunctions() {
        functions = new HashMap<String, Function>();

        functions.put("add", new Add(this));
        functions.put("asgn", new Asgn(this));
        functions.put("div", new Div(this));
        functions.put("mul", new Mul(this));
        functions.put("negate", new Negate(this));
        functions.put("pow", new Pow(this));
        functions.put("print", new Print(this));
        functions.put("sub", new Sub(this));
        functions.put("constpush", new ConstPush(this));
        functions.put("varpush", new VarPush(this));
        functions.put("pop", new Pop(this));
        functions.put("stop", new Stop(this));
        functions.put("prexpr", new Print(this));
        functions.put("or", new Or(this));
        functions.put("and", new And(this));
        functions.put("eq", new Eq(this));
        functions.put("ne", new Ne(this));
        functions.put("not", new Not(this));
        functions.put("le", new Le(this));
        functions.put("lt", new Lt(this));
        functions.put("ge", new Ge(this));
        functions.put("gt", new Gt(this));
        functions.put("ifcode", new If(this));
        functions.put("whilecode", new While(this));
        functions.put("forcode", new For(this));
        functions.put("switchcode", new Switch(this));
    }

    public IntegerPtr getPc() {
        return pc;
    }

    public void resumeExecution() {
        executing = true;
    }

    public void stopExecution() {
        executing = false;
    }

    public void execute() {
        execute(pc);
    }

    public boolean load(String str) throws Exception {
        List<String> insts;
        str += "\n";
        p = new parser(new Yylex(new StringReader(str)));
        insts = (List<String>) p.parse().value;
        //Utils.addAll(program, insts);
        program = insts;
        return insts.size() > 0;
    }

    public void loadExec(String str) throws Exception {
        if (load(str)) {
            execute();
        }
    }

    public void executeStep() {
        executeStep(pc);
    }

    public void executeStep(IntegerPtr pc) {
        Function func;
        func = functions.get(program.get(pc.incr()));
        if (func == null) {
            printer.printf("function " + program.get(pc.getNum() - 1) + " not found");
            return;
        }
        try {
            func.execute(pc);
        } catch (HocException ex) {
            printer.printf(ex.getMessage() + "\n");
        }
    }

    public void executeAll() {
        while (pc.getNum() < program.size()) {
            execute();
        }
    }

    public void execute(IntegerPtr pc) {
        executing = true;
        while (executing) {
            executeStep(pc);
        }
    }

    /**
     * @param args the command line arguments
    .getNum()*/
    public static void main(String[] args) throws Exception {

        QApplication.initialize(args);
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        QApplication.exec();




    /*
    HocInterpreter interpreter = new HocInterpreter();
    Scanner scan = new Scanner(System.in);
    List insts;
    String line;
    parser p;

    while (true) {
    System.out.print("(hoc5)>> ");
    line = scan.nextLine() + "\n";
    p = new parser(new Yylex(new StringReader(line)));
    insts = (List) p.parse().value;
    System.out.println(insts);
    Utils.addAll(interpreter.getProgram(), insts);
    if (insts.size() > 0) {
    interpreter.execute();
    }
    }
     */


    }

}
