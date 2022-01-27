package estancias.servicios;

import estancias.persistencia.ClienteDAO;
import java.util.List;
import estancias.entidades.Cliente;

public class ClienteServicios {
    private ClienteDAO dao;
    
    public ClienteServicios(){
        this.dao = new ClienteDAO();
    }
    
    public void listarClientes() throws Exception{
        List<Cliente> clientes = dao.listarClientes();
        
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId_cliente() + ", nombre " + cliente.getNombre());
        }
    }
    
    public Cliente buscarCliente(int id) throws Exception{
        Cliente cliente = dao.seleccionCliente(id);
        
        return cliente;
    }
}
