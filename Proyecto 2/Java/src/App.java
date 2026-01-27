import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class App {
    private static final Scanner scanner = new Scanner(System.in); // inicializamos un scanner general (modo singleton) para evitar problemas
    static final MainJFlexCup mfjc = new MainJFlexCup();

    public static void GenerarLexerParser() throws Exception{
        String basePath, fullPathLexer, fullPathparser, jlexer, jparser, jlexerCarpeta; // se crean variables para el manejo de archivos

        basePath = System.getProperty("user.dir"); //camino base para prácticamente todo lo que hacemos

        // en esta sección vamos a borrar todos los archivos generados por el lexer.flex y el parser.cup
        Files.deleteIfExists(Paths.get(basePath + "\\Java\\src\\generated\\sym.java"));

        fullPathLexer = basePath + "\\Java\\src\\generated\\lexer.flex";
        fullPathparser = basePath + "\\Java\\src\\parser\\parser.cup";
        jparser = "parser.java";
        jlexer = "Lexer.java";
        jlexerCarpeta = "generated";

        Files.deleteIfExists(Paths.get(basePath+ "\\Java\\src\\generated\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath+ "\\Java\\src\\generated\\" + jlexer));

        // en esta sección vamos a mover los archivos generados por el lexer a una misma carpeta llamada generated para mantener el orden
        mfjc.iniLexerParser(fullPathLexer, fullPathparser);

        Files.move(Paths.get(basePath+"\\sym.java"), Paths.get(basePath + "\\Java\\src\\generated\\sym.java"));
        Files.move(Paths.get(basePath+"\\"+ jparser), Paths.get(basePath + "\\Java\\src\\generated\\"+ jparser));
        Files.move(Paths.get(basePath+"\\Java\\src\\" + jlexerCarpeta + "\\" + jlexer), Paths.get(basePath + "\\Java\\src\\generated\\" + jlexer));

    }

    public static void PruebasLexerParser() throws Exception{ // como su nombre indica, con este método hacemos pruebas
        String basePath, fullPathScanner, nombre; // se crean variables para trabajar después

        basePath = System.getProperty("user.dir"); // inicializamos el camino base de los archivos

        // esta sección es bastante autoexplicativa leyendo  los sout, pero se centra en
        // pedir el nombre del archivo en el que vamos a utilizar el analizador léxico.
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
        mfjc.prueba(fullPathScanner); // aquí es donde trabajamos con los tokens
    }

    public static void PruebaSintactica(int opcion) throws Exception{ // como su nombre indica, con este método hacemos pruebas
        String basePath, fullPathScanner, nombre; // se crean variables para trabajar después

        basePath = System.getProperty("user.dir"); // inicializamos el camino base de los archivos

        // esta sección es bastante autoexplicativa leyendo los sout, pero se centra en
        // pedir el nombre del archivo en el que vamos a utilizar el analizador léxico.
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

        mfjc.pruebaParser(fullPathScanner, opcion); // aquí es donde trabajamos con los tokens
    }

    public static void main(String[] args) throws Exception{
        int opcion = 0;

        while (opcion != 6) { // autoexplicativa, interacción continua con el usuario
            System.out.println("=================================");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Generar parser");
            System.out.println("2. Generar lexemas");
            System.out.println("3. Generar árbol sintáctico");
            System.out.println("4. Generar tabla de símbolos");
            System.out.println("5. Generar todo lo anterior (excepto parser)");
            System.out.println("6. Terminar programa");
            System.out.print("Opción: ");

            String input = scanner.nextLine(); // leer línea completa

            try {
                opcion = Integer.parseInt(input); // convertir
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Debe ingresar un número.");
                opcion = 0;
                continue;
            }

            // dependiendo del número que ingrese el usuario, el programa hará una de las siguientes acciones
            switch (opcion) {
                case 1:
                    GenerarLexerParser();
                    break;
                case 2:
                    PruebasLexerParser();
                    break;
                case 3:
                    PruebaSintactica(opcion%3);
                    break;
                case 4:
                    PruebaSintactica(opcion%3);
                case 5:
                    PruebaSintactica(opcion%3);
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}



