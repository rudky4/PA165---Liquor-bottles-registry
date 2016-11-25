package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.service.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Sumera
 */
public class StoreFacadeImplTest extends AbstractFacadeImplTest {

    @Mock
    private StoreService storeService;

    @InjectMocks
    private final StoreFacadeImpl storeFacade = new StoreFacadeImpl();

    private Store store;

    private StoreDTO storeDTO;

    private Long storeId = 1L;

    private String storeName = "name";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        store = new Store();
        store.setName(storeName);

        storeDTO = new StoreDTO();
        storeDTO.setName(storeName);
    }

    @Test
    public void testFindAll() {
        when(storeService.findAll()).thenReturn(Collections.singletonList(store));

        List<StoreDTO> stores = storeFacade.findAll();

        assertEquals(Collections.singletonList(store).get(0).getName(), stores.get(0).getName());
        verify(storeService).findAll();
        verify(beanMappingService).mapTo(Collections.singletonList(store), StoreDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(storeService.findAll()).thenReturn(null);

        List<StoreDTO> stores = storeFacade.findAll();

        assertNull(stores);
        verify(storeService).findAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(storeService.findById(storeId)).thenReturn(store);

        StoreDTO storeDTO = storeFacade.findById(storeId);

        assertNotNull(storeDTO);
        assertEquals(store.getName(), storeDTO.getName());
        verify(storeService).findById(storeId);
        verify(beanMappingService).mapTo(store, StoreDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(storeService.findById(storeId)).thenReturn(null);

        StoreDTO storeDTO = storeFacade.findById(storeId);

        assertNull(storeDTO);
        verify(storeService).findById(storeId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(storeService.findByName(storeName)).thenReturn(store);

        StoreDTO storeDTO = storeFacade.findByName(storeName);

        assertNotNull(storeDTO);
        assertEquals(store.getName(), storeDTO.getName());
        verify(storeService).findByName(storeName);
        verify(beanMappingService).mapTo(store, StoreDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(storeService.findByName(storeName)).thenReturn(null);

        StoreDTO storeDTO = storeFacade.findByName(storeName);

        assertNull(storeDTO);
        verify(storeService).findByName(storeName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testCreateStore() {
        when(beanMappingService.mapTo(storeDTO, Store.class)).thenReturn(store);

        storeFacade.createStore(storeDTO);

        verify(storeService).createStore(store);
        verify(beanMappingService).mapTo(storeDTO, Store.class);
    }

}
