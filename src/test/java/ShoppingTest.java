import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sr.api.core.business.service.ICustomerService;
import sr.api.core.business.service.IProductService;
import sr.api.core.business.service.IShopOrderService;
import sr.api.core.business.service.IShoppingService;
import sr.api.core.persistence.dao.IShopOrderDAO;
import sr.api.core.persistence.dao.IShopOrderDetailDAO;
import sr.api.core.persistence.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by byzas on 27/01/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback = true, transactionManager = "txManager")
@Transactional
public class ShoppingTest {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProductService productService;
    @Autowired
    IShoppingService shoppingService;
    @Autowired
    IShopOrderService shopOrderService;
    @Autowired
    IShopOrderDAO shopOrderDAO;
    @Autowired
    IShopOrderDetailDAO shopOrderDetailDAO;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShopping() {
        Customer customer = customerService.getByUsername("test@test.com");
        System.out.println("User is logged in : " + customer.getName());

        Product cay = productService.getProductById(4L);
        System.out.println("User choose product : " + cay.getName());
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setProduct(cay);
        cartItem.setQuantity(3);
        shoppingService.getShoppingCart().addLineItem(cartItem);
        System.out.println("Added product to shopping cart : ");


        ShoppingCart shoppingCart = shoppingService.getShoppingCart();
        if (shoppingCart != null) {
            double totalPrice = shoppingCart.calculateTotalPrice();
            ShopOrder orderItem = new ShopOrder();
            orderItem.setCard_session_id(shoppingCart.getSession_id());
            orderItem.setCreated_date(new Date());
            Customer user = customer;
            orderItem.setCustomer(user);
            orderItem.setDiscount_price(shoppingCart.calculateDiscount());
            orderItem.setTotal_price(shoppingCart.calculateTotalPrice());
            orderItem.setPaid_price(shoppingCart.calculateTotalPrice() - shoppingCart.calculateDiscount());
            shopOrderDAO.saveShopOrder(Arrays.asList(orderItem));

            for (ShoppingCartItem item : shoppingCart.getItems()) {
                ShopOrderDetail detail = new ShopOrderDetail();
                detail.setProduct(item.getProduct());
                detail.setQuantity(item.getQuantity());
                detail.setShopOrder(orderItem);
                detail.setItem_id(item.getId());
                detail.setPrice(item.getProduct().getPrice() * item.getQuantity());
                shopOrderDetailDAO.saveShopOrderDetail(detail);

                List<ShopOrderDetail> products = new ArrayList<>();
                if (item.getExtras() != null) {
                    for (ShoppingCartExtraItem extra : item.getExtras()) {
                        detail = new ShopOrderDetail();
                        detail.setProduct(extra.getProduct());
                        detail.setQuantity(extra.getQuantity());
                        detail.setShopOrder(orderItem);
                        detail.setItem_id(item.getId());
                        detail.setPrice(extra.getProduct().getPrice() * extra.getQuantity());
                        shopOrderDetailDAO.saveShopOrderDetail(detail);
                    }
                }
            }
            System.out.println("Alışveriş tamamlandı");
        }

    }

}
