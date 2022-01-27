package estancias.persistencia;

import estancias.entidades.Casas;
import estancias.entidades.Comentario;
import java.util.List;
import estancias.entidades.Estancias;
import java.util.ArrayList;

public class EstanciasDAO extends DAO {
    
    public List<Estancias> obtenrClienteCasaComentario() throws Exception{
        List<Estancias> estancias = new ArrayList<>();
        try {
            String sql = "select e.nombre_huesped,c.tipo_vivienda,co.comentario from estancias e inner join casas c on e.id_casa = c.id_casa inner join comentarios co on c.id_casa = co.id_casa;";
            
            consultarBase(sql);
            
            while (resultado.next()) {                
                Estancias estancia = new Estancias();
                estancia.setNombreHuesped(resultado.getString("nombre_huesped"));
                
                Casas casa = new Casas();
                casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                
                Comentario comentario = new Comentario();
                
                comentario.setComentario(resultado.getString("comentario"));
                
                casa.setComentario(comentario);
                
                estancia.setCasas(casa);
                
                estancias.add(estancia);                
            }
            
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return estancias;
    }
    
    public List<Estancias> obtenerEstanciaYCasas() throws Exception{
        List<Estancias> estancias = new ArrayList<>();
        
        try {
            String sql = "select * from estancias e inner join casas c on e.id_casa = c.id_casa;";
            consultarBase(sql);
            
            while (resultado.next()) {                
                Estancias estancia = new Estancias();
                estancia.setNombreHuesped(resultado.getString("nombre_huesped"));
                estancia.setFechaDesde(resultado.getDate("fecha_desde"));
                estancia.setFechaHasta(resultado.getDate("fecha_hasta"));
                
                Casas casa = new Casas();
                casa.setCalle(resultado.getString("calle"));
                casa.setCodigoPostal(resultado.getInt("codigo_postal"));
                casa.setCiudad(resultado.getString("ciudad"));
                casa.setPais(resultado.getString("pais"));
                casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                
                estancia.setCasas(casa);
                
                estancias.add(estancia);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return estancias;
    }
    
    public List<Estancias> listarComentarioLimpias() throws Exception{
        List<Estancias> estancias = new ArrayList<>();
        try {
            String sql = "select * from estancias e inner join casas ca on e.id_casa = ca.id_casa\n" +
            "inner join comentarios co on ca.id_casa = co.id_casa where co.comentario like '%limpia%' and ca.pais like '%reino unido%' group by co.comentario;";
            consultarBase(sql);
            
            while (resultado.next()) {                
                Estancias estancia = new Estancias();
                estancia.setNombreHuesped(resultado.getString("nombre_huesped"));
                Casas casa = new Casas();
                casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                casa.setPais(resultado.getString("pais"));
                Comentario comentario = new Comentario();
                comentario.setComentario(resultado.getString("comentario"));
                casa.setComentario(comentario);
                estancia.setCasas(casa);
                
                estancias.add(estancia);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return estancias;
    }
    
    public boolean validacionFecha(String fecha) throws Exception{
        boolean band = false;
        try {
            String sql = "select * from estancias where fecha_desde ='"+fecha+"'";
            consultarBase(sql);
            
            band = resultado.first();
            
        } catch (Exception e) {
            throw e;
        }
        return band;
    }
    
    public void crearEstanciaNueva(int id_cliente,int id_casa, String nombre,String fechaDesde,String fechaHasta) throws Exception{
        try {
            String sql = "INSERT INTO estancias VALUES (null,'"+id_cliente+"','"+id_casa+"','"+nombre+"','"+fechaDesde+"','"+fechaHasta+"')";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
