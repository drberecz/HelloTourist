package hu.ps.ht.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_GROUP")
public class CustomerGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "GROUP_NAME", unique = true)
    private String groupName;

    @ManyToMany
    @JoinTable(name = "CUSTOMER_CUSTOMER_GROUP",
            joinColumns = @JoinColumn(name = "GROUP_NAME", referencedColumnName = "GROUP_NAME"),
            inverseJoinColumns = @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL"))
    private List<Customer> customers;

    public CustomerGroup() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
