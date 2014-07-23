package datos;

import negocio.Usuario;

/**
 *
 * @author Sergio Vacas
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {
    Usuario getUsuarioFromLogin(String login) throws BusinessException;
}
