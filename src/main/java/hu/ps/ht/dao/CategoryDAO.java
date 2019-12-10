package hu.ps.ht.dao;

import hu.ps.ht.entity.CategoryEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.transaction.Transactional;

@Singleton
@Local
public class CategoryDAO extends BaseDAO<CategoryEntity> {

    @Transactional
    public List<CategoryEntity> findById(Long id) {

        return em.createQuery("select c from  CategoryEntity c WHERE c.id=:catId")
                .setParameter("catId", id)
                .getResultList();

    }

    @Transactional
    public CategoryEntity findByName(String categoryName) {

        return (CategoryEntity) em.createQuery("SELECT c FROM CategoryEntity c WHERE c.name=:catName")
                .setParameter("catName", categoryName)
                .getSingleResult();

    }

}
