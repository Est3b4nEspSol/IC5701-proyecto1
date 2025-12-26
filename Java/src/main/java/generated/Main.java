package generated;

import java.io.Reader;
import java_cup.internal_error;
import java_cup.parser;
import java_cup.runtime.Symbol;
import jflex.exceptions.SilentExit;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java_cup.parser;

import java.io.IOException;
import java.io.StringReader;

public class Main {

    public void iniLexerParser(String rutaLexer, String rutaParser) throws internal_error, Exception{
        GenerateLexer(rutaLexer);
        Generateparser(rutaParser);
    }

    public void GenerateLexer(String ruta) throws IOException, SilentExit{
        String[] strArr = {ruta};
        jflex.Main.generate(strArr);
    }

    public void Generateparser(String ruta) throws internal_error, IOException, Exception{
        String[] strArr = {ruta};
        java_cup.Main.main(strArr);
    }

    public static void main(String[] args) throws Exception {
        String input = "12 + 34 + 5";

        Lexer lexer = new Lexer(new StringReader(input));
        parser parser = new parser(lexer);

        parser.parse();

        System.out.println("Análisis completado correctamente.");
    }
}
