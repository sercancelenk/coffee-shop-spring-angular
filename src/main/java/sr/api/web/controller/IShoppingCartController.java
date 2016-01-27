package sr.api.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sr.api.web.util.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by byzas on 15/01/16.
 */

@RestController
@RequestMapping("/shop")
@Scope(value = "request")
public interface IShoppingCartController {

    @RequestMapping(value = "cart", method = RequestMethod.GET)
    Response getShoppingCart();

    @RequestMapping(value = "cart/add/item/withextra", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody Response addItemWithExtra(@RequestBody String data, HttpServletRequest request);

    @RequestMapping(value = "cart/remove/{pid}/{itemid}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody Response removeBaseItem(@PathVariable(value = "pid") Long pid,@PathVariable(value = "itemid") Long itemid, HttpServletRequest request);

    @RequestMapping(value = "cart/remove/{pid}/{epid}/{itemid}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody Response removeExtraItem(@PathVariable(value = "pid") Long pid,@PathVariable(value = "itemid") Long itemid,@PathVariable(value = "epid") Long epid, HttpServletRequest request);

    @RequestMapping(value = "cart/update/base/quantity/{pid}/{quantity}/{itemid}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody Response updateQuantityOfBaseItem(@PathVariable(value = "pid") Long pid, @PathVariable(value = "quantity") Integer quantity,@PathVariable(value = "itemid") Long itemid);

    @RequestMapping(value = "cart/update/extra/quantity/{pid}/{epid}/{quantity}/{itemid}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody Response updateQuantityOfExtraItem(@PathVariable(value = "pid") Long pid,@PathVariable(value = "epid") Long epid, @PathVariable(value = "quantity") Integer quantity,@PathVariable(value = "itemid") Long itemid);

    @RequestMapping(value = "cart/complete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody Response completeShopping(@RequestBody String data, HttpServletRequest request);

}
