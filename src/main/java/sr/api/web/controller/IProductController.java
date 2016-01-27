package sr.api.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sr.api.web.util.Response;

/**
 * Created by byzas on 15/01/16.
 */

@RestController
@RequestMapping(value = "product")
@Scope(value = "request")
public interface IProductController {

    @RequestMapping(value = "all", method = RequestMethod.GET)
    Response loadAllProducts();

    @RequestMapping(value = "all/{type}", method = RequestMethod.GET)
    Response loadAllBaseProducts(@PathVariable(value = "type") String type);

    @RequestMapping(value = "recommendations", method = RequestMethod.GET)
    Response loadRecommendations();

}
