import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class App {

    public static void GenerarLexerParser() throws Exception{
        String basePath, fullPathLexer, fullPathparser, jlexer, jparser, jlexerCarpeta;
        MainJFlexCup mfjc;

        basePath = System.getProperty("user.dir");
        //.java de parser y lexer
        jlexer = "Lexer.java";
        jparser = "Parser.java";

        mfjc = new MainJFlexCup();

        Files.deleteIfExists(Paths.get(basePath + "\\Java\\src\\main\\java\\generated\\sym.java"));

        fullPathLexer = basePath + "\\Java\\src\\main\\java\\generated\\lexer.flex";
        fullPathparser = basePath + "\\Java\\src\\parser\\parser.cup";
        jparser = "parser.java";
        jlexer = "Lexer.java";
        jlexerCarpeta = "generated";

        Files.deleteIfExists(Paths.get(basePath+ "\\Java\\src\\main\\java\\generated\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath+ "\\Java\\src\\main\\java\\generated\\" + jlexer));

        mfjc.iniLexerParser(fullPathLexer, fullPathparser);

        Files.move(Paths.get(basePath+"\\sym.java"), Paths.get(basePath + "\\Java\\src\\main\\java\\generated\\sym.java"));
        System.out.println("oli1");
        Files.move(Paths.get(basePath+"\\"+ jparser), Paths.get(basePath + "\\Java\\src\\main\\java\\generated\\"+ jparser));
        System.out.println("oli2");
        Files.move(Paths.get(basePath+"\\Java\\src\\main\\java\\" + jlexerCarpeta + "\\" + jlexer), Paths.get(basePath + "\\Java\\src\\main\\java\\generated\\" + jlexer));
        System.out.println("breteó");

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
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("=================================");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Generar parser");
            System.out.println("2. Probar analizador");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    GenerarLexerParser();
                    break;
                case 2:
                    PruebasLexerParser();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}




