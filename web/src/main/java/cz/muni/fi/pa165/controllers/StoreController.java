package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.facade.StoreFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Martin Sumera
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Inject
    private StoreFacade storeFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<StoreDTO> getStores() {
        return storeFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final StoreDTO getStoresById(@PathVariable("id") long id) {
        StoreDTO result = storeFacade.findById(id);
        if (result != null) {
            return result;
        } else {
            throw new ResourceNotFound();
        }
    }

}
