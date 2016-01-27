package sr.api.core.persistence.dao;

import sr.api.core.persistence.domain.Product;
import sr.api.core.util.enums.ProductType;

import java.util.List;

/**
 * Created by byzas on 15/01/16.
 */
public interface IProductDAO extends GenericDAO<Product, Long> {

    List<Product> getAllProducts();

    List<Product> getAllProductsByType(ProductType type);

    Product getProductById(Long id);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    void deleteProduct(Long id);



}
