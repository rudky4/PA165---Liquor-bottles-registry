/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.controllers;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.facade.BottleFacade;
import cz.muni.fi.pa165.facade.LaboratoryFacade;
import cz.muni.fi.pa165.facade.PersonFacade;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rk
 */
@RestController
@RequestMapping("/laboratory")
public class LabController {
    
    @Inject
    private LaboratoryFacade laboratoryFacade;

    @Inject
    private PersonFacade personFacade;
    
    @RequestMapping(value = "/bottles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BottleDTO> getBottlesForLab(){
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = personFacade.findUserByLogin(login).getLaboratory().getId();
        
        return laboratoryFacade.getBottlesToCheck(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<LaboratoryDTO> getLaboratories() {
        return laboratoryFacade.findAll();
    }
}


