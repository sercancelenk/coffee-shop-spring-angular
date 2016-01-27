package sr.api.core.business.service;

import sr.api.core.persistence.domain.Customer;

/**
 * Created by byzas on 15/01/16.
 */
public interface ICustomerService {
    Customer getByUsername(String userName);
    Customer getUserByID(Long id);
}
