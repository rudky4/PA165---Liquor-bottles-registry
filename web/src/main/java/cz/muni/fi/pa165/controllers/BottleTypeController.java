package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.exceptions.ResourceConflict;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.facade.BottleTypeFacade;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * @author Jakub Fiser
 * @date 04/12/2016
 */
@RestController
@RequestMapping("/bottleType")
public class BottleTypeController {

    @Inject
    private BottleTypeFacade bottleTypeFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleTypeDTO> getBottleTypes() {
        List<BottleTypeDTO> result = bottleTypeFacade.findAll();
        if(result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public final void createBottleType(@RequestBody BottleTypeDTO bottleType,
                                       @PathVariable("id") long manufacturerId) {
        try{
            bottleTypeFacade.createBottleType(bottleType, manufacturerId);
        } catch (DataAccessException dae) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException iae) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public final void updateBottleType(@RequestBody BottleTypeDTO bottleType,
                                       @PathVariable("id") long typeId) {
        try{
            bottleTypeFacade.updateBottleType(bottleType);
        } catch (IllegalArgumentException iae) {
            throw new ResourceNotFound();
        }
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public final void deleteBottleType(@PathVariable("id") long id) {
//        bottleTypeFacade.deleteBottleType(id);
//    }
}
