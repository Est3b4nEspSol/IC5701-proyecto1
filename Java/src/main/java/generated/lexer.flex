package generated;
import java_cup.runtime.*;

%%
%class Lexer
%public
%unicode
%cup
%line
%column

%state STRING_CONTENT
%state MULTI_COMMENT

%{
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

    private void reportError(String message) {
        System.err.println("ERROR LÉXICO (Línea " + (yyline + 1) +
                          ", Columna " + (yycolumn + 1) + "): " + message);
    }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/* Literales */
digit = [0-9]
digitoNoCero = [1-9]
sign = [-]?
DecIntegerLiteral = {sign}({digit}+|{digit}+"."+{digit}+)

/* Identificadores */
Identifier = [:jletter:] ([:jletterdigit:]|"_")*

/* Comentarios */
LineComment = "|" [^\r\n]*
MultiCommentStart = "є"
MultiCommentEnd = "э"

%%

/* Estado de contenido de string */
<STRING_CONTENT> {
    \" {
        yybegin(YYINITIAL);
        return symbol(sym.STRING_LITERAL, string.toString());
    }

    /* Caracteres normales */
    [^\n\r\"\\]+ {
        string.append(yytext());
    }

    /* Secuencias de escape válidas */
    \\\" { string.append('\"'); }
    \\\\ { string.append('\\'); }
    \\t  { string.append('\t'); }
    \\n  { string.append('\n'); }
    \\r  { string.append('\r'); }

    /* ERROR: Newline no escapado */
    \n|\r|\r\n {
        reportError("Newline no escapado en cadena de texto");
        yybegin(YYINITIAL);
        return symbol(sym.ERROR);
    }

    /* ERROR: Secuencia de escape inválida */
    \\. {
        reportError("Secuencia de escape inválida '\\" + yytext().charAt(1) +
                   "' en cadena de texto");
        yybegin(YYINITIAL);
        return symbol(sym.ERROR);
    }

    /* ERROR: Cadena no cerrada */
    <<EOF>> {
        reportError("Cadena de texto no cerrada");
        yybegin(YYINITIAL);
        return symbol(sym.ERROR, "Cadena no cerrada");
    }
}

/* Estado Comentario Multilinea */
<MULTI_COMMENT> {
    {MultiCommentEnd} {
        yybegin(YYINITIAL);
    }

    [^э]+ {
        /* Ignorar contenido de comentario */
    }

    /* ERROR: Comentario no cerrado */
    <<EOF>> {
        reportError("Comentario multilínea no cerrado");
        yybegin(YYINITIAL);
        return symbol(sym.ERROR);
    }
}

/* Estado Inicial */
<YYINITIAL> {
    /* Palabras Reservadas  */
    "world"     { return symbol(sym.WORLD); }
    "local"     { return symbol(sym.LOCAL); }
    "decide"    { return symbol(sym.DECIDE); }
    "of"        { return symbol(sym.OF); }
    "else"      { return symbol(sym.ELSE); }
    "end"       { return symbol(sym.END); }
    "loop"      { return symbol(sym.LOOP); }
    "exit"      { return symbol(sym.EXIT); }
    "when"      { return symbol(sym.WHEN); }
    "for"       { return symbol(sym.FOR); }
    "gift"      { return symbol(sym.GIFT); }
    "navidad"   { return symbol(sym.NAVIDAD); }
    "coal"      { return symbol(sym.COAL); }
    "show"      { return symbol(sym.SHOW); }
    "get"       { return symbol(sym.GET); }
    "return"    { return symbol(sym.RETURN); }
    "break"     { return symbol(sym.BREAK); }
    "endl"      { return symbol(sym.ENDL); }

    /* Tipos de Datos */
    "int"       { return symbol(sym.INT); }
    "float"     { return symbol(sym.FLOAT); }
    "bool"      { return symbol(sym.BOOL); }
    "char"      { return symbol(sym.CHAR); }
    "string"    { return symbol(sym.STRING); }

    /* Literales */
    {DecIntegerLiteral} {
        try {
            if (yytext().contains(".")) {
                return symbol(sym.LITERAL_FLOAT, Float.parseFloat(yytext()));
            } else {
                return symbol(sym.LITERAL_INTEGER, Integer.parseInt(yytext()));
            }
        } catch (NumberFormatException e) {
            reportError("Número mal formado '" + yytext() + "'");
            return symbol(sym.ERROR);
        }
    }

    \" {
        string.setLength(0);
        yybegin(STRING_CONTENT);
    }

    "true"      { return symbol(sym.BOOL_LITERAL, Boolean.TRUE); }
    "false"     { return symbol(sym.BOOL_LITERAL, Boolean.FALSE); }

    /* Identificadores */
    {Identifier} {
        return symbol(sym.IDENTIFIER, yytext());
    }

    /* Operadores Aritmeticos */
    "+"         { return symbol(sym.PLUS); }
    "-" [0-9]   { /* Capturado por DecIntegerLiteral  */ }
    "-"         { return symbol(sym.MINUS); }
    "*"         { return symbol(sym.MULTIPLY); }
    "/"         { return symbol(sym.DIVIDE); }
    "//"        { return symbol(sym.INT_DIV); }
    "%"         { return symbol(sym.MODULO); }
    "^"         { return symbol(sym.POWER); }
    "++"        { return symbol(sym.INCREMENT); }
    "--"        { return symbol(sym.DECREMENT); }

    /* Operadores Relacionales */
    "<"         { return symbol(sym.LESS); }
    "<="        { return symbol(sym.LESS_EQUAL); }
    ">"         { return symbol(sym.GREATER); }
    ">="        { return symbol(sym.GREATER_EQUAL); }
    "=="        { return symbol(sym.EQUAL); }
    "!="        { return symbol(sym.NOT_EQUAL); }

    /* Operadores Lógicos */
    "@"         { return symbol(sym.AND); }
    "~"         { return symbol(sym.OR); }
    "Σ"         { return symbol(sym.NOT); }

    /* Delimitadores */
    "¡"         { return symbol(sym.LBRACE); }
    "!"         { return symbol(sym.RBRACE); }
    "¿"         { return symbol(sym.LPAREN); }
    "?"         { return symbol(sym.RPAREN); }
    "["         { return symbol(sym.LBRACKET); }
    "]"         { return symbol(sym.RBRACKET); }

    /* Puntuación */
    ","         { return symbol(sym.COMMA); }
    ";"         { return symbol(sym.SEMI); }
    ":"         { return symbol(sym.COLON); }
    "."         { return symbol(sym.DOT); }
    "="         { return symbol(sym.ASSIGN); }
    "->"        { return symbol(sym.ARROW); }

    /* Comentarios */
    {LineComment} {
        /* Ignorar comentarios de una linea */
    }

    {MultiCommentStart} {
        yybegin(MULTI_COMMENT);
    }

    /* Espacios en Blanco */
    {WhiteSpace} { /* ignorar */ }

    /* Manejo de Errores (Falta agregar) */

    /* ERROR: Identificador que comienza con número */
    [0-9]+[a-zA-Z_][a-zA-Z0-9_]* {
        System.err.println("ERROR LÉXICO: Identificador no puede comenzar con número...");
        return symbol(sym.ERROR);
    }

    /* ERROR: Número mal formado */
    [0-9]*\.\.+[0-9]* {
        System.err.println("ERROR LÉXICO: Número decimal inválido...");
        return symbol(sym.ERROR);
    }

    /* ERROR FINAL: cualquier cosa no reconocida */
    [^] {
        reportError("Caracter no reconocido '" + yytext() + "'");
        return symbol(sym.ERROR);
    }
}

<<EOF>> {
    return symbol(sym.EOF);
}