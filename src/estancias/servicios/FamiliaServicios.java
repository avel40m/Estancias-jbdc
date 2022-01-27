package estancias.servicios;

import estancias.persistencia.FamiliaDAO;
import java.util.List;
import estancias.entidades.Familia;

public class FamiliaServicios {
    
    private FamiliaDAO dao;
    
    public FamiliaServicios(){
        this.dao = new FamiliaDAO();
    }
    
    public void ListarFamilias() throws Exception{
        List<Familia> familias = dao.ListarFamiliaSegunEdadEHijos();
        System.out.println("Listar familia que tinen al manos 3 hijos y edad maxima de 10");
        for (Familia familia : familias) {
            System.out.println(familia);
        }
    }
    
    public void ListarFamiliaSoloHotmail() throws Exception{
        List<Familia> familias = dao.ListarFamiliaQueTenganHotmail();
        System.out.println("Familia cuyo direccion sean Hotmail");
        for (Familia familia : familias) {
            System.out.println(familia);
        }
    }
}
