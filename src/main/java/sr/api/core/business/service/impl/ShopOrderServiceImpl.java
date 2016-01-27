package sr.api.core.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sr.api.core.business.service.ICustomerService;
import sr.api.core.business.service.IShopOrderService;
import sr.api.core.persistence.dao.ICustomerDAO;
import sr.api.core.persistence.dao.IShopOrderDAO;
import sr.api.core.persistence.dao.IShopOrderDetailDAO;
import sr.api.core.persistence.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by byzas on 26/01/16.
 */

@Service(value = "shopOrderService")
@Transactional
public class ShopOrderServiceImpl implements IShopOrderService {

    @Autowired IShopOrderDAO shopOrderDAO;
    @Autowired IShopOrderDetailDAO shopOrderDetailDAO;
    @Autowired ICustomerService customerService;
    @Autowired LoggerServiceImpl loggerService;

    @Override
    public void addItemsToShopOrders(ShoppingCart shoppingCart) {
        try {
            if (shoppingCart != null) {
                double totalPrice = shoppingCart.calculateTotalPrice();
                ShopOrder orderItem = new ShopOrder();
                orderItem.setCard_session_id(shoppingCart.getSession_id());
                orderItem.setCreated_date(new Date());
                String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
                Customer user = customerService.getByUsername(username);
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


            }
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }

    }
}
