package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.service.StoreService;
import cz.muni.fi.pa165.service.StoreServiceImpl;
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
    private StoreService journeyService;

    @InjectMocks
    private final StoreFacadeImpl journeyFacade = new StoreFacadeImpl();

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
        when(journeyService.findAll()).thenReturn(Collections.singletonList(store));

        List<StoreDTO> stores = journeyFacade.findAll();

        assertEquals(Collections.singletonList(store).get(0).getName(), stores.get(0).getName());
        verify(journeyService).findAll();
        verify(beanMappingService).mapTo(Collections.singletonList(store), StoreDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(journeyService.findAll()).thenReturn(null);

        List<StoreDTO> stores = journeyFacade.findAll();

        assertNull(stores);
        verify(journeyService).findAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(journeyService.findById(storeId)).thenReturn(store);

        StoreDTO storeDTO =journeyFacade.findById(storeId);

        assertNotNull(storeDTO);
        assertEquals(store.getName(), storeDTO.getName());
        verify(journeyService).findById(storeId);
        verify(beanMappingService).mapTo(store, StoreDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(journeyService.findById(storeId)).thenReturn(null);

        StoreDTO storeDTO = journeyFacade.findById(storeId);

        assertNull(storeDTO);
        verify(journeyService).findById(storeId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(journeyService.findByName(storeName)).thenReturn(store);

        StoreDTO storeDTO = journeyFacade.findByName(storeName);

        assertNotNull(storeDTO);
        assertEquals(store.getName(), storeDTO.getName());
        verify(journeyService).findByName(storeName);
        verify(beanMappingService).mapTo(store, StoreDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(journeyService.findByName(storeName)).thenReturn(null);

        StoreDTO storeDTO = journeyFacade.findByName(storeName);

        assertNull(storeDTO);
        verify(journeyService).findByName(storeName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testCreateStore() {
        when(beanMappingService.mapTo(storeDTO, Store.class)).thenReturn(store);

        journeyFacade.createStore(storeDTO);

        verify(journeyService).createStore(store);
        verify(beanMappingService).mapTo(storeDTO, Store.class);
    }

}
