package sr.api.core.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by byzas on 15/01/16.
 */

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCart implements Serializable {

    private String session_id;

    private List<ShoppingCartItem> items;

    private double subTotalCost;

    public ShoppingCart(){
        items = new CopyOnWriteArrayList<ShoppingCartItem>();
    }

    public void addLineItem(ShoppingCartItem item){
            items.add(item);
    }

    public void clear(){
        subTotalCost = 0.0;
        items.clear();
    }

    public double calculateTotalPrice(){
        double total = 0.0;
        for(ShoppingCartItem scitem : items){
            total += scitem.calculateTotalPrice();
        }
        return total;
    }

    public double calculateDiscount(){
        boolean discount1_hasOverTotalCostFrom12 = false;
        double discount1_price = 0.0;
        boolean discount2_itemCountOverFrom3 = false;
        double discount2_price = 0.0;

        if(calculateTotalPrice() > 12) {
            discount1_hasOverTotalCostFrom12 = true;
            discount1_price = calculateTotalPrice() * 0.25;
        }

        int itemsize = 0;
        for(ShoppingCartItem i : items){
            itemsize += i.getQuantity();
        }
        if(itemsize>=3) {
            discount2_itemCountOverFrom3 = true;
            double prevcost = 0;
            double mincost = 0;
            for(ShoppingCartItem item : items){
                if(prevcost == 0) {
                    prevcost = item.getTotalCost();
                    mincost = item.getTotalCost();
                }else{
                    if(prevcost >= item.getTotalCost()){
                        mincost = item.getTotalCost();
                    }
                    prevcost = item.getTotalCost();
                }
            }
            discount2_price = mincost;
        }

        if(discount1_hasOverTotalCostFrom12 && discount2_itemCountOverFrom3){
            return discount1_price>discount2_price?discount1_price:discount2_price;
        }else if(discount1_hasOverTotalCostFrom12){
            return discount1_price;
        }else if(discount2_itemCountOverFrom3){
            return discount2_price;
        }

        return 0.0;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public double getSubTotalCost() {
        return subTotalCost;
    }

    public void setSubTotalCost(double subTotalCost) {
        this.subTotalCost = subTotalCost;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
