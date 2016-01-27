package sr.api.core.business.service;

import sr.api.core.persistence.domain.Product;
import sr.api.core.util.enums.ProductType;

import java.util.List;

/**
 * Created by byzas on 15/01/16.
 */
public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getAllProductsByType(ProductType type);
    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    void deleteProduct(Long id);

}
