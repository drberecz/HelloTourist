package hu.ps.ht.dao;

import hu.ps.ht.entity.Customer;
import hu.ps.ht.entity.CustomerGroup;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
public class CustomerGroupDAO {

    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public CustomerGroup findCustomerGroup(String groupname) {

        CustomerGroup customerGroup = null;
        try {
            customerGroup = (CustomerGroup) em
                    .createQuery("SELECT c FROM CustomerGroup c WHERE c.groupName=:gName").setParameter("gName", groupname).getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return customerGroup;
    }

    @Transactional
    public void updateCustomerGroup(String groupname, Customer customer) {
        CustomerGroup customerGroup = findCustomerGroup(groupname);
        List<Customer> customers = customerGroup.getCustomers();
        customers.add(customer);
        customerGroup.setCustomers(customers);
        em.merge(customerGroup);
    }

}
