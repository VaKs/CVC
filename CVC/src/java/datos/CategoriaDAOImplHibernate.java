package datos;

import java.util.Date;
import negocio.Categoria;

/**
 *
 * @author Sergio Vacas
 */
public class CategoriaDAOImplHibernate extends GenericDAOImplHibernate<Categoria, Integer> implements CategoriaDAO {

    @Override
    public void save(Categoria entity) throws BusinessException {
        entity.setFechaAlta(new Date());
        super.save(entity);
    }

}
