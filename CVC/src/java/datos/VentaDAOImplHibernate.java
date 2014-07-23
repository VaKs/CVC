package datos;

import negocio.Usuario;
import negocio.Venta;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Sergio Vacas
 */
public class VentaDAOImplHibernate extends GenericDAOImplHibernate<Venta, Integer> implements VentaDAO {
@Override
    public void save(Venta entity) throws BusinessException {
        entity.setFechaVenta(new Date());
        super.save(entity);
    }


    @Override
    public List<Venta> getVentaFromVendedor(Usuario usuario) throws BusinessException {
        Session session=sessionFactory.getCurrentSession();
        
        Integer idUsuario=usuario.getIdUsuario();
        
        Query query = session.createQuery("SELECT v FROM Venta v WHERE v.vendedor.idUsuario=?");
        query.setInteger(0,idUsuario);
        
        List<Venta> ventas = query.list();

        return ventas;

    }
    
    @Override
    public List<Venta> getVentaFromComprador(Usuario usuario) throws BusinessException {
        Session session=sessionFactory.getCurrentSession();
        
        Integer idUsuario=usuario.getIdUsuario();
        
        Query query = session.createQuery("SELECT v FROM Venta v WHERE v.comprador.idUsuario=?");
        query.setInteger(0,idUsuario);
        
        List<Venta> ventas = query.list();

        return ventas;

    }
   
}
