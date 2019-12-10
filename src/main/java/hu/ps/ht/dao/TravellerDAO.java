package hu.ps.ht.dao;

import hu.ps.ht.entity.TravellerEntity;
import javax.ejb.Singleton;

@Singleton
public class TravellerDAO extends BaseDAO<TravellerEntity> {

    public TravellerEntity getTravellerByUsername(String username) {

        return (TravellerEntity) em.createQuery("Select t FROM TravellerEntity t WHERE t.userName=:pName")
                .setParameter("pName", username)
                .getSingleResult();

    }

}
