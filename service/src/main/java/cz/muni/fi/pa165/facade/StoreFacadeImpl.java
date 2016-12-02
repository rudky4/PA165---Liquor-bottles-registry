/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.entity.Store;
import java.util.List;

import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.StoreService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 *
 * @author rk
 * @date 2016-11-23
 */
@Service
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
        List<Store> stores = storeService.findAll();
        return stores == null ? null : beanMappingService.mapTo(stores, StoreDTO.class);
    }

    @Override
    public StoreDTO findById(Long id) {        
        Store store = storeService.findById(id);
        return store == null ? null : beanMappingService.mapTo(store, StoreDTO.class);              
    }

    @Override
    public StoreDTO findByName(String name) {
        Store store = storeService.findByName(name);
        return store == null ? null : beanMappingService.mapTo(store, StoreDTO.class);  
    } 
}
