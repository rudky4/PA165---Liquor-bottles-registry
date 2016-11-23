package cz.muni.fi.pa165;

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

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Sumera
 */
public class BottleTypeServiceTest extends AbstractServiceTest {

    @Mock
    private BottleTypeDAO bottleTypeDAO;

    @InjectMocks
    @Autowired
    private BottleTypeService bottleTypeService;

    private BottleType firstBottleType;

    private BottleType secondBottleType;

    private List<BottleType> bottleTypeList;

    private List<BottleType> emptyList = Collections.emptyList();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        firstBottleType = new BottleType();
        firstBottleType.setName("name1");
        firstBottleType.setType(AlcoholType.RUM);

        secondBottleType = new BottleType();
        secondBottleType.setName("name2");
        secondBottleType.setType(AlcoholType.VODKA);

        bottleTypeList = new ArrayList<>();
        bottleTypeList.add(firstBottleType);
        bottleTypeList.add(secondBottleType);
    }

    @Test
    public void findAllWithEmptyListReturnsEmptyList() {
        when(bottleTypeDAO.findAll()).thenReturn(emptyList);

        List<BottleType> bottleTypes = bottleTypeService.findAll();

        assertEquals(bottleTypes, emptyList);
    }

    @Test
    public void findAllWithNonEmptyListReturnsNonEmptyList() {
        when(bottleTypeDAO.findAll()).thenReturn(bottleTypeList);

        List<BottleType> bottleTypes = bottleTypeService.findAll();
        assertEquals(bottleTypes, bottleTypeList);
    }

    @Test
    public void findByTypeWithEmptyListReturnsEmptyList() {
        when(bottleTypeDAO.findByType(AlcoholType.RUM)).thenReturn(emptyList);

        List<BottleType> bottleRumTypes = bottleTypeService.findByAlcoholType(AlcoholType.RUM);
        assertEquals(bottleRumTypes, emptyList);
    }

    @Test
    public void findByTypeWithNonEmptyListReturnsNonEmptyList() {
        when(bottleTypeDAO.findByType(AlcoholType.VODKA)).thenReturn(bottleTypeList);

        List<BottleType> bottleVodkaTypes = bottleTypeService.findByAlcoholType(AlcoholType.VODKA);
        assertEquals(bottleVodkaTypes, bottleTypeList);
    }

    @Test
    public void findWithHigherVolumeWithEmptyListReturnsEmptyList() {
        BigDecimal volume = new BigDecimal(80);

        when(bottleTypeDAO.findByVolumeGreaterThanEqual(volume)).thenReturn(emptyList);

        List<BottleType> bottlesWithHigherVolumes = bottleTypeService.findWithHigherVolume(volume);
        assertEquals(bottlesWithHigherVolumes, emptyList);
    }

    @Test
    public void findWithHigherVolumeWithNonEmptyListReturnsNonEmptyList() {
        BigDecimal volume = new BigDecimal(40);

        when(bottleTypeDAO.findByVolumeGreaterThanEqual(volume)).thenReturn(bottleTypeList);

        List<BottleType> bottlesWithHigherVolumes = bottleTypeService.findWithHigherVolume(volume);
        assertEquals(bottlesWithHigherVolumes, bottleTypeList);
    }

    @Test
    public void findWithSizeWithEmptyListReturnsEmptyList() {
        BigDecimal size = new BigDecimal(1);

        when(bottleTypeDAO.findBySize(size)).thenReturn(emptyList);

        List<BottleType> bottlesWithSize = bottleTypeService.findBySize(size);
        assertEquals(bottlesWithSize, emptyList);
    }

    @Test
    public void findWithSizeWithNonEmptyListReturnsNonEmptyList() {
        BigDecimal size = new BigDecimal(1);

        when(bottleTypeDAO.findBySize(size)).thenReturn(bottleTypeList);

        List<BottleType> bottlesWithSize = bottleTypeService.findBySize(size);
        assertEquals(bottlesWithSize, bottleTypeList);
    }

    @Test
    public void findByIdWithWrongIdReturnsNull() {
        Long id = 0l;
        when(bottleTypeDAO.findOne(id)).thenReturn(null);

        BottleType bottleType = bottleTypeService.findById(id);
        assertNull(bottleType);
    }

    @Test
    public void findByIdWithCorrectIdReturnsBottle() {
        Long id = 0l;
        when(bottleTypeDAO.findOne(id)).thenReturn(firstBottleType);

        BottleType bottleType = bottleTypeService.findById(id);
        assertEquals(bottleType, firstBottleType);
    }

    @Test
    public void createBottleTypeWillNotCrash() {
        bottleTypeService.createBottleType(firstBottleType);
        verify(bottleTypeDAO).save(firstBottleType);
    }
}
