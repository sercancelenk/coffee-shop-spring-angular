package sr.api.core.persistence.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sr.api.core.persistence.dao.IProductDAO;
import sr.api.core.persistence.domain.Product;
import sr.api.core.util.enums.ProductType;

import java.util.List;

/**
 * Created by byzas on 15/01/16.
 */

@Repository(value = "productDAO")
@Transactional
public class ProductDAOImpl extends AbstractHibernateDAO<Product, Long> implements IProductDAO {

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(persistentClass));
    }

    @Override
    public List<Product> getAllProductsByType(ProductType type) {
        return (List<Product>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(persistentClass).add(Restrictions.eq("product_type", type.toString())));
    }

    @Override
    public Product getProductById(Long id) {
        return getById(id);
    }

    @Override
    public void saveProduct(Product product) {
        save(product);
    }

    @Override
    public void updateProduct(Product product) {
        saveOrUpdate(product);
    }

    @Override
    public void deleteProduct(Product product) {
        delete(product);
    }

    @Override
    public void deleteProduct(Long id) {
        deleteById(id);
    }
}
