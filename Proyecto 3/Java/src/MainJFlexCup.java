
import java_cup.internal_error;
import java_cup.runtime.Symbol;
import jflex.exceptions.SilentExit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import Sintactico.*;

public class MainJFlexCup {

    public void iniLexerParser(String rutaLexer, String rutaParser) throws internal_error, Exception {
        GenerateLexer(rutaLexer);
        Generateparser(rutaParser);
    } // este método simplemente se encarga de invocar los métodos para generar los productos de lexer y parser

    public void GenerateLexer(String ruta) throws IOException, SilentExit {
        String[] strArr = {ruta};
        jflex.Main.generate(strArr);
    } // se llama a la librería jflex para generar el lexer

    public void Generateparser(String ruta) throws internal_error, IOException, Exception {
        String[] strArr = {ruta};
        java_cup.Main.main(strArr);
    } // se llama a la librería java_cup para generar el parser

    public void prueba(String rutaScanear) throws IOException {
        Reader reader = new BufferedReader(new FileReader(rutaScanear));
        generated.Lexer lex = new generated.Lexer(reader);
        Symbol token;
        BufferedWriter writer = null;

        // en esta sección es donde ocurre lo más importa a nivel de usuario
        // el programa crea el archivo, si no existe, para guardar los tokens y sus datos asociados
        // además, al ser un try cath, mantenemos un poco mejor el manejo de errores al escribir el archivo
        try {
            Path carpeta = Path.of(System.getProperty("user.dir")).resolve("Java").resolve("src").resolve("codigoPrueba");
            Path archivo = carpeta.resolve("tokens.txt");

            Files.createDirectories(carpeta);

            writer = new BufferedWriter(new FileWriter(archivo.toString()));

            while (true) {
                token = lex.next_token();

                if (token.sym == generated.sym.EOF) {
                    break;
                }

                String valor = (token.value == null)
                        ? lex.yytext()
                        : token.value.toString();

                // Obtener línea y columna
                int linea = token.left + 1;
                int columna = token.right + 1;


                // Escribir en archivo con línea y columna
                writer.write("token: " + token.sym + " lexema: " + valor +
                        " Línea: " + linea + " Columna: " + columna);
                writer.newLine();

            }
            System.out.println("***Tokens registrados en el archivo tokens.txt en la carpeta codigoPrueba.***");

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de tokens");
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException ignored) {
            }
        }
    }


    public void pruebaParser(String rutaScanear, int opcion) throws Exception{
        Reader reader = new BufferedReader(new FileReader(rutaScanear));

        generated.Lexer lexer = new generated.Lexer(reader);
        generated.parser parser = new generated.parser(lexer);

        parser.parse();
        if(opcion == 0){
            ArbolSintactico arbol = parser.getArbolSintactico();
            if (arbol != null) {
                arbol.print();
            } else {
                System.out.println("No disponible.");
            }
        } else if (opcion == 1) {
            System.out.println("\\n=== ANÁLISIS SINTÁCTICO COMPLETADO ===\\n");
            parser.getSymTable().print();
        }
        else{
            prueba(rutaScanear);
            ArbolSintactico arbol = parser.getArbolSintactico();
            if (arbol != null) {
                arbol.print();
            } else {
                System.out.println("No disponible.");
            }
            System.out.println("\\n=== ANÁLISIS SINTÁCTICO COMPLETADO ===\\n");
            parser.getSymTable().print();
        }
    }

    public static void main(String[] args) throws Exception {

    }
}