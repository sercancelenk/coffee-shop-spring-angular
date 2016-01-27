package sr.api.core.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by byzas on 25/01/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCartExtraItem {

    private Product product;
    private int quantity;
    private double amount;
    private double totalCost;
    private List<Product> extras = null;

    public Product getProduct(){
        return this.product;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.product.getPrice();
    }


    public double getTotalCost() {
        return calculateTotalPrice();
    }

    public double calculateTotalPrice(){
        return this.getQuantity() * this.getProduct().getPrice();
    }



    public double calculateTaxRate(){
        return 0;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return Objects.equals(getProduct(), that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct());
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}