package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.ApiContract;
import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.facade.BottleFacade;
import cz.muni.fi.pa165.facade.BottleTypeFacade;
import cz.muni.fi.pa165.facade.StoreFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Martin Sumera
 */
@RestController
@RequestMapping(ApiContract.Store.BASE)
public class StoreController {

    @Inject
    private StoreFacade storeFacade;

    @Inject
    private BottleFacade bottleFacade;

    @Inject
    private BottleTypeFacade bottleTypeFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<StoreDTO> getStores() {
        List<StoreDTO> result = storeFacade.findAll();
        if(result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    @RequestMapping(value = ApiContract.Store.ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final StoreDTO getStoreById(@PathVariable(ApiContract.Store.PATH_ID) long id) {
        StoreDTO result = storeFacade.findById(id);
        if (result != null) {
            return result;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Store.BOTTLES_NONTOXIC, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getAllNontoxicBottles(@PathVariable(ApiContract.Store.PATH_ID) long id) {
        List<BottleDTO> nontoxicBottles = bottleFacade.getAllNontoxicBottlesInStore(getStoreById(id));
        if (nontoxicBottles != null) {
            return nontoxicBottles;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Store.BOTTLES_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getAllBottles(@PathVariable(ApiContract.Store.PATH_ID) long id) {
        List<BottleDTO> allBottles = bottleFacade.getAllBottlesInStore(getStoreById(id));
        if (allBottles != null) {
            return allBottles;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Store.STORE_BY_BOTTLE_TYPE, method = RequestMethod.GET)
    public final Set<StoreDTO> getStoresByBottleType(@PathVariable(ApiContract.Store.PATH_ID) long bottleTypeId) {
        return storeFacade.findByBottleType(bottleTypeId);
    }

    @RequestMapping(value = ApiContract.Store.STORE_BOTTLE_TYPES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleTypeDTO> getStoreBottleTypes(@PathVariable(ApiContract.Store.PATH_ID) long id) {
        StoreDTO storeDTO = storeFacade.findById(id);
        if(storeDTO == null) {
            throw new ResourceNotFound();
        }

        List<BottleTypeDTO> bottleTypes = bottleTypeFacade.findByStore(storeDTO);
        if (bottleTypes == null) {
            bottleTypes = Collections.emptyList();
        }
        return bottleTypes;
    }
}
