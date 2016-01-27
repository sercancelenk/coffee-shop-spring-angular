package sr.api.core.persistence.dao;

import sr.api.core.persistence.domain.ShopOrder;
import sr.api.core.persistence.domain.ShopOrderDetail;

import java.util.List;

/**
 * Created by byzas on 26/01/16.
 */
public interface IShopOrderDetailDAO extends GenericDAO<ShopOrderDetail, Long> {
    void saveShopOrderDetail(ShopOrderDetail shopOrderDetail);
}
