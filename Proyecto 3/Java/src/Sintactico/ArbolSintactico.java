package Sintactico;

public class ArbolSintactico {
    private Nodo raiz;

    public ArbolSintactico(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void print() {
        if (raiz == null) {
            System.out.println("Árbol sintáctico vacío.");
            return;
        }

        System.out.println("=== ÁRBOL SINTÁCTICO ===");
        printArbol(raiz, 0);
    }

    private void printArbol(Nodo nodo, int nivel) {
        if (nodo == null) {
            System.out.println("  ".repeat(nivel) + "[NULL NODE]");
            return;
        }

        String indent = "  ".repeat(nivel);

        // Verificar que hijos no sea null
        if (nodo.hijos != null && nodo.hijos.size() > 0) {
            System.out.println(indent + "Padre: " + nodo.lexema);
            System.out.println(indent + "Hijos: ");
            for (Nodo hijo : nodo.hijos) {
                if (hijo == null) {
                    System.out.println(indent + "\t[Hijo Nulo]");
                } else {
                    System.out.println(indent + "\t" + hijo.lexema);
                }
            }
        } /*else {
            System.out.println(indent + "Nodo: " + nodo.lexema);
        }*/

        // Recursivamente imprimir hijos
        if (nodo.hijos != null) {
            for (Nodo hijo : nodo.hijos) {
                if (hijo != null) {
                    printArbol(hijo, nivel + 1);
                }
            }
        }
    }
}