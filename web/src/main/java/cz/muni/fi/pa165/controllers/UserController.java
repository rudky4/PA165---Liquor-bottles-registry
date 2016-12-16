package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.facade.PersonFacade;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

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
        List<?> authorities = new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return "{\"username\":\"" + getLogin() + "\", \"role\":\"" +
                authorities.get(0) + "\"}";
    }

    @RequestMapping(value = "/manufacturer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ManufacturerDTO getPersonsManufacturer() {
        final String login = getLogin();
        PersonDTO personDTO = personFacade.findUserByLogin(login);
        return personDTO.getManufacturer();
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StoreDTO getPersonsStore() {
        final String login = getLogin();
        PersonDTO personDTO = personFacade.findUserByLogin(login);
        return personDTO.getStore();
    }

    private String getLogin() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
