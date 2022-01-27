package estancias.persistencia;

import java.util.List;
import estancias.entidades.Casas;
import java.time.LocalDate;
import java.util.ArrayList;


public class CasaDAO extends DAO {
    
    public List<Casas> buscarPaisFecha() throws Exception{
        List<Casas> casas = new ArrayList();
        
        try {
            String sql = "SELECT * FROM casas WHERE pais = 'Reino Unido' and fecha_desde = '2020-08-01'";
            consultarBase(sql);
            
            while (resultado.next()) {                
                Casas casa = new Casas();
                casa.setId(resultado.getInt("id_casa"));
                casa.setCalle(resultado.getString("calle"));
                casa.setNumero(resultado.getInt("numero"));
                casa.setCodigoPostal(resultado.getInt("codigo_postal"));
                casa.setCiudad(resultado.getString("ciudad"));
                casa.setPais(resultado.getString("pais"));
                casa.setFechaDesde(resultado.getDate("fecha_desde"));
                casa.setFechaHasta(resultado.getDate("fecha_hasta"));
                casa.setTiempoMinimo(resultado.getInt("tiempo_minimo"));
                casa.setTiempoMaximo(resultado.getInt("tiempo_maximo"));
                casa.setPrecioHabitacion(resultado.getFloat("precio_habitacion"));
                casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                
                casas.add(casa);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return casas;        
    }
    
    public List<Casas> CasasDisponibleDesdeNumero(LocalDate fecha, int numero) throws Exception{
        List<Casas> casas = new ArrayList();
        try {
            String sql ="select * from casas where fecha_desde = '"+fecha+"' and numero = "+numero+"";
            consultarBase(sql);
            
            while(resultado.next()){
                Casas casa = new Casas();
                casa.setFechaDesde(resultado.getDate("fecha_desde"));
                casa.setFechaHasta(resultado.getDate("fecha_hasta"));
                casa.setNumero(resultado.getInt("numero"));
                casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                casas.add(casa);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return casas;
    }
    
    public List<Casas> listadoCasasAumentoPrecio() throws Exception{
        List<Casas> casas = new ArrayList<>();
        try {
            String sql = "select * from casas where pais = 'Reino unido';";
            consultarBase(sql);
            
            while (resultado.next()) {                
                Casas casa = new Casas();
                casa.setCalle(resultado.getString("calle"));
                casa.setCiudad(resultado.getString("ciudad"));
                casa.setPais(resultado.getString("pais"));
                casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                casa.setPrecioHabitacion(resultado.getFloat("precio_habitacion"));
                
                casas.add(casa);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return casas;
    }
    
    public List<Casas> cantidadCasasPorPaises() throws Exception{
        List<Casas> casas = new ArrayList<>();
        try {
            String sql = "select count(*) as 'cantidad',pais from casas group by pais;";
            consultarBase(sql);
            
            while (resultado.next()) {                
                Casas casa = new Casas();
                casa.setId(resultado.getInt("cantidad"));
                casa.setPais(resultado.getString("pais"));
                casas.add(casa);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return casas;
    }
    
    public List<Casas> seleccionarIdCasas() throws Exception{
        List<Casas> casas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM casas";
            consultarBase(sql);
            while (resultado.next()) {                
                Casas casa = new Casas();
                casa.setId(resultado.getInt("id_casa"));
                casa.setCalle(resultado.getString("calle"));
                
                casas.add(casa);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return casas;
    }
}
