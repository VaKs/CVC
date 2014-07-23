package datos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sergio Vacas
 */
public interface GenericDAO<TIPO_ENTIDAD, TIPO_PK extends Serializable> {

    TIPO_ENTIDAD create() throws BusinessException;

    void save(TIPO_ENTIDAD entity) throws BusinessException;

    void update(TIPO_ENTIDAD entity) throws BusinessException;

    TIPO_ENTIDAD get(TIPO_PK id) throws BusinessException;

    void delete(TIPO_PK id) throws BusinessException;

    List<TIPO_ENTIDAD> search(Map<String, Object> filter) throws BusinessException;
}
