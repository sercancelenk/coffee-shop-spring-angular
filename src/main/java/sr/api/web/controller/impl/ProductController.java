package sr.api.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sr.api.core.persistence.domain.Product;
import sr.api.core.persistence.domain.ShoppingCartExtraItem;
import sr.api.core.persistence.domain.ShoppingCartItem;
import sr.api.core.util.enums.ProductType;
import sr.api.core.business.service.IProductService;
import sr.api.web.controller.IProductController;
import sr.api.web.util.Response;

import java.util.*;

/**
 * Created by byzas on 15/01/16.
 */

@RestController
public class ProductController implements IProductController {

    @Autowired
    IProductService productService;

    @Override
    public Response loadAllProducts(){
        return new Response(productService.getAllProducts());
    }

    @Override
    public Response loadAllBaseProducts(@PathVariable(value = "type") String type){
        return new Response(productService.getAllProductsByType(ProductType.valueOf(type.toString())));
    }

    @Override
    public Response loadRecommendations(){
        class Recommendation{
            public String name;
            public List<ShoppingCartItem> items;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ShoppingCartItem> getItems() {
                return items;
            }

            public void setItems(List<ShoppingCartItem> items) {
                this.items = items;
            }
        }
        List<Recommendation> recommendations = new ArrayList<>();



        ShoppingCartItem filtreKahveItem = null;
        ShoppingCartItem mochaItem = null;
        ShoppingCartItem latteItem = null;
        ShoppingCartExtraItem sutItem = null;
        ShoppingCartExtraItem findikSurubuItem = null;

        Product filtreKahveProduct = productService.getProductById(1L);
        Product sutProduct = productService.getProductById(6L);
        Product findikSurubuProduct = productService.getProductById(7L);
        Product mochaProduct = productService.getProductById(3L);
        Product latteProduct = productService.getProductById(2L);

        filtreKahveItem = new ShoppingCartItem();
        filtreKahveItem.setProduct(filtreKahveProduct);
        filtreKahveItem.setQuantity(1);
        sutItem = new ShoppingCartExtraItem();
        sutItem.setProduct(sutProduct);
        sutItem.setQuantity(1);
        filtreKahveItem.addExtra(sutItem);

        Recommendation recommendation = new Recommendation();
        recommendation.setName("Sütlü Filtre Kahve");
        recommendation.setItems(Arrays.asList(filtreKahveItem));
        recommendations.add(recommendation);

        mochaItem = new ShoppingCartItem();
        mochaItem.setProduct(mochaProduct);
        mochaItem.setQuantity(1);
        sutItem = new ShoppingCartExtraItem();
        sutItem.setProduct(sutProduct);
        sutItem.setQuantity(2);
        mochaItem.addExtra(sutItem);
        recommendation = new Recommendation();
        recommendation.setName("Bol sütlü (2 x süt) Mocha");
        recommendation.setItems(Arrays.asList(mochaItem));
        recommendations.add(recommendation);

        latteItem = new ShoppingCartItem();
        latteItem.setProduct(latteProduct);
        latteItem.setQuantity(1);
        sutItem = new ShoppingCartExtraItem();
        sutItem.setProduct(sutProduct);
        sutItem.setQuantity(1);
        findikSurubuItem = new ShoppingCartExtraItem();
        findikSurubuItem.setProduct(findikSurubuProduct);
        findikSurubuItem.setQuantity(1);
        latteItem.addExtra(sutItem);
        latteItem.addExtra(findikSurubuItem);

        recommendation = new Recommendation();
        recommendation.setName("Sütlü ve fındık şuruplu Latte");
        recommendation.setItems(Arrays.asList(latteItem));
        recommendations.add(recommendation);

        return new Response(recommendations);

    }

}
