package sr.api.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sercan
 *
 */
@Component
@RequestMapping("/")
public interface IViewController {

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public ModelAndView redirectToHome();

    @RequestMapping(value = "/shoppingcart")
    public ModelAndView shoppingCartPage();

    @RequestMapping(value = "/login",method={RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public ModelAndView getLoginPage();

    @RequestMapping(value = "/completeshopping",method={RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public ModelAndView completeShoppingPage();


}
