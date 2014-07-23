package datos;

import negocio.Rol;
import java.util.Date;

/**
 *
 * @author Sergio Vacas
 */
public class RolDAOImplHibernate extends GenericDAOImplHibernate<Rol, Integer> implements RolDAO {

    @Override
    public void save(Rol entity) throws BusinessException {
        entity.setFechaAlta(new Date());
        super.save(entity);
    }
}
