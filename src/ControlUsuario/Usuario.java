package ControlUsuario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.codehaus.jackson.map.ObjectMapper;

public class Usuario {

    private String usuario;
    private String contra;
    private String permiso; //usuario o administrador

    boolean eliminarFormato = true, pagarFormato = true, exportarFormatos = true, eliminarCorte = true, exportarCorte = true, importarCortes = true, asignarFalloCortes = true;
    boolean editarEmpleados = true, editarSucursales = true, editarMaquinas = true, editarTarjetas = true, editarFormato = true, respaldar = true;
    boolean consultarEmpleados = true, consultarSucursales = true, consultarMaquinas = true, consultarTarjetas = true;
    boolean editarProducto = true, consultarProducto = true, editarCorte = true;

    boolean capturaFormato = true, capturaCorte = true, capturaMovimiento = true;
    boolean consultaFormato = true, consultaCorte = true, consultaMovimiento = true;
    boolean consultaRutero = true, editarRutero = true;

    boolean eliminarMovimiento = true, importarMovimiento = true, ajusteMovimiento = true;

    boolean consultaEstadisticas = true, consultaEncargados = true, capturaVales = true, consultaVales = true, editarVales = true;
    boolean consultaEtiquetas = true, consultaPerifericos = true, consultaChequeras = true;
    boolean editarEtiquetas = true, editarPerifericos = true, editarChequeras = true;

    //permisos de nominda
    boolean consultaNomina = true, consultaIncidentes = true, consultaPrestamos = true, capturaIncidentes = true, capturaPrestamos = true;

    public Usuario() {
    }

    public Usuario(String usuario, String contraseña, String permiso) {
        this.usuario = usuario;
        this.contra = contraseña;
        this.permiso = permiso;
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

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
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

    public boolean isConsultaNomina() {
        return consultaNomina;
    }

    public void setConsultaNomina(boolean consultaNomina) {
        this.consultaNomina = consultaNomina;
    }

    public boolean isConsultaIncidentes() {
        return consultaIncidentes;
    }

    public void setConsultaIncidentes(boolean consultaIncidentes) {
        this.consultaIncidentes = consultaIncidentes;
    }

    public boolean isConsultaPrestamos() {
        return consultaPrestamos;
    }

    public void setConsultaPrestamos(boolean consultaPrestamos) {
        this.consultaPrestamos = consultaPrestamos;
    }

    public boolean isCapturaIncidentes() {
        return capturaIncidentes;
    }

    public void setCapturaIncidentes(boolean capturaIncidentes) {
        this.capturaIncidentes = capturaIncidentes;
    }

    public boolean isCapturaPrestamos() {
        return capturaPrestamos;
    }

    public void setCapturaPrestamos(boolean capturaPrestamos) {
        this.capturaPrestamos = capturaPrestamos;
    }

    public static ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList();
        //leer de los usuarios los usuarios y contraseñas almacenarlas en un vector
        try {
            FileReader fr = new FileReader("Archivos/Control.data");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            ObjectMapper mapper = new ObjectMapper();
            while ((linea = br.readLine()) != null) {
                Usuario usuario = mapper.readValue(linea, Usuario.class);
                usuarios.add(usuario);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Base de datos no encontrada o inutilizable, Carga de Usuarios", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Base de datos no encontrada o inutilizable", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return usuarios;
    }

    public static void actualizarDB(ArrayList<Usuario> usuarios) {
        try {
            FileWriter fw = new FileWriter("Archivos/Control.data");
            PrintWriter pw = new PrintWriter(fw);
            ObjectMapper mapper = new ObjectMapper();
            for (Usuario usuario : usuarios) {
                pw.println(mapper.writeValueAsString(usuario));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Base de Datos Usuario no encontrada o inutilizable", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Base de Datos Usuario no encontrada o inutilizable", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", contra=" + contra + ", permiso=" + permiso + ", eliminarFormato=" + eliminarFormato + ", pagarFormato=" + pagarFormato + ", exportarFormatos=" + exportarFormatos + ", eliminarCorte=" + eliminarCorte + ", exportarCorte=" + exportarCorte + ", importarCortes=" + importarCortes + ", asignarFalloCortes=" + asignarFalloCortes + ", editarEmpleados=" + editarEmpleados + ", editarSucursales=" + editarSucursales + ", editarMaquinas=" + editarMaquinas + ", editarTarjetas=" + editarTarjetas + ", editarFormato=" + editarFormato + ", respaldar=" + respaldar + ", consultarEmpleados=" + consultarEmpleados + ", consultarSucursales=" + consultarSucursales + ", consultarMaquinas=" + consultarMaquinas + ", consultarTarjetas=" + consultarTarjetas + ", editarProducto=" + editarProducto + ", consultarProducto=" + consultarProducto + ", editarCorte=" + editarCorte + ", capturaFormato=" + capturaFormato + ", capturaCorte=" + capturaCorte + ", capturaMovimiento=" + capturaMovimiento + ", consultaFormato=" + consultaFormato + ", consultaCorte=" + consultaCorte + ", consultaMovimiento=" + consultaMovimiento + ", consultaRutero=" + consultaRutero + ", editarRutero=" + editarRutero + ", eliminarMovimiento=" + eliminarMovimiento + ", importarMovimiento=" + importarMovimiento + ", ajusteMovimiento=" + ajusteMovimiento + ", consultaEstadisticas=" + consultaEstadisticas + ", consultaEncargados=" + consultaEncargados + ", capturaVales=" + capturaVales + ", consultaVales=" + consultaVales + ", editarVales=" + editarVales + ", consultaEtiquetas=" + consultaEtiquetas + ", consultaPerifericos=" + consultaPerifericos + ", consultaChequeras=" + consultaChequeras + ", editarEtiquetas=" + editarEtiquetas + ", editarPerifericos=" + editarPerifericos + ", editarChequeras=" + editarChequeras + ", consultaNomina=" + consultaNomina + ", consultaIncidentes=" + consultaIncidentes + ", consultaPrestamos=" + consultaPrestamos + ", capturaIncidentes=" + capturaIncidentes + ", capturaPrestamos=" + capturaPrestamos + '}';
    }    

}
