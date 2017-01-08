package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.ApiContract;
import cz.muni.fi.pa165.dto.BottleCreateDTO;
import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.BottleToxicDTO;
import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.exceptions.ResourceConflict;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.exceptions.ResourceNotValid;
import cz.muni.fi.pa165.facade.BottleFacade;
import cz.muni.fi.pa165.facade.LaboratoryFacade;
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
@RequestMapping(ApiContract.Bottle.BASE)
public class BottleController {

    @Inject
    private BottleFacade bottleFacade;

    @Inject
    private LaboratoryFacade laboratoryFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getBottles() {
        List<BottleDTO> result = bottleFacade.findAll();
        if(result == null) {
            result = Collections.emptyList();
        }
        return result;
    }
    
    @RequestMapping(value = ApiContract.Bottle.TOXIC, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getToxicBottles() {
        List<BottleDTO> result = bottleFacade.getAllToxicBottles();
        if(result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    @RequestMapping(value = ApiContract.Bottle.ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final BottleDTO getBottleById(@PathVariable(ApiContract.Bottle.PATH_ID) long id) {
        BottleDTO result = bottleFacade.findById(id);
        if(result != null) {
            return result;
        } else {
            throw new ResourceNotFound();
        }
    }
    
    @RequestMapping(value = ApiContract.Bottle.TOXIC, method = RequestMethod.PUT)
    public final void setToxic(@Valid @RequestBody BottleToxicDTO bottleToxicDTO,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ResourceNotValid();
        }

        // TODO: make method taking ID and Bool to mark bottle (non)toxic
        BottleDTO result = bottleFacade.findById(bottleToxicDTO.getId());
        if(result == null) {
            throw new ResourceNotFound();
        }
        result.setToxic(bottleToxicDTO.getToxic());
        result.setLaboratory(null);
        bottleFacade.updateBottle(result);
    }

    @RequestMapping(value = ApiContract.Bottle.ID, method = RequestMethod.PUT)
    public final void assignToLab(@PathVariable(ApiContract.Bottle.PATH_ID) long id) {
        BottleDTO result = bottleFacade.findById(id);
        LaboratoryDTO laboratoryDTO = laboratoryFacade.findWithLeastBottles();
        if(result == null || laboratoryDTO == null) {
            throw new ResourceNotFound();
        }
        result.setLaboratory(laboratoryDTO);
        try {
            bottleFacade.updateBottle(result);
        } catch (IllegalArgumentException iae) {
            throw new ResourceConflict();
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final void createBottle(@Valid @RequestBody BottleCreateDTO bottle,
                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ResourceNotValid();
        }

        try {
            bottleFacade.importBottleToStore(bottle);
        } catch (DataAccessException dae) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException iae) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Bottle.ID, method = RequestMethod.DELETE)
    public final void deleteBottle(@PathVariable(ApiContract.Bottle.PATH_ID) long id) {
        try{
            bottleFacade.deleteBottle(id);
        } catch (DataAccessException dae) {
            throw new ResourceNotFound();
        }
    }
}
