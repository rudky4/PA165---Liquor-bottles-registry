package cz.muni.fi.pa165.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mhajas
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String getUserPrincipal() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        List<?> authorities = new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return "{\"username\":\"" + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + "\", \"role\":\"" +
                authorities.get(0) + "\"}";
    }
}
