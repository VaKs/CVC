package datos;

import negocio.Usuario;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sergio Vacas
 */
public class UsuarioDAOImplHibernate extends GenericDAOImplHibernate<Usuario, Integer> implements UsuarioDAO {
    
    @Override
    public void save(Usuario entity) throws BusinessException {
        entity.setFechaAlta(new Date());
        super.save(entity);
    }

    @Override
    public Usuario getUsuarioFromLogin(String login) throws BusinessException {
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("login", login);

        List<Usuario> usuarios = search(filter);
        if (usuarios.size()==0) {
            return null;
        } else if (usuarios.size()==1) {
            return usuarios.get(0);
        } else {
            throw new RuntimeException("Se ha encontrado " + usuarios.size() + " usuarios con el login:"+login);
        }
        
    }
}
