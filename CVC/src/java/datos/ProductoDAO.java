package datos;

import negocio.Categoria;
import negocio.Producto;
import negocio.Usuario;
import java.util.List;

/**
 *
 * @author Sergio Vacas
 */
public interface ProductoDAO extends GenericDAO<Producto, Integer> {

    public List<Producto> getProductosCompras(Categoria categoria) throws BusinessException;

    public List<Producto> getProductosTrueques(Categoria categoria) throws BusinessException;

    public List<Producto> getProductosFromCategoria(Categoria categoria) throws BusinessException;

    List<Producto> getProductosFromIdUsuario(Usuario usuario);
}
