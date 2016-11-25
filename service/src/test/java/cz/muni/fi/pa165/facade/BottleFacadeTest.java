package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.service.BottleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Sumera
 */
public class BottleFacadeTest extends AbstractFacadeTest {

    @Mock
    private BottleService bottleService;

    @Autowired
    @InjectMocks
    private BottleFacade bottleFacade;

    private Bottle bottle;

    private BottleType bottleType;

    private BottleTypeDTO bottleTypeDTO;

    private BottleDTO bottleDTO;

    private Long bottleId = 1L;

    private String bottleStickerId = "name";

    private Date date = new Date();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        bottle = new Bottle();
        bottle.setStickerID(bottleStickerId);

        bottleDTO = new BottleDTO();
        bottleDTO.setStickerID(bottleStickerId);

        bottleType = new BottleType();
        bottleTypeDTO = new BottleTypeDTO();
    }

    @Test
    public void testCreateBottle() {
        when(beanMappingService.mapTo(bottleDTO, Bottle.class)).thenReturn(bottle);

        bottleFacade.createBottle(bottleDTO);

        verify(bottleService).createBottle(bottle);
        verify(beanMappingService).mapTo(bottleDTO, Bottle.class);
    }

    @Test
    public void testFindAll() {
        when(bottleService.findAll()).thenReturn(Collections.singletonList(bottle));

        List<BottleDTO> bottles = bottleFacade.findAll();

        assertEquals(bottle.getStickerID(), bottles.get(0).getStickerID());
        verify(bottleService).findAll();
        verify(beanMappingService).mapTo(Collections.singletonList(bottle), BottleDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(bottleService.findAll()).thenReturn(null);

        List<BottleDTO> bottles = bottleFacade.findAll();

        assertNull(bottles);
        verify(bottleService).findAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testGetBottlesFromDate() {
        when(bottleService.getBottlesFromDate(date)).thenReturn(Collections.singletonList(bottle));

        List<BottleDTO> bottlesDTO = bottleFacade.getBottlesFromDate(date);

        assertNotNull(bottleDTO);
        assertEquals(bottle.getStickerID(), bottlesDTO.get(0).getStickerID());
        verify(bottleService).getBottlesFromDate(date);
        verify(beanMappingService).mapTo(Collections.singletonList(bottle), BottleDTO.class);
    }

    @Test
    public void testGetBottlesFromDateWithNull() {
        when(bottleService.getBottlesFromDate(date)).thenReturn(null);

        List<BottleDTO> bottlesDTO = bottleFacade.getBottlesFromDate(date);

        assertNull(bottlesDTO);
        verify(bottleService).getBottlesFromDate(date);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testGetAllToxicBottles() {
        when(bottleService.getAllToxicBottles()).thenReturn(Collections.singletonList(bottle));

        List<BottleDTO> bottlesDTO = bottleFacade.getAllToxicBottles();

        assertNotNull(bottleDTO);
        assertEquals(bottle.getStickerID(), bottlesDTO.get(0).getStickerID());
        verify(bottleService).getAllToxicBottles();
        verify(beanMappingService).mapTo(Collections.singletonList(bottle), BottleDTO.class);
    }

    @Test
    public void testGetAllToxicBottlesWithNull() {
        when(bottleService.getAllToxicBottles()).thenReturn(null);

        List<BottleDTO> bottlesDTO = bottleFacade.getAllToxicBottles();

        assertNull(bottlesDTO);
        verify(bottleService).getAllToxicBottles();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByBottleType() {
        when(bottleService.findByBottleType(bottleType)).thenReturn(Collections.singletonList(bottle));

        List<BottleDTO> bottlesDTO = bottleFacade.findByBottleType(bottleTypeDTO);

        assertNotNull(bottlesDTO);
        assertEquals(bottle.getStickerID(), bottlesDTO.get(0).getStickerID());
        verify(bottleService).findByBottleType(bottleType);
        verify(beanMappingService).mapTo(Collections.singletonList(bottle), BottleDTO.class);
    }

    @Test
    public void isToxicWithBottle() {
        when(bottleService.isToxic(bottle)).thenReturn(true);

        boolean result = bottleFacade.isToxic(bottleDTO);

        assertTrue(result);
        verify(bottleService).isToxic(bottle);
        verify(beanMappingService).mapTo(bottleDTO, Bottle.class);
    }

    @Test
    public void isToxicWithId() {
        when(bottleService.isToxic(bottleId)).thenReturn(true);

        boolean result = bottleFacade.isToxic(bottleId);

        assertTrue(result);
        verify(bottleService).isToxic(bottleId);
    }

    @Test
    public void setToxicWithBottle() {
        bottleFacade.setToxic(bottleDTO);

        verify(bottleService).setToxic(bottle);
    }

    @Test
    public void setToxicWithId() {
        bottleFacade.setToxic(bottleDTO);

        verify(bottleService).setToxic(bottle);
    }

    @Test
    public void testFindById() {
        when(bottleService.findById(bottleId)).thenReturn(bottle);

        BottleDTO bottleDTO = bottleFacade.findById(bottleId);

        assertNotNull(bottle);
        assertEquals(bottle.getStickerID(), bottleDTO.getStickerID());
        verify(bottleService).findById(bottleId);
        verify(beanMappingService).mapTo(bottle, BottleDTO.class);
    }

    @Test
    public void testFindByStickerId() {
        when(bottleService.findByStickerId(bottleStickerId)).thenReturn(bottle);

        BottleDTO bottleDTO = bottleFacade.findByStickerId(bottleStickerId);

        assertNotNull(bottle);
        assertEquals(bottle.getStickerID(), bottleDTO.getStickerID());
        verify(bottleService).findByStickerId(bottleStickerId);
        verify(beanMappingService).mapTo(bottle, BottleDTO.class);
    }

}
