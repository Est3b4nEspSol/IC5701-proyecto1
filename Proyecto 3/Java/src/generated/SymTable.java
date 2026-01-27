package generated;

import java.util.*;



public class SymTable {

    private Stack<Map<String, SymInfo>> scopes = new Stack<>();
    private ArrayList<Map<String, SymInfo>> todo = new ArrayList<>() ;
    public SymTable() {
        enterScope(); // ámbito global
    }

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        todo.add(scopes.pop());
    }

    public Map<String, SymInfo> currentScope(){
        return new HashMap<>();
    }

    public void insert(SymInfo sym) {
        scopes.peek().put(sym.name, sym);
    }


    public void print() {
        System.out.println("===== TABLA DE SÍMBOLOS (SINTÁCTICA) =====");
        for (Map<String, SymInfo> scope : scopes) {
            for (SymInfo s : scope.values()) {
                System.out.println(s);
            }
        }
        for (Map<String, SymInfo> bicho : todo) {
            for (SymInfo s : bicho.values()) {
                System.out.println(s);
            }
        }
    }


}
