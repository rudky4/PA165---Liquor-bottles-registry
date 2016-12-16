/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.StoreDAO;
import cz.muni.fi.pa165.entity.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 *
 * @author rk
 */
@Service
public class StoreServiceImpl implements StoreService{
    @Inject
    private StoreDAO storeDAO;

    @Override
    public void createStore(Store store) {
        storeDAO.save(store);
    }

    @Override
    public List<Store> findAll() {
        return storeDAO.findAll();
    }

    @Override
    public Store findById(Long id) {
        return storeDAO.findOne(id);
    }

    @Override
    public Store findByName(String name) {
        return storeDAO.findByName(name);
    }

    @Override
    public Set<Store> findByBottleType(Long id) {
        return storeDAO.findByBottleType(id);
    }
}
