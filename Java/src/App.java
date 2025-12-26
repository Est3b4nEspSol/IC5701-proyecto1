import generated.Main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App {

    public static void GenerarLexerParser() throws Exception{
        String basePath, fullPathLexer, fullPathparser, jlexer, jparser, jlexerCarpeta;
        Main main;

        basePath = System.getProperty("user.dir");
        //.java de parser y lexer
        jlexer = "Lexer.java";
        jparser = "Parser.java";

        main = new Main();

        Files.deleteIfExists(Paths.get(basePath + "\\src\\lexer\\lexer.flex"));

        fullPathLexer = basePath + "\\src\\main\\java\\generated\\lexer.flex";
        fullPathparser = basePath + "\\src\\parser\\parser.cup";
        jparser = "ParserIni.java";
        jlexer = "Lexer.java";
        jlexerCarpeta = "generated";

        Files.deleteIfExists(Paths.get(basePath+ "\\src\\main\\java\\generated\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath+ "\\src\\main\\java\\generated\\" + jlexer));

        main.iniLexerParser(fullPathLexer, fullPathparser);

        Files.move(Paths.get(basePath+"\\sym.java"), Paths.get(basePath + "\\src\\main\\java\\generated\\sym.java"));
        Files.move(Paths.get(basePath+"\\"+ jparser), Paths.get(basePath + "\\src\\main\\java\\generated\\"+ jparser));
        Files.move(Paths.get(basePath+"\\src\\" + jlexerCarpeta + "\\" + jlexer), Paths.get(basePath + "\\src\\main\\java\\generated\\" + jlexer));

    }

    public static void main(String[] args) throws Exception{
        GenerarLexerParser();
    }
}




