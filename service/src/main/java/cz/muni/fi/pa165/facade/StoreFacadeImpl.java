/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Store;
import java.util.List;

import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.StoreService;
import javax.inject.Inject;

/**
 *
 * @author rk
 * @date 2016-11-23
 */
public class StoreFacadeImpl implements StoreFacade{
    @Inject
    private StoreService storeService;

    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public void createStore(StoreDTO store) {
        storeService.createStore(beanMappingService.mapTo(store, Store.class));
    }

    @Override
    public List<StoreDTO> findAll() {
        return beanMappingService.mapTo(storeService.findAll(), StoreDTO.class);
    }

    @Override
    public StoreDTO findById(Long id) {
       return beanMappingService.mapTo(storeService.findById(id), StoreDTO.class);               
    }

    @Override
    public StoreDTO findByName(String name) {
        return beanMappingService.mapTo(storeService.findByName(name), StoreDTO.class);
    }

    @Override
    public List<PersonDTO> findAllPersonsInStore() {
        return beanMappingService.mapTo(storeService.findAllPersonsInStore(), PersonDTO.class);       
    }

    @Override
    public List<BottleDTO> findAllBottlesInStore() {
        return beanMappingService.mapTo(storeService.findAllBottlesInStore(), BottleDTO.class); 
    }

    @Override
    public boolean isBottleInStore(BottleDTO bottle) {
        return storeService.isBottleInStore(beanMappingService.mapTo(bottle, Bottle.class));
    }   
}
