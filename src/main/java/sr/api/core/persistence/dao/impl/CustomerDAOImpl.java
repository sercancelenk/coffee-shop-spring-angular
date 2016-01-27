package sr.api.core.persistence.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import sr.api.core.persistence.dao.ICustomerDAO;
import sr.api.core.persistence.domain.Customer;

/**
 * Created by byzas on 15/01/16.
 */

@Repository(value = "customerDAO")
public class CustomerDAOImpl extends AbstractHibernateDAO<Customer, Long> implements ICustomerDAO {
    @Override
    public Customer getByUsername(String userName) {
        Customer customer = null;
        try{
            customer = (Customer) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(persistentClass).add(Restrictions.eq("username", userName))).get(0);
        }catch (Exception ex){
            //TODO: Loglama
        }
        return customer;
    }
}
