package sr.api.core.business.vo;

import sr.api.core.persistence.domain.ShoppingCartItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by byzas on 15/01/16.
 */
public class ShoppingCartVO implements Serializable {
    private List<ShoppingCartItem> items;
    private Double totalPrice;
    private Double discountPrice;
    private int item_count;

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
