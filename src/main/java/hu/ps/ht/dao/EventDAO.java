package hu.ps.ht.dao;

import hu.ps.ht.entity.EventEntity;
import hu.ps.ht.enumerated.Region;
import java.util.List;
import javax.ejb.Singleton;
import javax.transaction.Transactional;

@Singleton
public class EventDAO extends BaseDAO<EventEntity> {

    @Transactional
    public List<EventEntity> getAllEvents() {

        return em.createQuery("SELECT e FROM event e").getResultList();
    }

    @Transactional
    public List<EventEntity> getEventListByRegion(Region region) {

        return em.createQuery("SELECT e FROM event e WHERE e.region=:regio")
                .setParameter("regio", region)
                .getResultList();
    }

    @Transactional
    public List<EventEntity> getEventEntityListByGuide(String guidename) {

        return em.createQuery("SELECT e FROM event e WHERE e.guide.userName=:pGuidename")
                .setParameter("pGuidename", guidename)
                .getResultList();
    }

    @Transactional
    public List<EventEntity> findEventsByCategory(String category) {

        return em.createQuery("SELECT e FROM event e WHERE e.category.name=:pCat")
                .setParameter("pCat", category)
                .getResultList();

    }

}
