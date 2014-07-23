package datos;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sergio Vacas
 */
public class GenericDAOImplHibernate<TIPO_ENTIDAD, TIPO_PK extends Serializable> implements GenericDAO<TIPO_ENTIDAD, TIPO_PK> {

    @Autowired
    SessionFactory sessionFactory;
    private Class<TIPO_ENTIDAD> entityType;

    public GenericDAOImplHibernate() {
        entityType = (Class<TIPO_ENTIDAD>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public TIPO_ENTIDAD create() throws BusinessException{
        try {
            return entityType.newInstance();
        } 
        
        catch (javax.validation.ConstraintViolationException cve){
            throw new BusinessException(cve); 
        }
        catch (org.hibernate.exception.ConstraintViolationException cve){
            throw new BusinessException(cve); 
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void save(TIPO_ENTIDAD entity) throws BusinessException{
        Session session = sessionFactory.getCurrentSession();
        try{
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        }
        catch (javax.validation.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }
        catch (org.hibernate.exception.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }

    }

    @Override
    public void update(TIPO_ENTIDAD entity) throws BusinessException {
        Session session = sessionFactory.getCurrentSession();
        try{
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        }
        catch (javax.validation.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }
        catch (org.hibernate.exception.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }

    }

    @Override
    public TIPO_ENTIDAD get(TIPO_PK id) throws BusinessException{
        Session session = sessionFactory.getCurrentSession();
        
        try{
            TIPO_ENTIDAD entity = (TIPO_ENTIDAD) session.get(entityType, id);

            return entity;
        }
        catch (javax.validation.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }
        catch (org.hibernate.exception.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }
      
    }

    @Override
    public void delete(TIPO_PK id) throws BusinessException {
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            TIPO_ENTIDAD entity = get(id);
            session.delete(entity);
            session.getTransaction().commit();
        }
        catch (javax.validation.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }
        catch (org.hibernate.exception.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }

    }

    @Override
    public List<TIPO_ENTIDAD> search(Map<String, Object> filter) throws BusinessException{
        
        try {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(entityType);
        if (filter != null) {
            for (String propertyName : filter.keySet()) {
                Object value = filter.get(propertyName);
                
                if (value!=null) {
                    if (value instanceof String) {
                        criteria.add(Restrictions.like(propertyName, "%"+value+"%"));
                    } else {
                        criteria.add(Restrictions.eq(propertyName, value));
                    }
                }
            }
        }

        List<TIPO_ENTIDAD> entities = criteria.list();
        
        return entities;
        }
        catch (javax.validation.ConstraintViolationException cve){
            throw new BusinessException(cve); 
        }
        catch (org.hibernate.exception.ConstraintViolationException cve){
            throw new BusinessException(cve);
        }

}
}
