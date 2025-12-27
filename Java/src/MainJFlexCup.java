import generated.Lexer;
import generated.sym;
import java_cup.internal_error;
import java_cup.parser;
import java_cup.runtime.Symbol;
import jflex.exceptions.SilentExit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


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

    public void prueba(String rutaScanear) throws IOException{
        Reader reader = new BufferedReader(new FileReader(rutaScanear));
        reader.read();
        Lexer lex = new Lexer(reader);
        int i = 0;
        Symbol token;
        BufferedWriter writer = null;

        try {
            Path carpeta = Path.of(System.getProperty("user.dir")).resolve("Java").resolve("src").resolve("codigoPrueba");
            Path archivo = carpeta.resolve("tokens.txt");

            Files.createDirectories(carpeta);

            writer = new BufferedWriter(new FileWriter(archivo.toString()));

            while (true) {
                token = lex.next_token();

                if (token.sym == sym.EOF) {
                    System.out.println("Cantidad de lexemas encontrados: " + i);
                    break;
                }

                String valor = (token.value == null)
                        ? lex.yytext()
                        : token.value.toString();

                System.out.println("token: " + token.sym + ", valor: " + valor);

                writer.write("token: " + token.sym + "lexema: " + valor);
                writer.newLine();

                i++;
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de tokens");
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException ignored) {}
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
