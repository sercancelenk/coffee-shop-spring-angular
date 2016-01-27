package sr.api.core.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sr.api.core.persistence.dao.ICustomerDAO;
import sr.api.core.persistence.domain.Customer;
import sr.api.core.business.service.ICustomerService;

/**
 * Created by byzas on 15/01/16.
 */

@Service(value = "customerService")
public class CustomerServiceImpl implements ICustomerService {

    @Autowired LoggerServiceImpl loggerService;

    @Autowired
    ICustomerDAO customerDAO;

    @Override
    public Customer getByUsername(String userName) {
        try {
            return customerDAO.getByUsername(userName);
        }catch (Exception ex){
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }

    @Override
    public Customer getUserByID(Long id){
        try {
            return customerDAO.getById(id);
        }catch (Exception ex){
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }
}
