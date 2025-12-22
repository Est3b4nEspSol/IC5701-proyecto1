%%
%class Lexer
%unicode
%cup
%line
%column

%%
[0-9]+     { return new java_cup.runtime.Symbol(sym.NUM); }
"+"        { return new java_cup.runtime.Symbol(sym.PLUS); }
[ \t\r\n]+ { /* ignorar espacios */ }
