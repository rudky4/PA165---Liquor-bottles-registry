package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.exceptions.RoleIsNotSet;
import cz.muni.fi.pa165.facade.PersonFacade;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author mhajas
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Inject
    private PersonFacade personFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRole() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.size() == 0) {
            throw new RoleIsNotSet();
        }
        return authorities.iterator().next().toString();
    }

    @RequestMapping(value = "/manufacturer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ManufacturerDTO getPersonsManufacturer() {
        final String login = getUserPrincipal();
        PersonDTO personDTO = personFacade.findUserByLogin(login);
        return personDTO.getManufacturer();
    }
}
