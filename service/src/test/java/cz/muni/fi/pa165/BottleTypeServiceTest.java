package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.BottleTypeDAO;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.service.BottleTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Sumera
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BottleTypeServiceTest extends AbstractServiceTest {

    @Mock
    private BottleTypeDAO bottleTypeDAO;

    @InjectMocks
    @Autowired
    private BottleTypeService bottleTypeService;

    private BottleType firstBottleType;

    private BottleType secondBottleType;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        firstBottleType = new BottleType();
        firstBottleType.setName("name1");
        firstBottleType.setType(AlcoholType.RUM);

        secondBottleType = new BottleType();
        secondBottleType.setName("name2");
        secondBottleType.setType(AlcoholType.VODKA);
    }

    @Test
    public void findAll_withEmptyList_returnsEmptyList() {
        ArrayList<BottleType> emptyList = new ArrayList<>();
        when(bottleTypeDAO.findAll()).thenReturn(emptyList);

        List<BottleType> bottleTypes = bottleTypeService.findAll();

        assertEquals(bottleTypes, emptyList);
    }

    @Test
    public void findAll_withNonEmptyList_returnsNonEmptyList() {
        ArrayList<BottleType> list = new ArrayList<>();
        list.add(firstBottleType);
        list.add(secondBottleType);

        when(bottleTypeDAO.findAll()).thenReturn(list);

        List<BottleType> bottleTypes = bottleTypeService.findAll();
        assertEquals(bottleTypes, list);
    }

    @Test
    public void findByType_withEmptyList_returnsEmptyList() {
        ArrayList<BottleType> emptyList = new ArrayList<>();
        when(bottleTypeDAO.findByType(AlcoholType.RUM)).thenReturn(emptyList);

        List<BottleType> bottleRumTypes = bottleTypeService.findByAlcoholType(AlcoholType.RUM);
        assertEquals(bottleRumTypes, emptyList);
    }

    @Test
    public void findByType_withNonEmptyList_returnsNonEmptyList() {
        ArrayList<BottleType> list = new ArrayList<>();
        list.add(firstBottleType);
        list.add(secondBottleType);
        when(bottleTypeDAO.findByType(AlcoholType.VODKA)).thenReturn(list);

        List<BottleType> bottleVodkaTypes = bottleTypeService.findByAlcoholType(AlcoholType.VODKA);
        assertEquals(bottleVodkaTypes, list);
    }

    @Test
    public void findWithHigherVolume_withEmptyList_returnsEmptyList() {
        BigDecimal volume = new BigDecimal(80);

        ArrayList<BottleType> emptyList = new ArrayList<>();
        when(bottleTypeDAO.findByVolumeGreaterThanEqual(volume)).thenReturn(emptyList);

        List<BottleType> bottlesWithHigherVolumes = bottleTypeService.findWithHigherVolume(volume);
        assertEquals(bottlesWithHigherVolumes, emptyList);
    }

    @Test
    public void findWithHigherVolume_withNonEmptyList_returnsNonEmptyList() {
        BigDecimal volume = new BigDecimal(40);

        ArrayList<BottleType> list = new ArrayList<>();
        list.add(firstBottleType);
        list.add(secondBottleType);
        when(bottleTypeDAO.findByVolumeGreaterThanEqual(volume)).thenReturn(list);

        List<BottleType> bottlesWithHigherVolumes = bottleTypeService.findWithHigherVolume(volume);
        assertEquals(bottlesWithHigherVolumes, list);
    }

    @Test
    public void findWithSize_withEmptyList_returnsEmptyList() {
        BigDecimal size = new BigDecimal(1);

        ArrayList<BottleType> emptyList = new ArrayList<>();
        when(bottleTypeDAO.findBySize(size)).thenReturn(emptyList);

        List<BottleType> bottlesWithSize = bottleTypeService.findBySize(size);
        assertEquals(bottlesWithSize, emptyList);
    }

    @Test
    public void findWithSize_withNonEmptyList_returnsNonEmptyList() {
        BigDecimal size = new BigDecimal(1);

        ArrayList<BottleType> list = new ArrayList<>();
        list.add(firstBottleType);
        list.add(secondBottleType);
        when(bottleTypeDAO.findBySize(size)).thenReturn(list);

        List<BottleType> bottlesWithSize = bottleTypeService.findBySize(size);
        assertEquals(bottlesWithSize, list);
    }

    @Test
    public void findById_withWrongId_returnsNull() {
        Long id = 0l;
        when(bottleTypeDAO.findOne(id)).thenReturn(null);

        BottleType bottleType = bottleTypeService.findById(id);
        assertNull(bottleType);
    }

    @Test
    public void findById_withCorrectId_returnsBottle() {
        Long id = 0l;
        when(bottleTypeDAO.findOne(id)).thenReturn(firstBottleType);

        BottleType bottleType = bottleTypeService.findById(id);
        assertEquals(bottleType, firstBottleType);
    }

    @Test
    public void createBottleType_willNotCrash() {
        bottleTypeService.createBottleType(firstBottleType);
        verify(bottleTypeDAO).save(firstBottleType);
    }
}
