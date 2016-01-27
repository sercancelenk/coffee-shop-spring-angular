package sr.api.core.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sr.api.core.business.service.ICustomerService;
import sr.api.core.persistence.domain.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byzas on 27/01/16.
 */


public class CheckAppUserDetailsService implements UserDetailsService {


    @Autowired
    LoggerServiceImpl loggerService;

    @Autowired
    ICustomerService customerService;

    private org.springframework.security.core.userdetails.User appUserDetails;

    @Override
    public UserDetails loadUserByUsername(String user)
            throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        Customer authUser = getUserDetail(user);

        if (authUser == null) {
            loggerService.info("Auth user is null. Exception is throwing..");
            throw new UsernameNotFoundException("Username not found");
        }
        appUserDetails = new org.springframework.security.core.userdetails.User(
                user, authUser.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked,
                getAuthorities(authUser.getRole()));


        return appUserDetails;
    }

    public List<GrantedAuthority> getAuthorities(Integer role) {
        try{
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            if (role.intValue() == 1) {
                authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            } else if (role.intValue() == 2) {
                authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return authList;
        }catch (Exception ex){
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }

        return null;

    }

    public Customer getUserDetail(String username) {
        Customer user = null;
        try {
            user = customerService.getByUsername(username);
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return user;
    }


}
