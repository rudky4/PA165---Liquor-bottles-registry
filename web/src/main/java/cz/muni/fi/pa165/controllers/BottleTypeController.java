package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.facade.BottleTypeFacade;
import cz.muni.fi.pa165.facade.ManufacturerFacade;
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

    @Inject
    private ManufacturerFacade manufacturerFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleTypeDTO> getBottleTypes() {
        return bottleTypeFacade.findAll();
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public final void createBottleType(@RequestBody BottleTypeDTO bottleType, @PathVariable("id") long manufacturerId) {
        ManufacturerDTO manufacturer = manufacturerFacade.findById(manufacturerId);
        bottleType.setManufacturedBy(manufacturer);
        bottleTypeFacade.createBottleType(bottleType);
    }
}
