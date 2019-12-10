package hu.ps.ht.dao;

import hu.ps.ht.entity.GuideEntity;
import hu.ps.ht.enumerated.Region;
import java.util.List;
import javax.ejb.Singleton;
import javax.transaction.Transactional;

@Singleton
public class GuideDAO extends BaseDAO<GuideEntity> {

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GuideEntity> getGuideListByRegion(Region region) {

        return em.createQuery("SELECT g FROM guide g WHERE g.operatesInRegion=:regio")
                .setParameter("regio", region)
                .getResultList();

    }

    @Transactional
    public GuideEntity getGuideByUserName(String userName) {

        return (GuideEntity) em.createQuery("SELECT g FROM guide g  WHERE g.userName=:uName")
                .setParameter("uName", userName)
                .getSingleResult();
    }

}
