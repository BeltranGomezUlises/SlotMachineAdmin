package ControlUsuario;

import Utilerias.Utileria;
import static Utilerias.Utileria.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Usuario{
    
    String usuario;
    String contraseña;
    String permiso; //usuario o administrador
    
    boolean eliminarFormato=true, pagarFormato=true, exportarFormatos=true, eliminarCorte=true, exportarCorte=true,importarCortes=true,asignarFalloCortes=true;
    boolean editarEmpleados=true,editarSucursales=true,editarMaquinas=true,editarTarjetas=true, editarFormato=true, respaldar=true;
    boolean consultarEmpleados=true,consultarSucursales=true,consultarMaquinas=true,consultarTarjetas=true;
    boolean editarProducto=true,consultarProducto=true, editarCorte = true;
    
    boolean capturaFormato=true, capturaCorte=true, capturaMovimiento=true;
    boolean consultaFormato=true, consultaCorte=true, consultaMovimiento=true;
    boolean consultaRutero=true, editarRutero=true;

    boolean eliminarMovimiento=true, importarMovimiento=true, ajusteMovimiento=true;
    
    boolean consultaEstadisticas = true, consultaEncargados = true, capturaVales = true, consultaVales = true, editarVales = true;
    boolean consultaEtiquetas = true, consultaPerifericos = true, consultaChequeras = true;
    boolean editarEtiquetas = true, editarPerifericos = true, editarChequeras = true;
    
    public Usuario(String usuario, String contraseña,String permiso) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.permiso=permiso;
    }

    public boolean isImportarMovimiento() {
        return importarMovimiento;
    }
    public void setImportarMovimiento(boolean importarMovimiento) {
        this.importarMovimiento = importarMovimiento;
    }

    public boolean isAjusteMovimiento() {
        return ajusteMovimiento;
    }
    public void setAjusteMovimiento(boolean ajusteMovimiento) {
        this.ajusteMovimiento = ajusteMovimiento;
    }

    public boolean isEliminarMovimiento() {
        return eliminarMovimiento;
    }
    public void setEliminarMovimiento(boolean eliminarMovimiento) {
        this.eliminarMovimiento = eliminarMovimiento;
    }
    
    
    public boolean isConsultarProducto() {
        return consultarProducto;
    }
    public void setConsultarProducto(boolean consultarProducto) {
        this.consultarProducto = consultarProducto;
    }
    
    
    public String getUsuario() {
        return usuario;
    }    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public String getPermiso() {
        return permiso;
    }
    
    public boolean isEditarProducto() {
        return editarProducto;
    }
    public void setEditarProducto(boolean editarProducto) {
        this.editarProducto = editarProducto;
    }    
    
    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }    
    
    public boolean isEliminarFormato() {
        return eliminarFormato;
    }
    public void setEliminarFormato(boolean eliminarFormato) {
        this.eliminarFormato = eliminarFormato;
    }
    
    public boolean isPagarFormato() {
        return pagarFormato;
    }
    public void setPagarFormato(boolean pagarFormato) {
        this.pagarFormato = pagarFormato;
    }
    
    public boolean isExportarFormatos() {
        return exportarFormatos;
    }
    public void setExportarFormatos(boolean exportarFormatos) {
        this.exportarFormatos = exportarFormatos;
    }
    
    public boolean isEliminarCorte() {
        return eliminarCorte;
    }
    public void setEliminarCorte(boolean eliminarCorte) {
        this.eliminarCorte = eliminarCorte;
    }
    
    public boolean isExportarCortes() {
        return exportarCorte;
    }
    public void setExportarCortes(boolean exportarCorte) {
        this.exportarCorte = exportarCorte;
    }
    
    public boolean isImportarCortes() {
        return importarCortes;
    }
    public void setImportarCortes(boolean importarCortes) {
        this.importarCortes = importarCortes;
    }
    
    public boolean isAsignarFalloCortes() {
        return asignarFalloCortes;
    }
    public void setAsignarFalloCortes(boolean asignarFalloCortes) {
        this.asignarFalloCortes = asignarFalloCortes;
    }
    
    public boolean isEditarEmpleados() {
        return editarEmpleados;
    }
    public void setEditarEmpleados(boolean editarEmpleados) {
        this.editarEmpleados = editarEmpleados;
    }
       
    public boolean isEditarSucursales() {
        return editarSucursales;
    }
    public void setEditarSucursales(boolean editarSucursales) {
        this.editarSucursales = editarSucursales;
    }
    
    public boolean isEditarMaquinas() {
        return editarMaquinas;
    }
    public void setEditarMaquinas(boolean editarMaquinas) {
        this.editarMaquinas = editarMaquinas;
    }
    
    public boolean isEditarTarjetas() {
        return editarTarjetas;
    }
    public void setEditarTarjetas(boolean editarTarjetas) {
            this.editarTarjetas = editarTarjetas;

    }
    
    public boolean isConsultarEmpleados() {
        return consultarEmpleados;
    }
    public void setConsultarEmpleados(boolean consultarEmpleados) {
        this.consultarEmpleados = consultarEmpleados;
    }
        
    public boolean isConsultarSucursales() {
        return consultarSucursales;
    }
    public void setConsultarSucursales(boolean consultarSucursales) {
        this.consultarSucursales = consultarSucursales;
    }
    
    public boolean isConsultarMaquinas() {
        return consultarMaquinas;
    }
    public void setConsultarMaquinas(boolean consultarMaquinas) {
        this.consultarMaquinas = consultarMaquinas;
    }
    
    public boolean isConsultarTarjetas() {
        return consultarTarjetas;
    }
    public void setConsultarTarjetas(boolean consultarTarjetas) {
        this.consultarTarjetas = consultarTarjetas;
    }
    
    public boolean isExportarCorte() {
        return exportarCorte;
    }
    public void setExportarCorte(boolean exportarCorte) {
        this.exportarCorte = exportarCorte;
    }
    
    public boolean isEditarFormato() {
        return editarFormato;
    }
    public void setEditarFormato(boolean editarFormato) {
        this.editarFormato = editarFormato;
    }
    
    public boolean isRespaldar() {
        return respaldar;
    }
    public void setRespaldar(boolean respaldar) {
        this.respaldar = respaldar;
    }

    public boolean isCapturaFormato() {
        return capturaFormato;
    }

    public void setCapturaFormato(boolean capturaFormato) {
        this.capturaFormato = capturaFormato;
    }

    public boolean isCapturaCorte() {
        return capturaCorte;
    }

    public void setCapturaCorte(boolean capturaCorte) {
        this.capturaCorte = capturaCorte;
    }

    public boolean isCapturaMovimiento() {
        return capturaMovimiento;
    }

    public void setCapturaMovimiento(boolean capturaMovimiento) {
        this.capturaMovimiento = capturaMovimiento;
    }

    public boolean isConsultaFormato() {
        return consultaFormato;
    }

    public void setConsultaFormato(boolean consultaFormato) {
        this.consultaFormato = consultaFormato;
    }

    public boolean isConsultaCorte() {
        return consultaCorte;
    }

    public void setConsultaCorte(boolean consultaCorte) {
        this.consultaCorte = consultaCorte;
    }

    public boolean isConsultaMovimiento() {
        return consultaMovimiento;
    }

    public void setConsultaMovimiento(boolean consultaMovimiento) {
        this.consultaMovimiento = consultaMovimiento;
    }

    public boolean isConsultaRutero() {
        return consultaRutero;
    }

    public void setConsultaRutero(boolean consultaRutero) {
        this.consultaRutero = consultaRutero;
    }

    public boolean isEditarRutero() {
        return editarRutero;
    }

    public void setEditarRutero(boolean editarRutero) {
        this.editarRutero = editarRutero;
    }

    public boolean isEditarCorte() {
        return editarCorte;
    }

    public void setEditarCorte(boolean editarCorte) {
        this.editarCorte = editarCorte;
    }  

    public boolean isConsultaEstadisticas() {
        return consultaEstadisticas;
    }

    public void setConsultaEstadisticas(boolean consultaEstadisticas) {
        this.consultaEstadisticas = consultaEstadisticas;
    }

    public boolean isConsultaEncargados() {
        return consultaEncargados;
    }

    public void setConsultaEncargados(boolean consultaEncargados) {
        this.consultaEncargados = consultaEncargados;
    }

    public boolean isCapturaVales() {
        return capturaVales;
    }

    public void setCapturaVales(boolean capturaVales) {
        this.capturaVales = capturaVales;
    }

    public boolean isConsultaVales() {
        return consultaVales;
    }

    public void setConsultaVales(boolean consultaVales) {
        this.consultaVales = consultaVales;
    }

    public boolean isConsultaEtiquetas() {
        return consultaEtiquetas;
    }

    public void setConsultaEtiquetas(boolean consultaEtiquetas) {
        this.consultaEtiquetas = consultaEtiquetas;
    }

    public boolean isConsultaPerifericos() {
        return consultaPerifericos;
    }

    public void setConsultaPerifericos(boolean consultaPerifericos) {
        this.consultaPerifericos = consultaPerifericos;
    }

    public boolean isConsultaChequeras() {
        return consultaChequeras;
    }

    public void setConsultaChequeras(boolean consultaChequeras) {
        this.consultaChequeras = consultaChequeras;
    }

    public boolean isEditarEtiquetas() {
        return editarEtiquetas;
    }

    public void setEditarEtiquetas(boolean editarEtiquetas) {
        this.editarEtiquetas = editarEtiquetas;
    }

    public boolean isEditarPerifericos() {
        return editarPerifericos;
    }

    public void setEditarPerifericos(boolean editarPerifericos) {
        this.editarPerifericos = editarPerifericos;
    }

    public boolean isEditarChequeras() {
        return editarChequeras;
    }

    public void setEditarChequeras(boolean editarChequeras) {
        this.editarChequeras = editarChequeras;
    }

    public boolean isEditarVales() {
        return editarVales;
    }

    public void setEditarVales(boolean editarVales) {
        this.editarVales = editarVales;
    }        
    
    @Override
    public String toString(){
        return usuario+" "+stringToBinary(contraseña)+" "+permiso
               +" "+capturaFormato
               +" "+consultaFormato               
               +" "+eliminarFormato
               +" "+pagarFormato
               +" "+exportarFormatos
               +" "+editarFormato
               +" "+capturaCorte
               +" "+consultaCorte
               +" "+eliminarCorte
               +" "+exportarCorte
               +" "+importarCortes
               +" "+asignarFalloCortes
               +" "+capturaMovimiento
               +" "+consultaMovimiento
               +" "+editarRutero
               +" "+editarEmpleados
               +" "+editarProducto  //leer en cargar
               +" "+editarSucursales  
               +" "+editarMaquinas
               +" "+editarTarjetas
               +" "+editarCorte
               +" "+consultaRutero 
               +" "+consultarEmpleados
               +" "+consultarProducto  //leer en cargar
               +" "+consultarSucursales
               +" "+consultarMaquinas
               +" "+consultarTarjetas
               +" "+eliminarMovimiento
               +" "+importarMovimiento
               +" "+ajusteMovimiento
               +" "+respaldar
               +" "+consultaEstadisticas
               +" "+consultaEncargados
               +" "+capturaVales
               +" "+consultaVales
               +" "+consultaEtiquetas
               +" "+consultaPerifericos
               +" "+consultaChequeras
               +" "+editarEtiquetas
               +" "+editarPerifericos
               +" "+editarChequeras               
               +" "+editarVales;               
    }
    
    public static ArrayList<Usuario> cargarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList();
        //leer de los usuarios los usuarios y contraseñas almacenarlas en un vector
        try {
            FileReader fr = new FileReader("Archivos/Control.data");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while((linea=br.readLine())!=null){
                token= new StringTokenizer(linea);
                
                String u= token.nextToken();
                String c= token.nextToken();                
                c = Utileria.binaryToString(c);
                
                String p = token.nextToken();
                
                boolean capturaFormato=Boolean.parseBoolean(token.nextToken());
                boolean consultaFormato=Boolean.parseBoolean(token.nextToken());
                boolean eliminarFormato=Boolean.parseBoolean(token.nextToken());
                boolean pagarFormato=Boolean.parseBoolean(token.nextToken());
                boolean exportarFormatos=Boolean.parseBoolean(token.nextToken());
                boolean editarFormato=Boolean.parseBoolean(token.nextToken());                
                boolean capturarCorte=Boolean.parseBoolean(token.nextToken());
                boolean consultarCorte=Boolean.parseBoolean(token.nextToken());
                boolean eliminarCorte=Boolean.parseBoolean(token.nextToken());
                boolean exportarCorte=Boolean.parseBoolean(token.nextToken());
                boolean importarCortes=Boolean.parseBoolean(token.nextToken());
                boolean asignarFalloCortes=Boolean.parseBoolean(token.nextToken());
                boolean capturaMovimiento=Boolean.parseBoolean(token.nextToken());
                boolean consultaMovimiento=Boolean.parseBoolean(token.nextToken());
                boolean editarRutero=Boolean.parseBoolean(token.nextToken());
                boolean editarEmpleados=Boolean.parseBoolean(token.nextToken());
                boolean editarProducto=Boolean.parseBoolean(token.nextToken());                
                boolean editarSucursales=Boolean.parseBoolean(token.nextToken());
                boolean editarMaquinas=Boolean.parseBoolean(token.nextToken());
                boolean editarTarjetas=Boolean.parseBoolean(token.nextToken());
                boolean editarCorte = Boolean.parseBoolean(token.nextToken());
                boolean consultaRutero=Boolean.parseBoolean(token.nextToken());                
                boolean consultarEmpleados=Boolean.parseBoolean(token.nextToken());
                boolean consultarProducto=Boolean.parseBoolean(token.nextToken());
                boolean consultarSucursales=Boolean.parseBoolean(token.nextToken());
                boolean consultarMaquinas=Boolean.parseBoolean(token.nextToken());
                boolean consultarTarjetas=Boolean.parseBoolean(token.nextToken());
                boolean eliminarMoviemiento=Boolean.parseBoolean(token.nextToken());
                boolean importarMovimiento=Boolean.parseBoolean(token.nextToken());
                boolean ajusteMovimiento=Boolean.parseBoolean(token.nextToken());
                boolean respaldar=Boolean.parseBoolean(token.nextToken());
                
                boolean consultaEstadisticas = Boolean.parseBoolean(token.nextToken());
                boolean consultaEncargados = Boolean.parseBoolean(token.nextToken());
                boolean capturaVales = Boolean.parseBoolean(token.nextToken());
                boolean consultaVales = Boolean.parseBoolean(token.nextToken());;
                boolean consultaEtiquetas = Boolean.parseBoolean(token.nextToken());
                boolean consultaPerifericos = Boolean.parseBoolean(token.nextToken());
                boolean consultaChequeras = Boolean.parseBoolean(token.nextToken());;
                boolean editarEtiquetas = Boolean.parseBoolean(token.nextToken());
                boolean editarPerifericos = Boolean.parseBoolean(token.nextToken());
                boolean editarChequeras = Boolean.parseBoolean(token.nextToken());;
                boolean editarVales = Boolean.parseBoolean(token.nextToken());
                
                Usuario usuario = new Usuario(u,c,p);
                //System.out.println(usuario);
                usuario.setEliminarFormato(eliminarFormato);
                usuario.setPagarFormato(pagarFormato);
                usuario.setExportarFormatos(exportarFormatos);
                usuario.setEliminarCorte(eliminarCorte);
                usuario.setAsignarFalloCortes(asignarFalloCortes);
                usuario.setExportarCortes(exportarCorte);
                usuario.setImportarCortes(importarCortes);
                usuario.setEditarEmpleados(editarEmpleados);
                usuario.setEditarProducto(editarProducto);
                usuario.setEditarSucursales(editarSucursales);                
                usuario.setEditarMaquinas(editarMaquinas);
                usuario.setEditarTarjetas(editarTarjetas);
                usuario.setEditarCorte(editarCorte);
                usuario.setConsultarEmpleados(consultarEmpleados);
                usuario.setConsultarProducto(consultarProducto);
                usuario.setConsultarSucursales(consultarSucursales);                
                usuario.setConsultarMaquinas(consultarMaquinas);
                usuario.setConsultarTarjetas(consultarTarjetas);
                usuario.setEditarFormato(editarFormato);
                usuario.setRespaldar(respaldar);
                usuario.setCapturaFormato(capturaFormato);
                usuario.setCapturaCorte(capturarCorte);
                usuario.setCapturaMovimiento(capturaMovimiento);
                usuario.setConsultaFormato(consultaFormato);
                usuario.setConsultaCorte(consultarCorte);
                usuario.setConsultaMovimiento(consultaMovimiento);
                usuario.setEditarRutero(editarRutero);
                usuario.setConsultaRutero(consultaRutero);
                usuario.setEliminarMovimiento(eliminarMoviemiento);
                usuario.setImportarMovimiento(importarMovimiento);
                usuario.setAjusteMovimiento(ajusteMovimiento);      
                
                usuario.setConsultaEstadisticas(consultaEstadisticas);
                usuario.setConsultaEncargados(consultaEncargados);
                usuario.setCapturaVales(capturaVales);
                usuario.setConsultaVales(consultaVales);
                usuario.setConsultaEtiquetas(consultaEtiquetas);
                usuario.setConsultaPerifericos(consultaPerifericos);
                usuario.setConsultaChequeras(consultaChequeras);
                usuario.setEditarEtiquetas(editarEtiquetas);
                usuario.setEditarChequeras(editarChequeras);
                usuario.setEditarPerifericos(editarPerifericos);
                usuario.setEditarVales(editarVales);
                
                usuarios.add(usuario);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Base de datos no encontrada o inutilizable, Carga de Usuarios", "Error", JOptionPane.ERROR_MESSAGE);            
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Base de datos no encontrada o inutilizable", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return usuarios;
    }
    
    public static void actualizarDB(ArrayList<Usuario> usuarios){
        try {
            FileWriter fw = new FileWriter("Archivos/Control.data");
            PrintWriter pw= new PrintWriter(fw);
            for(int k=0; k<usuarios.size(); k++){
                pw.println(usuarios.get(k).toString());
            }
            pw.close(); 
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Base de Datos Usuario no encontrada o inutilizable", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Base de Datos Usuario no encontrada o inutilizable", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
}