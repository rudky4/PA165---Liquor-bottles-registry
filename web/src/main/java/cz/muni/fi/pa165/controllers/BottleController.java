package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.exceptions.ResourceConflict;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.exceptions.ResourceNotValid;
import cz.muni.fi.pa165.facade.BottleFacade;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * @author Jakub Fiser
 * @date 04/12/2016
 */
@RestController
@RequestMapping("/bottle")
public class BottleController {

    @Inject
    private BottleFacade bottleFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getBottles() {
        List<BottleDTO> result = bottleFacade.findAll();
        if(result == null) {
            result = Collections.emptyList();
        }
        return result;
    }
    
    @RequestMapping(value = "/toxic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getToxicBottles() {
        List<BottleDTO> result = bottleFacade.getAllToxicBottles();
        if(result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final BottleDTO getBottleById(@PathVariable("id") long id) {
        BottleDTO result = bottleFacade.findById(id);
        if(result != null) {
            return result;
        } else {
            throw new ResourceNotFound();
        }
    }
    
    @RequestMapping(value = "/{id}/toxicity/{value}", method = RequestMethod.PUT)
    public final void setToxic(@PathVariable("id") long id, @PathVariable("value") int isToxic) {
        BottleDTO result = bottleFacade.findById(id);
        if(result == null) {
            throw new ResourceNotFound();
        }
        result.setToxic(isToxic == 1);
        result.setLaboratory(null);
        bottleFacade.updateBottle(result);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final void createBottle(@Valid @RequestBody BottleDTO bottle,
                                   @RequestParam("store") long storeId,
                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ResourceNotValid();
        }
        try {
            bottleFacade.importBottleToStore(bottle, storeId);
        } catch (DataAccessException dae) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException iae) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteBottleType(@PathVariable("id") long id) {
        bottleFacade.deleteBottle(id);
    }
}
