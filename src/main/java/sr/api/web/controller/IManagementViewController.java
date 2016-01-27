package sr.api.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by byzas on 25/01/16.
 */

@Component
@RequestMapping("/management")
public interface IManagementViewController {

    @RequestMapping(value = "/products")
    ModelAndView productsPage();

    @RequestMapping(value = "/reports")
    ModelAndView reportsPage();

}
