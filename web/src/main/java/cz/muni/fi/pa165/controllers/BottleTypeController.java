package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.facade.BottleTypeFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.math.BigDecimal;
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
}
