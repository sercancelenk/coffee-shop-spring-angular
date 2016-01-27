package sr.api.core.persistence.dao;

import sr.api.core.persistence.domain.Customer;

/**
 * Created by byzas on 15/01/16.
 */
public interface ICustomerDAO extends GenericDAO<Customer, Long> {
    Customer getByUsername(String userName);
}
