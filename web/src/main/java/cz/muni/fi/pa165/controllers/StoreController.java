package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.facade.BottleFacade;
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

    @Inject
    private BottleFacade bottleFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<StoreDTO> getStores() {
        return storeFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final StoreDTO getStoreById(@PathVariable("id") long id) {
        StoreDTO result = storeFacade.findById(id);
        if (result != null) {
            return result;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = "/{id}/bottles/nontoxic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getAllNontoxicBottles(@PathVariable("id") long id) {
        List<BottleDTO> nontoxicBottles = bottleFacade.getAllNontoxicBottlesInStore(getStoreById(id));
        if (nontoxicBottles != null) {
            return nontoxicBottles;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = "/{id}/bottles/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getAllBottles(@PathVariable("id") long id) {
        List<BottleDTO> allBottles = bottleFacade.getAllBottlesInStore(getStoreById(id));
        if (allBottles != null) {
            return allBottles;
        } else {
            throw new ResourceNotFound();
        }
    }

}
