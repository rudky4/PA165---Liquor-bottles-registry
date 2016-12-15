package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.facade.BottleTypeFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
        return bottleTypeFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public final void createBottleType(@RequestBody BottleTypeDTO bottleType,
                                       @PathVariable("id") long manufacturerId) {
        bottleTypeFacade.createBottleType(bottleType, manufacturerId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteBottleType(@PathVariable("id") long id) {
        System.out.println("Delete bottle with id=" + id);
        bottleTypeFacade.deleteBottleType(id);
    }
}
