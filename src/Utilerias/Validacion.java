package Utilerias;

import java.awt.event.KeyEvent;

public class Validacion {
        
    public static boolean isDigit(KeyEvent evt){
        boolean res = false;
        if (Character.isDigit(evt.getKeyChar())) {
            res = true;
        }
        return res;
    }
        
}

