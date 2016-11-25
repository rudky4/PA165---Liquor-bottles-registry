package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.BottleDAO;
import cz.muni.fi.pa165.dao.BottleTypeDAO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.service.BottleService;
import cz.muni.fi.pa165.service.BottleTypeService;
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
public class BottleServiceTest extends AbstractServiceTest{
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
    
    private Bottle b1;

    private Bottle b2;

    private List<Bottle> bottles;

    private List<Bottle> emptyList = Collections.emptyList();
    
    private BottleType bType;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
     
        bType = new BottleType();
        bType.setName("DzamaRhum");
        bType.setType(AlcoholType.RUM);
                
        b1 = new Bottle();
        b1.setToxic(false);
        b1.setStickerID("ID1");
        b1.setProduced(new Date());
        b1.setBottleType(bType);

        b2 = new Bottle();
        b2.setToxic(true);
        b2.setStickerID("ID2");
        b2.setProduced(new Date());
        b2.setBottleType(bType);
        
        bottles = new ArrayList<>();
        bottles.add(b1);
        bottles.add(b2);
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
        
        assertEquals(bottleList, bottleList);
    }
    
    @Test
    public void findAllToxicWithEmptyListReturnsEmptyList() {
        when(bottleDAO.findByToxic(true)).thenReturn(emptyList);

        List<Bottle> bottleList = bottleService.getAllToxicBottles();
        
        assertEquals(bottleList, emptyList);
    }

    @Test
    public void findAllToxicWithNonEmptyListReturnsNonEmptyList() {
        when(bottleDAO.findByToxic(true)).thenReturn(emptyList);

        List<Bottle> bottleList = bottleService.getAllToxicBottles();
        
        ArrayList<Bottle> toxicBottles = new ArrayList<>();
        bottles.add(b2);
        
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
        when(bottleDAO.findByStickerID("ID1")).thenReturn(b1);

        Bottle bottleFirst = bottleService.findByStickerId("ID1");
        
        assertEquals(bottleFirst, b1);
    }    
    
    @Test
    public void findByIdWithWrongIdReturnsNull() {
        when(bottleDAO.findOne(789L)).thenReturn(null);

        Bottle bottleNull = bottleService.findById(789L);
        
        assertNull(bottleNull);
    }

    @Test
    public void findByIdWithCorrectIdReturnsBottle() {
        when(bottleDAO.findOne(0L)).thenReturn(b1);

        Bottle bottleFirst = bottleService.findById(0L);
        
        assertEquals(bottleFirst, b1);
    }

    @Test
    public void createBottleTypeWillNotCrash() {
        bottleService.createBottle(b1);
        verify(bottleDAO).save(b1);
    }
}
