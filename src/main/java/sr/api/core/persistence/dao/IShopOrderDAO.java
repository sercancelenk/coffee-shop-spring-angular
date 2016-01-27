package sr.api.core.persistence.dao;

import sr.api.core.persistence.domain.ShopOrder;

import java.util.List;

/**
 * Created by byzas on 26/01/16.
 */
public interface IShopOrderDAO extends GenericDAO<ShopOrder, Long>  {

    void saveShopOrder(List<ShopOrder> shopOrder);
    List getTotalPricesByCustomer();
    List getTotalPricesByProductBase();
    List getTotalPricesByProductExtra();

}
