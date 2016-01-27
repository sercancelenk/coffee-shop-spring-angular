package sr.api.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import sr.api.core.business.service.IShopOrderService;
import sr.api.core.business.service.IShoppingService;
import sr.api.web.controller.IViewController;


/**
 * @author sercan
 *
 */
@Component
public class ViewControllerImpl implements IViewController {

	@Autowired
	IShoppingService shoppingService;

	@Autowired
	IShopOrderService shopOrderService;

	@Override
	public ModelAndView redirectToHome() {
		ModelAndView m = new ModelAndView("home");
		return m;
	}

	@Override
	public ModelAndView shoppingCartPage() {
		ModelAndView m = new ModelAndView("shoppingcart");
		m.addObject("cart", shoppingService.getShoppingCart());
		return m;
	}

	@Override
	public ModelAndView getLoginPage() {
		return new ModelAndView("login");
	}

	@Override
	public ModelAndView completeShoppingPage() {
		try{
			shopOrderService.addItemsToShopOrders(shoppingService.getShoppingCart());
			shoppingService.getShoppingCart().clear();
		}catch (Exception ex){

		}
		ModelAndView m = new ModelAndView("shoppingcart");
		m.addObject("cart", shoppingService.getShoppingCart());
		m.addObject("complete", true);
		return m;
	}

}
