import java_cup.runtime.Symbol;

%%
%cup

SIMB=[A-Za-z0-9'@^~]
CERR_ESTR=\*
CERR_POSI=\+
INTERR=\?
OR=\|
FLECHA=->
PUNTO_COMA=;
PAR_IZQ=\(
PAR_DER=\)

%%

{PAR_IZQ} {
	return new Symbol(sym.PAR_IZQ);
}

{PAR_DER} {
	return new Symbol(sym.PAR_DER);
}

{SIMB} {
	return new Symbol(sym.SIMB, yytext());
}

{CERR_ESTR} {
	return new Symbol(sym.CERR_ESTR);
}

{CERR_POSI} {
	return new Symbol(sym.CERR_POSI);
}

{INTERR} {
	return new Symbol(sym.INTERR);
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

[\r\n\f\t] {}

