package sr.api.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sr.api.core.persistence.domain.Customer;
import sr.api.core.business.service.impl.CheckAppUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author sercan
 */
public class LoginPage extends HandlerInterceptorAdapter {
    @Autowired
    CheckAppUserDetailsService appUserDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Customer user = (Customer) session.getAttribute("user");
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            user = appUserDetailsService.getUserDetail(email);
            session.setAttribute("user", user);
        }

        return super.preHandle(request, response, handler);
    }


}
