package sr.api.core.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sr.api.core.persistence.domain.ShoppingCart;
import sr.api.core.persistence.domain.ShoppingCartExtraItem;
import sr.api.core.persistence.domain.ShoppingCartItem;
import sr.api.core.business.service.IShoppingService;
import sr.api.core.business.vo.ShoppingCartVO;
import sr.api.core.util.enums.ProductType;

/**
 * Created by byzas on 15/01/16.
 */

@Service(value = "shoppingService")
public class ShoppingServiceImpl implements IShoppingService {

    @Autowired LoggerServiceImpl loggerService;

    @Autowired
    ShoppingCart shoppingCart;

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void addItemToShoppingCart(ShoppingCartItem item) {
            shoppingCart.addLineItem(item);
    }

    @Override
    public void removeItemFromShoppingCart(ShoppingCart item) {
        try {
            if (shoppingCart.getItems().contains(item)) {
                shoppingCart.getItems().remove(item);
            }
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }

    }

    @Override
    public void updateQuantityOfItemInShoppingCart(ShoppingCartItem item, Integer quantity) {
        try {
            for (ShoppingCartItem itm : shoppingCart.getItems()) {
                if (itm.getProduct().getId() == item.getProduct().getId()) {
                    itm.setQuantity(itm.getQuantity() + quantity);
                }
            }
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
    }


    @Override
    public ShoppingCartVO toVO(){
        try {
            ShoppingCartVO vo = new ShoppingCartVO();
            vo.setItems(getShoppingCart().getItems());
            vo.setItem_count(getShoppingCart().getItems().size());
            vo.setTotalPrice(getShoppingCart().calculateTotalPrice());
            vo.setDiscountPrice(getShoppingCart().calculateDiscount());
            return vo;
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }


    @Override
    public void saveShoppingCart() {

    }
}
