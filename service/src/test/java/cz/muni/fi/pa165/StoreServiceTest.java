package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.StoreDAO;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.service.LaboratoryService;
import cz.muni.fi.pa165.service.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Sumera
 */
public class StoreServiceTest extends AbstractServiceTest {

    @Mock
    private StoreDAO storeDAO;

    @InjectMocks
    @Autowired
    private StoreService storeService;

    private Long storeId = 1L;

    private String storeName = "storeName";

    private Store store;

    private List<Store> stores = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        store = createStore("store");

        stores.add(createStore("firstStore"));
        stores.add(createStore("secondStore"));
    }

    @Test
    public void createBottleTypeWillNotCrash() {
        storeService.createStore(store);
        verify(storeDAO).save(store);
    }

    @Test
    public void findAllWithEmptyList() {
        when(storeDAO.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), storeService.findAll());
    }

    @Test
    public void findAllWithNonEmptyList() {
        when(storeDAO.findAll()).thenReturn(stores);

        assertEquals(stores, storeService.findAll());
    }

    @Test
    public void findByIdReturnsNull() {
        when(storeDAO.findOne(storeId)).thenReturn(null);

        assertNull(storeService.findById(storeId));
    }

    @Test
    public void findByIdReturnsLaboratory() {
        when(storeDAO.findOne(storeId)).thenReturn(store);

        assertEquals(store, storeService.findById(storeId));
    }

    @Test
    public void findByNameReturnsNull() {
        when(storeDAO.findByName(storeName)).thenReturn(null);

        assertNull(storeService.findByName(storeName));
    }

    @Test
    public void findByNameReturnsLaboratory() {
        when(storeDAO.findByName(storeName)).thenReturn(store);

        assertEquals(store, storeService.findByName(storeName));
    }

    private Store createStore(String name) {
        Store store = new Store();
        store.setName(name);
        return store;
    }

}
