/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import negocio.Trueque;
import negocio.Usuario;
import java.util.List;

/**
 *
 * @author Sergio Vacas
 */
public interface TruequeDAO extends GenericDAO<Trueque, Integer>{
    public List<Trueque> getTruequeFromUsuario(Usuario usuario) throws BusinessException;
}
