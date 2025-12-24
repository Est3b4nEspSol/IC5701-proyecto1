import java_cup.runtime.*;

%%
%class Lexer
%public
%unicode
%cup
%line
%column



 %{
      StringBuffer string = new StringBuffer();

      private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
      }
      private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
      }
    %}

    /* comments */
        Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

    //HAY QUE AJUSTARLO EN EL CONTEXTO DE NUESTRO PROYECTO
        TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
        // Comment can be the last line of the file, without line terminator.
        EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
        DocumentationComment = "/**" {CommentContent} "*"+ "/"
        CommentContent       = ( [^*] | \*+ [^/*] )*

    //Que permita guion bajo o encontrar el patron para identificarlo
    Identifier = [:jletter:] [:jletterdigit:]*

    //Definir enteros y flotantes ya sea con REGEX o definiciones regulares como se vio en clases (pantallazos)

//Investigar state
 %state CADENA

    %%

    /*Nota: Para esta seccion el orden es de arriba hacia abajo y el Jflex va a buscar siempre la cadena mas larga.
    (Creo que esto era el ejemplo de System.out.print(X-----Y)
    Ejemplo: si pongo Identifier antes que abstract eso puede dar error porque lee abstract como un identifier.
    Hay unas palabras reservadas que se pueden mezclar con Identifier entonces
    Se debe modificar esta parte para lo que se pide del proyecto*/

    /* Recomendacion del profe:
    Palabras Reservadas de esta manera */
    <YYINITIAL> "abstract"           { return symbol(sym.ABSTRACT); }
    <YYINITIAL> "boolean"            { return symbol(sym.BOOLEAN); }
    <YYINITIAL> "break"              { return symbol(sym.BREAK); }

    /*Operadores de esta manera*/
    <YYINITIAL> {
      /* identifiers */
      {Identifier}                   { return symbol(sym.IDENTIFIER); }

      /* literals */
      {DecIntegerLiteral}            { return symbol(sym.INTEGER_LITERAL); }
      \"                             { string.setLength(0); yybegin(CADENA); }

      /* operators */
      "="                            { return symbol(sym.EQ); }
      "=="                           { return symbol(sym.EQEQ); }
      "+"                            { return symbol(sym.PLUS); }

      /* comments */
      {Comment}                      { /* ignore */ }

      /* whitespace */
      {WhiteSpace}                   { /* ignore */ }
    }

    /*Errores se tiene que implementar*/

[0-9]+     { return new java_cup.runtime.Symbol(sym.NUM); }
"+"        { return new java_cup.runtime.Symbol(sym.PLUS); }
[ \t\r\n]+ { /* ignorar espacios */ }
