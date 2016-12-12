package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.enums.PersonRole;
import cz.muni.fi.pa165.security.SimpleGrantedAuthorityFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mhajas
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String getUserPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(value = "/role/is/police", method = RequestMethod.GET)
    public boolean isPolice() {
        SimpleGrantedAuthority policeAuthority = SimpleGrantedAuthorityFactory.createAuthority(PersonRole.POLICE.toString());
        for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            return authority.equals(policeAuthority);
        }
        return false;
    }
}
