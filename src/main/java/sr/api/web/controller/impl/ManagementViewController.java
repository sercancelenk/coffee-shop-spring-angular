package sr.api.web.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import sr.api.web.controller.IManagementViewController;

/**
 * Created by byzas on 25/01/16.
 */

@Component
public class ManagementViewController implements IManagementViewController {
    @Override
    public ModelAndView productsPage() {
        ModelAndView m = new ModelAndView("pm");
        return m;
    }

    @Override
    public ModelAndView reportsPage() {
        ModelAndView m = new ModelAndView("reports");
        return m;
    }
}
