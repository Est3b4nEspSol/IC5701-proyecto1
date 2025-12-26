package generated;

import java_cup.Lexer;
import java_cup.internal_error;
import java_cup.parser;
import java_cup.runtime.Symbol;
import jflex.exceptions.SilentExit;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;

public class MainJFlexCup {

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

    public void prueba1(String rutaScanear) throws IOException{
        Reader reader = new BufferedReader(new FileReader(rutaScanear));
        reader.read();
        Lexer lex = new Lexer(reader);
        int i = 0;
        Symbol token;
        while(true)
        {
            token = lex.next_token();
            if(token.sym != 0){
                System.out.println("token: "+ ", valor: " +(token.value == null?lex.yytext():token.value.toString()));
            }
            else {
                System.out.println("Cantidad de lexemas encontrados: " + i);
                return;
            }
            i++;
        }

    }

    public static void main(String[] args) throws Exception {
        String input = "12 + 34 + 5";
        System.out.println(System.getProperty("user.dir"));

        Lexer lexer = new Lexer(new StringReader(input));
        parser parser = new parser(lexer);

        parser.parse();

        System.out.println("Análisis completado correctamente.");
    }
}
