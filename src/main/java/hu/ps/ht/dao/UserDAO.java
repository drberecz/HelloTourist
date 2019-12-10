package hu.ps.ht.dao;

import hu.ps.ht.entity.Customer;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Csaba
 */
@Singleton
public class UserDAO {

    @PersistenceContext
    protected EntityManager em;

    public Customer checkIfUserExists(String username) {
        Customer customer = null;
        try {
            customer = (Customer) em.createQuery("SELECT u FROM Customer u WHERE u.email=:pEmail")
                    .setParameter("pEmail", username).getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return customer;
    }

    @Transactional
    public void removeUser(Customer user) {
        em.remove(user);
    }

    @Transactional
    public void updateUser(Customer user) {
        em.merge(user);
    }

    public void insertCustomer(Customer entity) {
        em.persist(entity);
    }

}
