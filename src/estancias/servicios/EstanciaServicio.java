/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estancias.servicios;

import estancias.persistencia.EstanciasDAO;
import java.util.List;
import estancias.entidades.Estancias;
import java.util.Scanner;
import estancias.entidades.Cliente;
import estancias.entidades.Casas;

public class EstanciaServicio {
    
    private EstanciasDAO dao;
    private ClienteServicios clienteServicios;
    private CasaServicios casaServicios;
    
    public EstanciaServicio(){
        dao = new EstanciasDAO();
        clienteServicios = new ClienteServicios();
        casaServicios = new CasaServicios();
    }
    
    public void listdoClienteCasasComentario() throws Exception{
        List<Estancias> estancias = dao.obtenrClienteCasaComentario();
        
        System.out.println("Listado de cliente y sus descripcion");
        
        for (Estancias e : estancias) {
            System.out.println("Nombre del cliente: " + e.getNombreHuesped() + ", tipo de vivienda: " 
            + e.getCasas().getTipoVivienda() + ", descripcion del lugar: " + e.getCasas().getComentario().getComentario());
        }
    }
    
    public void listarEstanciaYCasas() throws  Exception{
        List<Estancias> listadoEstancias = dao.obtenerEstanciaYCasas();
        
        System.out.println("Lista de cliente que realizaron una estancia");
        
        for (Estancias l : listadoEstancias) {
            System.out.println("Datos del huesped: " + l.getNombreHuesped() + " - calle: " + l.getCasas().getCalle()
            + " - ciudad: " + l.getCasas().getCiudad() + " - Pais: " + l.getCasas().getPais() + " - codigo postal: "
            + l.getCasas().getCodigoPostal() + ". Vivienda tipo: " + l.getCasas().getTipoVivienda()
            + " - Fecha de alquiler: " + l.getFechaDesde() + " - " + l.getFechaHasta());
        }
    }
    
    public void listarComentarioLimpio() throws Exception{
        List<Estancias> estancias = dao.listarComentarioLimpias();
        System.out.println("Listar aquellas casas del pais Reino Unido donde su comentario fue 'LIMPIA'");
        for (Estancias estancia : estancias) {
            System.out.println("Estancias de " + estancia.getNombreHuesped() + ", casa alquilada es " + estancia.getCasas().getTipoVivienda()
            + ", pais al que corresponde " + estancia.getCasas().getPais() + 
                    " y su comentario fue " + estancia.getCasas().getComentario().getComentario());
        }
    }
    
    private void buscarFecha(String fecha) throws Exception{
       if(dao.validacionFecha(fecha) == true){
           System.out.println("Fecha disponible");
       }else{
           throw new Exception("No hay fecha disponible");
       }
    }
    
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void crearEstancia() throws Exception{
        System.out.println("Ingrese la fecha");
        String fechaDesde = leer.next();
        buscarFecha(fechaDesde);
        System.out.println("Hasta que fecha se quedaran");  
        String fechaHasta = leer.next();
        System.out.println("Seleccione un cliente");
        clienteServicios.listarClientes();
        int id_cliente = leer.nextInt();
        
        Cliente cliente  = clienteServicios.buscarCliente(id_cliente);
        
       if(cliente.getId_cliente() == 0 || cliente.getNombre().isEmpty()){
           throw new Exception("Fallo los datos");
        }
        System.out.println("Seleccionar casas"); 
       casaServicios.seleccionarIdCasa();
       int id_casa = leer.nextInt();
       
       dao.crearEstanciaNueva(id_cliente, id_casa, cliente.getNombre(),fechaDesde, fechaHasta);
    }
    
}
