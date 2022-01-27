/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estancias.persistencia;

import java.util.List;
import estancias.entidades.Familia;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class FamiliaDAO extends DAO {

    public List<Familia> ListarFamiliaSegunEdadEHijos() throws Exception {
        List<Familia> familias = new ArrayList();
        try {
            String sql = "SELECT * FROM familias where num_hijos >= 3 and edad_maxima < 10";
            consultarBase(sql);

            while (resultado.next()) {
                Familia familia = new Familia();
                familia.setId(resultado.getInt("id_familia"));
                familia.setNombre(resultado.getString("nombre"));
                familia.setEdad_minima(resultado.getInt("edad_minima"));
                familia.setEdad_maxima(resultado.getInt("edad_maxima"));
                familia.setNum_hijos(resultado.getInt("num_hijos"));
                familia.setEmail(resultado.getString("email"));
                familias.add(familia);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
        return familias;
    }

    public List<Familia> ListarFamiliaQueTenganHotmail() throws Exception {
        List<Familia> familias = new ArrayList();
        try {
            String sql = "SELECT * FROM familias WHERE email LIKE '%hotmail%'";
            consultarBase(sql);

            while (resultado.next()) {
                Familia familia = new Familia();
                familia.setId(resultado.getInt("id_familia"));
                familia.setNombre(resultado.getString("nombre"));
                familia.setEdad_minima(resultado.getInt("edad_minima"));
                familia.setEdad_maxima(resultado.getInt("edad_maxima"));
                familia.setNum_hijos(resultado.getInt("num_hijos"));
                familia.setEmail(resultado.getString("email"));
                familias.add(familia);
            }
        } catch (Exception e) {
            throw e;
        }
        return familias;
    }
}
