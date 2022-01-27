package estancias.servicios;

import estancias.persistencia.CasaDAO;
import java.util.List;
import java.util.Date;
import estancias.entidades.Casas;
import java.time.*;
import java.util.ArrayList;

public class CasaServicios {
    
    private CasaDAO dao;
    
    public CasaServicios(){
        this.dao = new CasaDAO();
    }
    
    public void buscarCasaPaisFecha() throws Exception{
        List<Casas> casas = dao.buscarPaisFecha();
        System.out.println("Buscar casa comprendido desde 1 de agosto 2020 en el pais Reino Unido");
        for (Casas casa : casas) {
            System.out.println(casa);
        }
    }
    
    private List<Casas> listarCasaDisponible(LocalDate fecha,int numero) throws Exception{
        List<Casas> casas = dao.CasasDisponibleDesdeNumero(fecha, numero);
        return casas;
    }
    
    public void ListarCasasDisponibleFechaNumero(LocalDate fecha, int numero) throws Exception{
        List<Casas> casas = listarCasaDisponible(fecha, numero);
        if(casas.isEmpty() == true){
            System.out.println("No existe casas disponible");
        }else{
            System.out.println("Estas son las casas disponible");
            for (Casas casa : casas) {
                System.out.println(casa.getFechaDesde() + " - " + casa.getFechaHasta() + " - " + casa.getNumero() +
                        " - " + casa.getTipoVivienda());
            }
        }   
    }
    
    public void modificacionPrecioAumento() throws Exception{
        List<Casas> casas = dao.listadoCasasAumentoPrecio();
        System.out.println("Listado de precio aumeto el 5% del pais reino unido");
        
        for (Casas casa : casas) {
            double precio = casa.getPrecioHabitacion() + (casa.getPrecioHabitacion() * 0.5) ;
            
            System.out.println("Direccion de la casa: calle " + casa.getCalle() + ", ciudad " + casa.getCiudad()
           + " pais " + casa.getPais() + ", tipo de vivienda " + casa.getTipoVivienda() + 
                   " y el precio es $" + precio);
        }
    }
    
    public void contadorDeCasasPorPaises() throws Exception{
        List<Casas> casas = dao.cantidadCasasPorPaises();
        System.out.println("Número de casas por paises");
        
        for (Casas casa : casas) {
            System.out.println("Pais: " + casa.getPais() + " tiene un total de " + casa.getId() + " numero de casa.");
        }
    }
    
    public void seleccionarIdCasa() throws Exception{
        List<Casas> casas = dao.seleccionarIdCasas();
        System.out.println("Seleccionar ID");
        
        for (Casas casa : casas) {
            System.out.println(casa.getId() + " - " + casa.getCalle());
        }
    }
}
