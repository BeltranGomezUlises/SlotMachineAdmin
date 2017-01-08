
package Utilerias;

import Formato.Formato;
import static Formato.Formato.ordenarFormatosQ;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

public class Utileria {
    
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/MM/yyyy");
    
    public static String quitaGuion(String s){
       String sinGuion="";
       for(int k=0; k<s.length(); k++){
           if(s.charAt(k)=='_'){
               sinGuion+=' ';
           }else{
               sinGuion+=s.charAt(k);
           }
       }
       return sinGuion;
   }
    
    public static String quitaEspacios(String s){
        String sinEspacio="";
        for(int k=0; k<s.length(); k++){
            if(s.charAt(k)==' '){
                sinEspacio+="_";
            }else{
               sinEspacio+=s.charAt(k);
            }
        }
       return sinEspacio;
    }
    
    public static String quitaDiagonal(String s){
        String sinDiagonal="";
        for (int i = 0; i <s.length(); i++) {
            if(s.charAt(i)=='/'){
                sinDiagonal+='_';
            }else{
                sinDiagonal+=s.charAt(i);
            }
        }
        return sinDiagonal;
    }
    
    public static String quitarCaracteresEspeciales(String f){
       //quitar caracteres especiales no imprimibles de la fecha
        char[] oldChars = new char[f.length()];
        f.getChars(0, f.length(), oldChars, 0);
        char[] newChars = new char[f.length()];
        int newLen = 0;
        for (int j = 0; j < f.length(); j++) {
            char ch = oldChars[j];
            if (Character.isDigit(ch) || ch=='/') {
                newChars[newLen] = ch;
                newLen++;
            }
        }
        f = new String(newChars, 0, newLen); //asignar a la fecha la nueva cadena de caracteres
        return f;
    }
    
    public static String binaryToString(String digitoBinario){             
            String frase = "";//almacena la frase completa            
            for (int i = 0; i < digitoBinario.length(); i += 8) {//recorre la frase de 8 en 8
                /*separa la cadena cada 8 digitos con substring*/
                String cadenaSeparada = digitoBinario.substring(i, i + 8);
                /*entrega un numero decimal a partir de un numero binario de 8 bit*/
                int decimal = Integer.parseInt(cadenaSeparada, 2);
                /*concadena la frase y transfroma el decimal a Ascii*/
                frase = frase + (char) decimal;                
            }
            return frase;//retorna la frase completa            
        }
        
    public static  String stringToBinary(String text){                
            String codigoBinario = "";//guarda el en binario
            for (int i = 0; i < text.length(); i++) {//recorre el texto ingresado
                char letra = text.charAt(i);//separa letra a letra
                /* si el binario de la letra es menor a 8 bit entonces se le agrega
                 un cero a la izquierda para llenar el bit restante*/
                int aux = Binario((int) (letra)).length();//almacena largo del binario de la letra devuelta
                for (int j = 0; j < 8; j++) {//recorrido de 8 characteres
                    if (aux < 8) {//si el largo del binario es menor a 7 (0-7)entonces
                        codigoBinario = codigoBinario + "0";//agregar 0 a la concadenacion
                        aux++;//aumentar el largo del binario
                    }
                }
                /*se concatena el binario de cada letra separado por un espacio. al metodo binario
                 se le pasa por parametro el decimal correspondiente a cada letra*/
                codigoBinario = codigoBinario + Binario((int) (letra));
            }
            return codigoBinario;/*imprime el codigo binario completo*/
    }

    static private String Binario(int Decimal) {
            int R, x = 0;//variables que se implementaran
            String Binario = ""; //guarda el contenido en codigo binario
            R = Decimal % 2;//resto del parametro
            if (R == 1) {//si el resto es 1
                while (Decimal > 1) {//si el parametro es mas grande que el resto
                    Decimal /= 2;//entonces decimal se divide en 2 y se guarda en decimal
                    x = Decimal % 2;//x contendra el resto del decimal
                    Binario = String.valueOf(x + Binario);//binario se ira formando de forma correcta y no alreves
                }
            } else {//si resto no es 1
                while (Decimal > 0) {//y decimal es mayor a 0
                    Decimal /= 2;//decimal se divide en 2
                    x = Decimal % 2;//x contendra el resto del decimal resultante
                    Binario = String.valueOf(x + Binario);//se forma el numero en binario de forma correcta
                }
            }
         return String.valueOf(Binario + x);//devuelve el binario resultante mas el ultimo bit
    }    
    //con este metodo podremos ordenar una lista de fechas en String de forma ascendente
    public static void BubbleOrderStringOfDates(ArrayList<String> lista){
        ArrayList<Date> dates = new ArrayList<>();        
        for (String s  : lista) {
            try {
                dates.add(simpleDateFormat.parse(s));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Error de converion de fecha al ordenar por burbuja");
            }
        }        
        for (int i = 0; i < dates.size() - 1; i++) {
            for (int j = 0; j < dates.size() - 1 - i; j++) {
                if (dates.get(j).after(dates.get(j + 1))) {
                    //para la lista de fechas
                    Date aux = dates.get(j + 1);
                    dates.set(j + 1, dates.get(j));
                    dates.set(j, aux);
                    //su equivalente de string
                    String auxS = lista.get(j + 1);
                    lista.set(j + 1, lista.get(j));
                    lista.set(j, auxS);
                }
            }
        }
                                
    } 
    public static void QSortStringDates(ArrayList<String> dates) throws ParseException{
        ArrayList<Date> miDates = new ArrayList<>();
        for (String date : dates) {
            miDates.add(simpleDateFormat.parse(date));
        }
        QuickSortStringOfDates(miDates, 0, miDates.size() - 1);
    }
    
    public static void QuickSortStringOfDates(ArrayList<Date> lista, int izq, int der) throws ParseException{
            int i = izq; 
            int j = der; 
            Date x = lista.get((izq + der) /2 ); 
            Date aux;
            do{ 
                while( (lista.get(i).compareTo(x) < 0) && (j <= der) ){ 
                       i++;} 
                while( (lista.get(j).compareTo(x) > 0) && (j > izq) ){ 
                       j--;} 
                if( i <= j ){ 
                    aux = simpleDateFormat.parse(simpleDateFormat.format(lista.get(i)));                            
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);                            
                    i++;  j--; 
                } 
            }while( i <= j ); 
            if( izq < j ) 
                QuickSortStringOfDates( lista, izq, j ); 
            if( i < der ) 
                QuickSortStringOfDates( lista, i, der );  
    }
  
    public static Date lunesAnterior(String fecha){              
        GregorianCalendar cal = new GregorianCalendar();                
        try {
            cal.setTime(simpleDateFormat.parse(fecha));    
        } catch (Exception e) {
        }           
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) { //mientras sea mayor que lunes           
            cal.add(Calendar.DATE, -1); //restar un dia
        }
        return cal.getTime();                
    }
    
    public static Date lunesPosterior(String fecha){
        GregorianCalendar cal = new GregorianCalendar();                
        try {
            cal.setTime(simpleDateFormat.parse(fecha));    
        } catch (Exception e) {
        } 
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) { //mientras sea mayor que lunes           
            cal.add(Calendar.DATE, 1); //restar un dia
        }
        return cal.getTime();  
    }
    public static Date lunesAnterior(Date fecha){              
        GregorianCalendar cal = new GregorianCalendar();                
        try {
            cal.setTime(fecha);    
        } catch (Exception e) {
        }           
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) { //mientras sea mayor que lunes           
            cal.add(Calendar.DATE, -1); //restar un dia
        }
        return cal.getTime();                
    }
    
    public static Date domingoPosterior(Date fecha){
        GregorianCalendar cal = new GregorianCalendar();                
        try {
            cal.setTime(fecha);    
        } catch (Exception e) {
        } 
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) { //mientras sea mayor que lunes           
            cal.add(Calendar.DATE, 1); //restar un dia
        }
        return cal.getTime();  
    }
 
     public static boolean belongsDateToWeek(String date, String week){
        boolean res = false;
        try {
            GregorianCalendar calDate = new GregorianCalendar();           
            GregorianCalendar calWeek = new GregorianCalendar();           

            calDate.setTime(simpleDateFormat.parse(date));                            
            calWeek.setTime(simpleDateFormat.parse(week));    
            
            if (calDate.get(Calendar.WEEK_OF_YEAR) == calWeek.get(Calendar.WEEK_OF_YEAR)) {
                res = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conversion de fechas", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }
     
    /* PARA CALCULAR LA CANTIDAD DE MILISEGUNDOS QUE TOMA UN PROCESO
    long time_start, time_end;
    time_start = System.currentTimeMillis();

    time_end = System.currentTimeMillis();
    System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");    
    */
}
