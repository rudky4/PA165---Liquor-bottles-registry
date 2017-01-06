package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.ApiContract;
import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.exceptions.ResourceNotFound;
import cz.muni.fi.pa165.facade.BottleFacade;
import cz.muni.fi.pa165.facade.ManufacturerFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Martin Sumera
 */
@RestController
@RequestMapping(ApiContract.Manufacturer.BASE)
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

    @RequestMapping(value = ApiContract.Manufacturer.ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ManufacturerDTO getManufacturerById(@PathVariable(ApiContract.Manufacturer.PATH_ID) long id) {
        ManufacturerDTO result = manufacturerFacade.findById(id);
        if (result != null) {
            return result;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Manufacturer.PRODUCTION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getManufacturerProduction(@PathVariable(ApiContract.Manufacturer.PATH_ID) long id) {
        List<BottleDTO> bottles = bottleFacade.getAllBottlesFromManufacturer(getManufacturerById(id));
        if (bottles == null) {
            bottles = Collections.emptyList();
        }
        return bottles;
    }

    @RequestMapping(value = ApiContract.Manufacturer.BOTTLE_TYPES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleTypeDTO> getManufacturerBottleTypes(@PathVariable(ApiContract.Manufacturer.PATH_ID) long id,
                                                                @RequestParam(value = ApiContract.Manufacturer.PARAM_DELETED, defaultValue = "0") int showDeleted) {
        ManufacturerDTO manufacturerDTO = manufacturerFacade.findById(id);
        if (manufacturerDTO == null) {
            throw new ResourceNotFound();
        }
        List<BottleTypeDTO> result = manufacturerDTO.getTypesProduced();
        if(result == null) {
            result = Collections.emptyList();
        }

        if(showDeleted == 1) {
            return result;
        } else {
            return result.stream().filter(bt -> !bt.isDeleted()).collect(Collectors.toList());
        }
    }
}
