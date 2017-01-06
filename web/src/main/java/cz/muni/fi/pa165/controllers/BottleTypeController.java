package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.ApiContract;
import cz.muni.fi.pa165.dto.BottleTypeCreateDTO;
import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.exceptions.ResourceConflict;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.exceptions.ResourceNotValid;
import cz.muni.fi.pa165.facade.BottleTypeFacade;
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
@RequestMapping(ApiContract.BottleType.BASE)
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

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public final void createBottleType(@Valid @RequestBody BottleTypeCreateDTO bottleType,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResourceNotValid();
        }
        try{
            bottleTypeFacade.createBottleType(bottleType);
        } catch (DataAccessException dae) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException iae) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public final void updateBottleType(@Valid @RequestBody BottleTypeDTO bottleType,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResourceNotValid();
        }
        try{
            bottleTypeFacade.updateBottleType(bottleType);
        } catch (IllegalArgumentException iae) {
            throw new ResourceNotFound();
        }
    }
}
