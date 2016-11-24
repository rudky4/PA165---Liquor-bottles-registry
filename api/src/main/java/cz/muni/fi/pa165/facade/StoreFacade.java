package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author rk
 * @date 2016-11-22
 */
public interface StoreFacade {
    void createStore(StoreDTO store);
    
    List<StoreDTO> findAll();

    StoreDTO findById(Long id);
    
    StoreDTO findByName(String name);
}
