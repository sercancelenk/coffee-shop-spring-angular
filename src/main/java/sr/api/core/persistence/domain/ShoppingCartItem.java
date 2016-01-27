package sr.api.core.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by byzas on 15/01/16.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCartItem {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartItem.class);
    private int id;
    private Product product;
    private int quantity;
    private double amount;
    private double totalCost;
    private List<ShoppingCartExtraItem> extras = null;

    public ShoppingCartItem() {
        int tmp = UUID.randomUUID().hashCode();
        this.id =  tmp < 0 ? tmp*(-1):tmp ;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
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

    public double calculateTotalPrice() {
        try {
            double basePrice = this.getQuantity() * this.getProduct().getPrice();
            double extraPrice = 0.0;
            if(extras != null) {
                for (ShoppingCartExtraItem extraItem : extras) {
                    extraPrice += extraItem.calculateTotalPrice();
                }
            }
            return basePrice + extraPrice;
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return 0;
    }

    public void addExtra(Product productExtra) {
        try {
            if (extras == null) {
                extras = new ArrayList<>();
                ShoppingCartExtraItem extraItem = new ShoppingCartExtraItem();
                extraItem.setProduct(productExtra);
                extraItem.setQuantity(1);
                extras.add(extraItem);
            } else {
                boolean alreadyExists = false;
                for (ShoppingCartExtraItem ex : extras) {
                    if (ex.getProduct().getId() == productExtra.getId()) {
                        alreadyExists = true;
                        ex.setQuantity(ex.getQuantity() + 1);
                        break;
                    }
                }

                if (!alreadyExists) {
                    ShoppingCartExtraItem extraItem = new ShoppingCartExtraItem();
                    extraItem.setProduct(productExtra);
                    extraItem.setQuantity(1);
                    extras.add(extraItem);
                }
            }
        } catch (Exception ex) {
            logger.error("Ekstra ürün eklenemedi. Hata Mesajı: " + ex.getMessage());
        }

    }

    public void addExtra(ShoppingCartExtraItem extraItem) {
        try {
            if (extras == null) {
                extras = new ArrayList<>();
                extras.add(extraItem);
            } else {
                boolean alreadyExists = false;
                for (ShoppingCartExtraItem ex : extras) {
                    if (ex.getProduct().getId() == extraItem.getProduct().getId()) {
                        alreadyExists = true;
                        ex.setQuantity(ex.getQuantity() + 1);
                        break;
                    }
                }

                if (!alreadyExists) {
                    extras.add(extraItem);
                }
            }
        } catch (Exception ex) {
            logger.error("Ekstra ürün eklenemedi. Hata Mesajı: " + ex.getMessage());
        }

    }

    public List<ShoppingCartExtraItem> getExtras() {
        return extras;
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
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
