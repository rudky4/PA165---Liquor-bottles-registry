package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.BottleDAO;
import cz.muni.fi.pa165.dao.BottleTypeDAO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rk
 * @date 2016-11-25
 */
public class BottleServiceTest extends AbstractServiceTest {
    @Mock
    private BottleDAO bottleDAO;

    @Mock
    private BottleTypeDAO bottleTypeDAO;
    
    @InjectMocks
    @Autowired
    private BottleService bottleService;

    @InjectMocks
    @Autowired
    private BottleTypeService bottleTypeService;
    
    private Bottle bottle1;

    private Bottle bottle2;

    private List<Bottle> bottles;

    private List<Bottle> emptyList = Collections.emptyList();
    
    private BottleType bType;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
     
        bType = new BottleType();
        bType.setName("DzamaRhum");
        bType.setType(AlcoholType.RUM);
                
        bottle1 = new Bottle();
        bottle1.setToxic(false);
        bottle1.setStickerID("ID1");
        bottle1.setProduced(new Date());
        bottle1.setBottleType(bType);

        bottle2 = new Bottle();
        bottle2.setToxic(true);
        bottle2.setStickerID("ID2");
        bottle2.setProduced(new Date());
        bottle2.setBottleType(bType);
        
        bottles = new ArrayList<>();
        bottles.add(bottle1);
        bottles.add(bottle2);
    }
    
    @Test
    public void findAllWithEmptyListReturnsEmptyList() {
        when(bottleDAO.findAll()).thenReturn(emptyList);

        List<Bottle> bottleList = bottleService.findAll();

        assertEquals(bottleList, emptyList);
    }
    
    @Test
    public void findAllWithNonEmptyListReturnsNonEmptyList() {
        when(bottleDAO.findAll()).thenReturn(bottles);

        List<Bottle> bottleList = bottleService.findAll();
        
        assertEquals(bottleList, bottles);
    }
    
    @Test
    public void findAllToxicWithEmptyListReturnsEmptyList() {
        when(bottleDAO.findByToxic(true)).thenReturn(emptyList);

        List<Bottle> bottleList = bottleService.getAllToxicBottles();
        
        assertEquals(bottleList, emptyList);
    }

    @Test
    public void findAllToxicWithNonEmptyListReturnsNonEmptyList() {
        final List<Bottle> toxicBottles = Collections.singletonList(bottle2);
        when(bottleDAO.findByToxic(true)).thenReturn(toxicBottles);

        List<Bottle> bottleList = bottleService.getAllToxicBottles();

        assertEquals(bottleList, toxicBottles);
    }
    
    @Test
    public void findAllByTypeWithEmptyListReturnsEmptyList() {
        when(bottleDAO.findByBottleType(bType)).thenReturn(emptyList);

        List<Bottle> bottleList = bottleService.findByBottleType(bType);
        
        assertEquals(bottleList, emptyList);
    }

    @Test
    public void findAllByTypeWithNonEmptyListReturnsNonEmptyList() {
        when(bottleDAO.findByBottleType(bType)).thenReturn(bottles);

        List<Bottle> bottleList = bottleService.findByBottleType(bType);

        assertEquals(bottleList, bottles);
    }
       

    @Test
    public void findByStickerIdWithWrongIdReturnsNull() {
        when(bottleDAO.findByStickerID("WRONGID")).thenReturn(null);

        Bottle bottleNull = bottleService.findByStickerId("WRONGID");
        
        assertNull(bottleNull);
    }

    @Test
    public void findByStickerIdWithCorrectIdReturnsBottle() {
        when(bottleDAO.findByStickerID("ID1")).thenReturn(bottle1);

        Bottle bottleFirst = bottleService.findByStickerId("ID1");
        
        assertEquals(bottleFirst, bottle1);
    }    
    
    @Test
    public void findByIdWithWrongIdReturnsNull() {
        when(bottleDAO.findOne(789L)).thenReturn(null);

        Bottle bottleNull = bottleService.findById(789L);
        
        assertNull(bottleNull);
    }

    @Test
    public void findByIdWithCorrectIdReturnsBottle() {
        when(bottleDAO.findOne(0L)).thenReturn(bottle1);

        Bottle bottleFirst = bottleService.findById(0L);
        
        assertEquals(bottleFirst, bottle1);
    }

    @Test
    public void createBottleTypeWillNotCrash() {
        bottleService.createBottle(bottle1);
        verify(bottleDAO).save(bottle1);
    }
}
