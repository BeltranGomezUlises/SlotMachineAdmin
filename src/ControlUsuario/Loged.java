
package ControlUsuario;

public class Loged {
    
    private static Usuario loged;

    public static Usuario getLoged() {
        return loged;
    }

    public static void setLoged(Usuario loged) {
        Loged.loged = loged;
    }
    
}
