package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.facade.BottleFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
        return bottleFacade.findAll();
    }
    
    @RequestMapping(value = "/toxic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getToxicBottles() {
        return bottleFacade.getAllToxicBottles();
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
    
    @RequestMapping(value = "/{id}/toxicity/{value}", method = RequestMethod.POST)
    public final void setToxic(@PathVariable("id") long id, @PathVariable("value") int isToxic) {
        BottleDTO result = bottleFacade.findById(id);
        if(result != null) {
            result.setToxic(isToxic == 1);
            result.setLaboratory(null);
        }
    }    
}
