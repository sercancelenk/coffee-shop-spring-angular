package sr.api.core.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sr.api.core.business.service.IProductService;
import sr.api.core.persistence.dao.IProductDAO;
import sr.api.core.persistence.domain.Product;
import sr.api.core.util.enums.ProductType;

import java.util.List;

/**
 * Created by byzas on 15/01/16.
 */

@Service(value = "productService")
@Transactional
public class ProductService implements IProductService {

    @Autowired
    LoggerServiceImpl loggerService;

    @Autowired
    IProductDAO productDAO;

    @Override
    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        try{
            return productDAO.getProductById(id);
        }catch (Exception ex){
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }

    @Override
    public List<Product> getAllProductsByType(ProductType type) {
        try{
            return productDAO.getAllProductsByType(type);
        }catch (Exception ex){
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }

    @Override
    public void saveProduct(Product product) {
        try {
            productDAO.saveProduct(product);
        }catch (Exception ex){
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        }catch (Exception ex){
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }
}
