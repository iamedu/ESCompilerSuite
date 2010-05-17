import java_cup.runtime.Symbol;

%%
%cup

SIMB=([A-Za-z0-9'@^~]+|\+|\-|\*|\?|/|\(|\)|EPSILON)" "?
OR=\|
FLECHA=->
PUNTO_COMA=;

%%

{SIMB} {
	return new Symbol(sym.SIMB, yytext().trim());
}

{OR} {
	return new Symbol(sym.OR);
}

{FLECHA} {
	return new Symbol(sym.FLECHA);
}

{PUNTO_COMA} {
	return new Symbol(sym.PUNTO_COMA);
}

[\r\n\f\t" "] {}

