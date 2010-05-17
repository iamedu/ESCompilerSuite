import java_cup.runtime.Symbol;

%%
%cup
%%

"default" { return new Symbol(sym.DEFAULT); }
"break" { return new Symbol(sym.BREAK); }
"case" { return new Symbol(sym.CASE); }
"switch" { return new Symbol(sym.SWITCH); }
"while" { return new Symbol(sym.WHILE); }
"for" { return new Symbol(sym.FOR); }
"if" { return new Symbol(sym.IF); }
"else" { return new Symbol(sym.ELSE); }
"print" { return new Symbol(sym.PRINT); }
"||" { return new Symbol(sym.OR); }
"&&" { return new Symbol(sym.AND); }
"==" { return new Symbol(sym.EQ); }
"!=" { return new Symbol(sym.NE); }
"!=" { return new Symbol(sym.NOT); }
"<=" { return new Symbol(sym.LE); }
"<" { return new Symbol(sym.LT); }
">=" { return new Symbol(sym.GE); }
">" { return new Symbol(sym.GT); }
"=" { return new Symbol(sym.ASGN); }
"+" { return new Symbol(sym.ADD); }
"-" { return new Symbol(sym.SUB); }
"*" { return new Symbol(sym.MUL); }
"/" { return new Symbol(sym.DIV); }
"(" { return new Symbol(sym.LPAR); }
")" { return new Symbol(sym.RPAR); }
"{" { return new Symbol(sym.LBRA); }
"}" { return new Symbol(sym.RBRA); }
"^" { return new Symbol(sym.POW); }
";" { return new Symbol(sym.PYC); }
[0-9]+(\.[0-9]+)? { return new Symbol(sym.NUM, yytext()); }
[a-zA-Z]+ { return new Symbol(sym.VAR, yytext()); }
\n { return new Symbol(sym.NL); }
[\t\r\f ] {  }
