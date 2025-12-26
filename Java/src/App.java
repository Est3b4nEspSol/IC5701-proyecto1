

import java.nio.file.Files;
import java.nio.file.Paths;


public class App {

    public static void GenerarLexerParser() throws Exception{
        String basePath, fullPathLexer, fullPathparser, jlexer, jparser, jlexerCarpeta;
        MainJFlexCup mfjc;

        basePath = System.getProperty("user.dir");
        //.java de parser y lexer
        jlexer = "generated.Lexer.java";
        jparser = "Parser.java";

        mfjc = new MainJFlexCup();

        Files.deleteIfExists(Paths.get(basePath + "\\Java\\src\\main\\java\\generated\\sym.java"));

        fullPathLexer = basePath + "\\Java\\src\\lexer\\lexer.flex";
        fullPathparser = basePath + "\\Java\\src\\parser\\parser.cup";
        jparser = "parser.java";
        jlexer = "generated.Lexer.java";
        jlexerCarpeta = "\\main\\java\\generated";

        Files.deleteIfExists(Paths.get(basePath+ "\\Java\\src\\main\\java\\generated\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath+ "\\Java\\src\\main\\java\\generated\\" + jlexer));

        mfjc.iniLexerParser(fullPathLexer, fullPathparser);

        Files.move(Paths.get(basePath+"\\sym.java"), Paths.get(basePath + "\\Java\\src\\main\\java\\generated\\sym.java"));
        Files.move(Paths.get(basePath+"\\"+ jparser), Paths.get(basePath + "\\Java\\src\\main\\java\\generated\\"+ jparser));
        Files.move(Paths.get(basePath+"\\Java\\src\\main\\java\\" + jlexerCarpeta + "\\" + jlexer), Paths.get(basePath + "\\Java\\src\\main\\java\\generated\\" + jlexer));

    }

    public static void PruebasLexerParser() throws Exception{
        String basePath, fullPathScanner, fullPathParser = "";
        MainJFlexCup mfjc;

        basePath = System.getProperty("user.dir");
        fullPathScanner = basePath + "\\Java\\src\\codigoPrueba\\prueba1.txt";

         mfjc = new MainJFlexCup();
         mfjc.prueba1(fullPathScanner);

    }

    public static void main(String[] args) throws Exception{
        GenerarLexerParser();
    }
}




