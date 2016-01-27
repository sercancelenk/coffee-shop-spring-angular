package sr.api.core.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by byzas on 26/01/16.
 */

@Entity
@Table(name = "shop_order")
public class ShopOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_session_id")
    private String card_session_id;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "shopOrder", targetEntity = ShopOrderDetail.class)
    private List<ShopOrderDetail> products;

    @Column(name = "total_price")
    private Double total_price;

    @Column(name = "discount_price")
    private Double discount_price;

    @Column(name = "paid_price")
    private Double paid_price;

    @Column(name = "created_date")
    private Date created_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard_session_id() {
        return card_session_id;
    }

    public void setCard_session_id(String card_session_id) {
        this.card_session_id = card_session_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(Double discount_price) {
        this.discount_price = discount_price;
    }

    public Double getPaid_price() {
        return paid_price;
    }

    public void setPaid_price(Double paid_price) {
        this.paid_price = paid_price;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public List<ShopOrderDetail> getProducts() {
        return products;
    }

    public void setProducts(List<ShopOrderDetail> products) {
        this.products = products;
    }
}
