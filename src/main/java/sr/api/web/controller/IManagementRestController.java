package sr.api.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import sr.api.web.util.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by byzas on 25/01/16.
 */

@RestController
@RequestMapping(value = "/management/rest")
@Scope(value = "request")
public interface IManagementRestController {

    @RequestMapping(value = "product/add", method = RequestMethod.POST)
    Response addProduct(@RequestBody String data, HttpServletRequest request);

    @RequestMapping(value = "product/delete/{pid}", method = RequestMethod.DELETE)
    Response deleteProduct(@PathVariable(value = "pid") Long pid, HttpServletRequest request);

    @RequestMapping(value = "product/all", method = RequestMethod.GET)
    Response getAllProduct();

    @RequestMapping(value = "product/update", method = RequestMethod.PUT)
    Response updateProduct(@RequestBody String data, HttpServletRequest request);


    @RequestMapping(value = "report/1", method = RequestMethod.GET)
    Response getTotalPricesByCustomerReport();

    @RequestMapping(value = "report/2", method = RequestMethod.GET)
    Response getTotalPricesByProductBase();

    @RequestMapping(value = "report/3", method = RequestMethod.GET)
    Response getTotalPricesByProductExtra();

}
