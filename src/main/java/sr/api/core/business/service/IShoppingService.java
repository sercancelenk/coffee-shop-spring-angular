package sr.api.core.business.service;

import sr.api.core.persistence.domain.ShoppingCart;
import sr.api.core.persistence.domain.ShoppingCartItem;
import sr.api.core.business.vo.ShoppingCartVO;

/**
 * Created by byzas on 15/01/16.
 */
public interface IShoppingService {

    ShoppingCart getShoppingCart();

    void addItemToShoppingCart(ShoppingCartItem item);

    void removeItemFromShoppingCart(ShoppingCart item);

    void updateQuantityOfItemInShoppingCart(ShoppingCartItem item, Integer quantity);

    void saveShoppingCart();

    ShoppingCartVO toVO();

}
