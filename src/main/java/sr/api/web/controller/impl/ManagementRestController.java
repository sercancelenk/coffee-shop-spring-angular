package sr.api.web.controller.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sr.api.core.business.service.IProductService;
import sr.api.core.business.service.IReportService;
import sr.api.core.persistence.domain.Product;
import sr.api.web.controller.IManagementRestController;
import sr.api.web.util.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by byzas on 25/01/16.
 */

@Component
public class ManagementRestController implements IManagementRestController{

    @Autowired
    IProductService productService;

    @Autowired
    IReportService reportService;

    @Override
    public Response addProduct(@RequestBody String data, HttpServletRequest request) {
        try{
            JsonObject dataObject = new Gson().fromJson(data, JsonObject.class);
            if(dataObject == null)
                return new Response(false);

            String productName = (dataObject.get("name") == null || "".equals(dataObject.get("name"))) ? null : dataObject.get("name").getAsString();
            String productDescription = (dataObject.get("description") == null || "".equals(dataObject.get("description"))) ? null : dataObject.get("description").getAsString();
            String product_type = (dataObject.get("product_type") == null || "".equals(dataObject.get("description"))) ? null : dataObject.get("product_type").getAsString();
            Double product_price = dataObject.get("price") == null ? 0.0 : dataObject.get("price").getAsDouble();

            if(productName == null || productDescription == null || product_type == null || product_price == 0.0)
                return new Response(false);

            Product product = new Product();
            product.setName(productName);
            product.setDescription(productDescription);
            product.setProduct_type(product_type);
            product.setPrice(product_price);
            product.setCreated_date(new Date());
            productService.saveProduct(product);

            return new Response(true);


        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        return new Response(false);
    }

    @Override
    public Response deleteProduct(@PathVariable(value = "pid") Long pid, HttpServletRequest request) {
        try{
            if(pid <= 0)
                return new Response(false);

            productService.deleteProduct(pid);

            return new Response(true);


        }catch (Exception ex){

        }


        return new Response(false);
    }

    @Override
    public Response getAllProduct() {
        return null;
    }

    @Override
    public Response updateProduct(@RequestBody String data, HttpServletRequest request) {
        try{
            JsonObject dataObject = new Gson().fromJson(data, JsonObject.class);
            if(dataObject == null)
                return new Response(false);

            Long id = dataObject.get("id").getAsLong();
            String productName = (dataObject.get("name") == null || "".equals(dataObject.get("name"))) ? null : dataObject.get("name").getAsString();
            String productDescription = (dataObject.get("description") == null || "".equals(dataObject.get("description"))) ? null : dataObject.get("description").getAsString();
            String product_type = (dataObject.get("product_type") == null || "".equals(dataObject.get("description"))) ? null : dataObject.get("product_type").getAsString();
            Double product_price = dataObject.get("price") == null ? 0.0 : dataObject.get("price").getAsDouble();

            if(id<=0)
                return new Response(false);

            Product product = productService.getProductById(id);
            product.setName(productName);
            product.setDescription(productDescription);
            product.setProduct_type(product_type);
            product.setPrice(product_price);
            product.setCreated_date(new Date());
            productService.updateProduct(product);

            return new Response(true);


        }catch (Exception ex){

        }


        return new Response(false);
    }

    @Override
    public Response getTotalPricesByCustomerReport() {
        return new Response(reportService.getTotalPricesByCustomer());
    }

    @Override
    public Response getTotalPricesByProductBase() {
        return new Response(reportService.getTotalPricesByProductBase());
    }

    @Override
    public Response getTotalPricesByProductExtra() {
        return new Response(reportService.getTotalPricesByProductExtra());
    }
}
