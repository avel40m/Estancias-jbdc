package estancias.persistencia;

import estancias.entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends DAO {
    
    public List<Cliente> listarClientes() throws Exception{
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql =  "SELECT * FROM clientes";
            consultarBase(sql);
            
            while (resultado.next()) {                
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultado.getInt("id_cliente"));
                cliente.setNombre(resultado.getString("nombre"));
                
                clientes.add(cliente);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return clientes;
    }
    
    public Cliente seleccionCliente(int id) throws Exception{
        Cliente cliente = null;
        try {
            String sql = "SELECT * FROM clientes WHERE id_cliente = '"+id+"'";
            consultarBase(sql);
            
            while (resultado.next()) {                
                cliente = new Cliente();
                cliente.setId_cliente(resultado.getInt("id_cliente"));
                cliente.setNombre(resultado.getString("nombre"));
            }
        } catch (Exception e) {
            throw e;
        }finally{
            desconectarBase();
        }
        return cliente;
    }
}
