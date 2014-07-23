package datos;

import negocio.Usuario;
import negocio.Venta;
import java.util.List;


/**
 *
 * @author Sergio Vacas
 */
public interface VentaDAO extends GenericDAO<Venta, Integer> {
    public List<Venta> getVentaFromVendedor(Usuario usuario) throws BusinessException;
    public List<Venta> getVentaFromComprador(Usuario usuario) throws BusinessException;
}
