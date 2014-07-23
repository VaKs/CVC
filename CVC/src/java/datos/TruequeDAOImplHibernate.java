package datos;

import negocio.Trueque;
import negocio.Usuario;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Sergio Vacas
 */
public class TruequeDAOImplHibernate extends GenericDAOImplHibernate<Trueque, Integer> implements TruequeDAO {
    @Override
    public void save(Trueque entity) throws BusinessException {
        entity.setFechaTrueque(new Date());
        super.save(entity);
    }

    @Override
    public List<Trueque> getTruequeFromUsuario(Usuario usuario) throws BusinessException {
        Session session=sessionFactory.getCurrentSession();
        
        Integer idUsuario=usuario.getIdUsuario();
        
        Query query = session.createQuery("SELECT t FROM Trueque t WHERE t.cliente1.idUsuario=? or t.cliente2.idUsuario=?");
        query.setInteger(0,idUsuario);
        query.setInteger(1,idUsuario);
        
        List<Trueque> trueques = query.list();

        return trueques;
    }
    
    
}
