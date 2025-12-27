import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void GenerarLexerParser() throws Exception{
        String basePath, fullPathLexer, fullPathparser, jlexer, jparser, jlexerCarpeta;
        MainJFlexCup mfjc;

        basePath = System.getProperty("user.dir");

        mfjc = new MainJFlexCup();

        Files.deleteIfExists(Paths.get(basePath + "\\Java\\src\\generated\\sym.java"));

        fullPathLexer = basePath + "\\Java\\src\\generated\\lexer.flex";
        fullPathparser = basePath + "\\Java\\src\\parser\\parser.cup";
        jparser = "parser.java";
        jlexer = "Lexer.java";
        jlexerCarpeta = "generated";

        Files.deleteIfExists(Paths.get(basePath+ "\\Java\\src\\generated\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath+ "\\Java\\src\\generated\\" + jlexer));

        mfjc.iniLexerParser(fullPathLexer, fullPathparser);

        Files.move(Paths.get(basePath+"\\sym.java"), Paths.get(basePath + "\\Java\\src\\generated\\sym.java"));
        Files.move(Paths.get(basePath+"\\"+ jparser), Paths.get(basePath + "\\Java\\src\\generated\\"+ jparser));
        Files.move(Paths.get(basePath+"\\Java\\src\\" + jlexerCarpeta + "\\" + jlexer), Paths.get(basePath + "\\Java\\src\\generated\\" + jlexer));

    }

    public static void PruebasLexerParser() throws Exception{
        String basePath, fullPathScanner, nombre;
        MainJFlexCup mfjc = new MainJFlexCup();

        basePath = System.getProperty("user.dir");

        while(true){
            System.out.println("Indicar nombre del archivo (en la carpeta codigoPrueba) en formato .txt (sin agregar .txt al final): ");
            nombre = scanner.nextLine();
            fullPathScanner = basePath + "\\Java\\src\\codigoPrueba\\" + nombre +".txt";

            File archivo = new File(fullPathScanner);

            if (!archivo.exists()) {
                System.out.println("El archivo '" + nombre + ".txt' no existe, ingrese uno que se encuentre en la carpeta codigoPrueba de la carpeta src");
            }
            else{
                break;
            }
        }
        mfjc.prueba(fullPathScanner);
    }

    public static void main(String[] args) throws Exception{
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("=================================");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Generar parser");
            System.out.println("2. Probar analizador");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            String input = scanner.nextLine(); // leer línea completa

            try {
                opcion = Integer.parseInt(input); // convertir
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Debe ingresar un número.");
                opcion = 0;
                continue;
            }

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
    }
}




