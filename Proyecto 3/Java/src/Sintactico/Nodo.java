package Sintactico;
import java.util.ArrayList;

public class Nodo {
    public String lexema;
    public String tipo;
    public ArrayList<Nodo> hijos;

    public Nodo(String lexema) {
        this.lexema = lexema;
        this.tipo = "";
        this.hijos = new ArrayList<Nodo>();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void addHijo(Nodo hijo) {
        this.hijos.add(hijo);
    }

}