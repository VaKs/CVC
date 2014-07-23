package datos;

import java.util.Date;
import negocio.Categoria;
import negocio.Producto;
import negocio.Usuario;
import java.util.List;
import org.hibernate.Query;

import org.hibernate.Session;

/**
 *
 * @author Sergio Vacas
 */
public class ProductoDAOImplHibernate extends GenericDAOImplHibernate<Producto, Integer> implements ProductoDAO {

    @Override
    public void save(Producto entity) throws BusinessException {
        entity.setFechaAlta(new Date());
        super.save(entity);
    }

    @Override
    public List<Producto> getProductosTrueques(Categoria categoria) throws BusinessException {

        Session session = sessionFactory.getCurrentSession();

        Query query;
        if (categoria == null) {
            query = session.createQuery("SELECT p FROM Producto p WHERE permiteMostrar=1 and permiteCambiar=1");
        } else {
            Integer idCategoria = categoria.getIdCategoria();
            query = session.createQuery("SELECT p FROM Producto p WHERE idCategoria=? and permiteMostrar=1 and permiteCambiar=1");
            query.setInteger(0, idCategoria);
        }

        List<Producto> productosTrueques = query.list();

        return productosTrueques;

    }

    @Override
    public List<Producto> getProductosFromIdUsuario(Usuario usuario) {
        Integer idUsuario = usuario.getIdUsuario();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Producto p WHERE p.usuario.idUsuario=? ");
        query.setInteger(0, idUsuario);
        List<Producto> productos = query.list();
        return productos;

    }

    @Override
    public List<Producto> getProductosCompras(Categoria categoria) throws BusinessException {
        Session session = sessionFactory.getCurrentSession();

        Query query;
        if (categoria == null) {
            query = session.createQuery("SELECT p FROM Producto p WHERE permiteMostrar=1 and permiteVender=1");
        } else {
            Integer idCategoria = categoria.getIdCategoria();
            query = session.createQuery("SELECT p FROM Producto p WHERE idCategoria=? and permiteMostrar=1 and permiteVender=1");
            query.setInteger(0, idCategoria);
        }

        List<Producto> productosCompras = query.list();

        return productosCompras;
    }
    @Override
    public List<Producto> getProductosFromCategoria(Categoria categoria) throws BusinessException {
        Session session = sessionFactory.getCurrentSession();

        Query query;
        if (categoria == null) {
            query = session.createQuery("SELECT p FROM Producto p WHERE permiteMostrar=1");
        } else {
            Integer idCategoria = categoria.getIdCategoria();
            query = session.createQuery("SELECT p FROM Producto p WHERE idCategoria=? and permiteMostrar=1");
            query.setInteger(0, idCategoria);
        }

        List<Producto> productosCategoria = query.list();

        return productosCategoria;
    }
 
}

