package hu.ps.ht.dao;

import hu.ps.ht.entity.BaseEntity;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class BaseDAO<T extends BaseEntity> {

    @PersistenceContext(name = "hellotouristPU")
    protected EntityManager em;

    @Transactional
    public T updateEntity(T entity) {
        return em.merge(entity);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public T find(Class<T> clazz, Long id) {

        return em.find(clazz, id);
    }

    @Transactional
    public void delete(Class<T> clazz, Long id) {
        T thingy = em.find(clazz, id);
        if (thingy != null) {
            em.remove(thingy);
        }

    }

    @Transactional
    public void createEntity(T Entity) {
        em.persist(Entity);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<T> findAll(Class<T> clazz) {

        try {
            return em.createQuery("select w from "
                    + clazz.getSimpleName() + " w", clazz).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
