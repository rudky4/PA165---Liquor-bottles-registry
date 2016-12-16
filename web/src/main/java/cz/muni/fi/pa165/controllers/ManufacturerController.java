package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.facade.BottleFacade;
import cz.muni.fi.pa165.facade.ManufacturerFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * @author Martin Sumera
 */
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Inject
    private ManufacturerFacade manufacturerFacade;

    @Inject
    private BottleFacade bottleFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ManufacturerDTO> getManufacturers() {
        List<ManufacturerDTO> result = manufacturerFacade.findAll();
        if(result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ManufacturerDTO getManufacturerById(@PathVariable("id") long id) {
        ManufacturerDTO result = manufacturerFacade.findById(id);
        if (result != null) {
            return result;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = "/{id}/production", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getManufacturerProduction(@PathVariable("id") long id) {
        List<BottleDTO> bottles = bottleFacade.getAllBottlesFromManufacturer(getManufacturerById(id));
        if (bottles == null) {
            bottles = Collections.emptyList();
        }
        return bottles;
    }

    @RequestMapping(value = "/{id}/bottleTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleTypeDTO> getManufacturerBottleTypes(@PathVariable("id") long id) {
        ManufacturerDTO manufacturerDTO = manufacturerFacade.findById(id);
        if (manufacturerDTO == null) {
            throw new ResourceNotFound();
        }
        List<BottleTypeDTO> result = manufacturerDTO.getTypesProduced();
        if(result == null) {
            result = Collections.emptyList();
        }
        return result;
    }
}
