package sr.api.core.persistence.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sr.api.core.persistence.dao.IShopOrderDetailDAO;
import sr.api.core.persistence.domain.ShopOrder;
import sr.api.core.persistence.domain.ShopOrderDetail;

import java.util.List;

/**
 * Created by byzas on 26/01/16.
 */

@Repository(value = "shopOrderDetailDAO")
@Transactional
public class ShopOrderDetailDAO extends AbstractHibernateDAO<ShopOrderDetail, Long> implements IShopOrderDetailDAO{

    @Override
    public void saveShopOrderDetail(ShopOrderDetail shopOrderDetail) {
        save(shopOrderDetail);
    }
}
