package sr.api.core.business.service;

import sr.api.core.persistence.domain.ShopOrder;
import sr.api.core.persistence.domain.ShoppingCart;

import java.util.List;

/**
 * Created by byzas on 26/01/16.
 */
public interface IShopOrderService {
    void addItemsToShopOrders(ShoppingCart shoppingCart);
}
