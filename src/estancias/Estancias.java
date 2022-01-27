/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estancias;

import estancias.servicios.CasaServicios;
import estancias.servicios.ClienteServicios;
import estancias.servicios.EstanciaServicio;
import estancias.servicios.FamiliaServicios;
import java.time.*;

/**
 *
 * @author usuario
 */
public class Estancias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FamiliaServicios familiaServicios = new FamiliaServicios();
        CasaServicios casaServicios = new CasaServicios();
        EstanciaServicio estanciaServicio = new EstanciaServicio();
        ClienteServicios clienteServicios = new ClienteServicios();
        
        try {
            estanciaServicio.crearEstancia();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
