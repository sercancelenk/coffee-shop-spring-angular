package sr.api.web.controller.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sr.api.core.business.service.IShopOrderService;
import sr.api.core.persistence.domain.Product;
import sr.api.core.persistence.domain.ShoppingCartExtraItem;
import sr.api.core.persistence.domain.ShoppingCartItem;
import sr.api.core.util.exception.CSRestRuntimeException;
import sr.api.core.business.service.ILoggerService;
import sr.api.core.business.service.IProductService;
import sr.api.core.business.service.IShoppingService;
import sr.api.web.controller.IShoppingCartController;
import sr.api.web.util.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by byzas on 15/01/16.
 */

@RestController
public class ShoppingCartController implements IShoppingCartController {

    @Autowired ILoggerService loggerService;
    @Autowired IProductService productService;
    @Autowired IShoppingService shoppingService;
    @Autowired IShopOrderService shopOrderService;

    @Override
    public Response getShoppingCart(){
        return new Response(shoppingService.toVO());
    }

    @Override
    public Response addItemWithExtra(@RequestBody String data, HttpServletRequest request) {
        try {
            JsonObject dataObject = new Gson().fromJson(data, JsonObject.class);
            ShoppingCartItem cartItem = new ShoppingCartItem();
            cartItem.setProduct(productService.getProductById(dataObject.get("product").getAsJsonObject().get("id").getAsLong()));
            cartItem.setQuantity(dataObject.get("quantity").getAsInt());
            JsonArray extras = dataObject.get("extras").getAsJsonArray();
            for(JsonElement element : extras){
                ShoppingCartExtraItem extraItem = new ShoppingCartExtraItem();
                extraItem.setProduct(productService.getProductById(element.getAsJsonObject().get("product").getAsJsonObject().get("id").getAsLong()));
                extraItem.setQuantity(element.getAsJsonObject().get("quantity").getAsInt());
                cartItem.addExtra(extraItem);
            }

            if(shoppingService.getShoppingCart() != null && !"".equals(shoppingService.getShoppingCart().getSession_id())
                    && shoppingService.getShoppingCart().getSession_id() == null)
                shoppingService.getShoppingCart().setSession_id(request.getSession().getId());

            shoppingService.addItemToShoppingCart(cartItem);

            return new Response(shoppingService.toVO());

        } catch (Exception ex) {
            if (ex instanceof CSRestRuntimeException)
                throw ex;
            throw new CSRestRuntimeException(1002, ex.getMessage());
        }
    }


    @Override
    public Response removeBaseItem(@PathVariable(value = "pid") Long pid,@PathVariable(value = "itemid") Long itemid, HttpServletRequest request) {

        try {
            Iterator<ShoppingCartItem> itemIterator = shoppingService.getShoppingCart().getItems().iterator();
            while (itemIterator.hasNext()) {
                ShoppingCartItem i = itemIterator.next();
                if (i.getId() == itemid) {
                    shoppingService.getShoppingCart().getItems().remove(i);
                }
            }
            return new Response(shoppingService.toVO());
        } catch (Exception ex) {
            if (ex instanceof CSRestRuntimeException)
                throw ex;

            throw new CSRestRuntimeException(3001, ex.getMessage());
        }

    }

    @Override
    public Response removeExtraItem(@PathVariable(value = "pid") Long pid,@PathVariable(value = "itemid") Long itemid, @PathVariable(value = "epid") Long epid, HttpServletRequest request) {

        try {
            Iterator<ShoppingCartItem> itemIterator = shoppingService.getShoppingCart().getItems().iterator();
            while (itemIterator.hasNext()) {
                ShoppingCartItem i = itemIterator.next();
                if (i.getId() == itemid) {
                    Iterator<ShoppingCartExtraItem> extraItemIterator = i.getExtras().iterator();
                    while(extraItemIterator.hasNext()){
                        ShoppingCartExtraItem extrai = extraItemIterator.next();
                        if(extrai.getProduct().getId() == epid){
                            extraItemIterator.remove();
                        }
                    }
                }
            }
            return new Response(shoppingService.toVO());
        } catch (Exception ex) {
            if (ex instanceof CSRestRuntimeException)
                throw ex;

            throw new CSRestRuntimeException(3001, ex.getMessage());
        }

    }

    @Override
    public Response updateQuantityOfBaseItem(@PathVariable(value = "pid") Long pid, @PathVariable(value = "quantity") Integer quantity,@PathVariable(value = "itemid") Long itemid){

        try {
            for (ShoppingCartItem item : shoppingService.getShoppingCart().getItems()) {
                if (item.getId() == itemid) {
                    item.setQuantity(quantity);
                }
            }

            return new Response(shoppingService.toVO());
        } catch (Exception ex) {
            if (ex instanceof CSRestRuntimeException)
                throw ex;

            throw new CSRestRuntimeException(3001, ex.getMessage());
        }
    }

    @Override
    public Response updateQuantityOfExtraItem(@PathVariable(value = "pid") Long pid,@PathVariable(value = "epid") Long epid, @PathVariable(value = "quantity") Integer quantity,@PathVariable(value = "itemid") Long itemid){

        try {
            for (ShoppingCartItem item : shoppingService.getShoppingCart().getItems()) {
                if (item.getId() == itemid) {
                    for(ShoppingCartExtraItem extraItem : item.getExtras()){
                        if(extraItem.getProduct().getId() == epid){
                            extraItem.setQuantity(quantity);
                        }
                    }
                }
            }

            return new Response(shoppingService.toVO());
        } catch (Exception ex) {
            if (ex instanceof CSRestRuntimeException)
                throw ex;

            throw new CSRestRuntimeException(3001, ex.getMessage());
        }
    }

    @Override
    public Response completeShopping(@RequestBody String data, HttpServletRequest request) {
        try{
            shopOrderService.addItemsToShopOrders(shoppingService.getShoppingCart());
            shoppingService.getShoppingCart().clear();
            return new Response(shoppingService.toVO());
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return new Response(null);
    }


}
