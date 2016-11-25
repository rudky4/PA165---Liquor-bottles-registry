package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.service.BottleTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
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
 * @author mhajas
 */
public class BottleTypeFacadeTest extends AbstractFacadeTest{

    @Mock
    private BottleTypeService bottleTypeService;

    @Autowired
    @InjectMocks
    private BottleTypeFacade bottleTypeFacade;

    private BottleType bottleType;

    private BottleTypeDTO bottleTypeDTO;

    private Long bottleTypeId = 1L;

    private String bottleTypeName = "name";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bottleTypeFacade = (BottleTypeFacade) unwrapProxy(bottleTypeFacade);
        ReflectionTestUtils.setField(bottleTypeFacade, "bottleTypeService", bottleTypeService);
        ReflectionTestUtils.setField(bottleTypeFacade, "beanMappingService", beanMappingService);

        bottleType = new BottleType();
        bottleType.setName(bottleTypeName);

        bottleTypeDTO = new BottleTypeDTO();
        bottleTypeDTO.setName(bottleTypeName);
    }

    @Test
    public void testFindAll() {
        when(bottleTypeService.findAll()).thenReturn(Collections.singletonList(bottleType));

        List<BottleTypeDTO> bottleTypes = bottleTypeFacade.findAll();

        assertNotNull(bottleTypes);
        assertEquals(bottleType.getName(), bottleTypes.get(0).getName());
        verify(bottleTypeService).findAll();
        verify(beanMappingService).mapTo(Collections.singletonList(bottleType), BottleTypeDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(bottleTypeService.findAll()).thenReturn(null);

        List<BottleTypeDTO> bottleTypes = bottleTypeFacade.findAll();

        assertNull(bottleTypes);
        verify(bottleTypeService).findAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(bottleTypeService.findById(bottleTypeId)).thenReturn(bottleType);

        BottleTypeDTO bottleTypeDTO = bottleTypeFacade.findById(bottleTypeId);

        assertNotNull(bottleTypeDTO);
        assertEquals(bottleType.getName(), bottleTypeDTO.getName());
        verify(bottleTypeService).findById(bottleTypeId);
        verify(beanMappingService).mapTo(bottleType, BottleTypeDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(bottleTypeService.findById(bottleTypeId)).thenReturn(null);

        BottleTypeDTO bottleTypeDTO = bottleTypeFacade.findById(bottleTypeId);

        assertNull(bottleTypeDTO);
        verify(bottleTypeService).findById(bottleTypeId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByAlcoholType() {
        when(bottleTypeService.findByAlcoholType(AlcoholType.RUM)).thenReturn(Collections.singletonList(bottleType));

        List<BottleTypeDTO> bottleTypeDTOs = bottleTypeFacade.findByAlcoholType(AlcoholType.RUM);

        assertNotNull(bottleTypeDTOs);
        assertEquals(bottleType.getName(), bottleTypeDTOs.get(0).getName());
        verify(bottleTypeService).findByAlcoholType(AlcoholType.RUM);
        verify(beanMappingService).mapTo(Collections.singletonList(bottleType), BottleTypeDTO.class);
    }

    @Test
    public void testFindByAlcoholTypeNull() {
        when(bottleTypeService.findByAlcoholType(AlcoholType.RUM)).thenReturn(null);

        List<BottleTypeDTO> bottleTypeDTOs = bottleTypeFacade.findByAlcoholType(AlcoholType.RUM);

        assertNull(bottleTypeDTOs);
        verify(bottleTypeService).findByAlcoholType(AlcoholType.RUM);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindWithHigherVolume() {
        when(bottleTypeService.findWithHigherVolume(BigDecimal.ONE)).thenReturn(Collections.singletonList(bottleType));

        List<BottleTypeDTO> bottleTypeDTOs = bottleTypeFacade.findWithHigherVolume(BigDecimal.ONE);

        assertNotNull(bottleTypeDTOs);
        assertEquals(bottleType.getName(), bottleTypeDTOs.get(0).getName());
        verify(bottleTypeService).findWithHigherVolume(BigDecimal.ONE);
        verify(beanMappingService).mapTo(Collections.singletonList(bottleType), BottleTypeDTO.class);
    }

    @Test
    public void testFindWithHigherVolumeNull() {
        when(bottleTypeService.findWithHigherVolume(BigDecimal.ONE)).thenReturn(null);

        List<BottleTypeDTO> bottleTypeDTOs = bottleTypeFacade.findWithHigherVolume(BigDecimal.ONE);

        assertNull(bottleTypeDTOs);
        verify(bottleTypeService).findWithHigherVolume(BigDecimal.ONE);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindBySize() {
        when(bottleTypeService.findBySize(BigDecimal.ONE)).thenReturn(Collections.singletonList(bottleType));

        List<BottleTypeDTO> bottleTypeDTOs = bottleTypeFacade.findBySize(BigDecimal.ONE);

        assertNotNull(bottleTypeDTOs);
        assertEquals(bottleType.getName(), bottleTypeDTOs.get(0).getName());
        verify(bottleTypeService).findBySize(BigDecimal.ONE);
        verify(beanMappingService).mapTo(Collections.singletonList(bottleType), BottleTypeDTO.class);
    }

    @Test
    public void testFindBySizeNull() {
        when(bottleTypeService.findBySize(BigDecimal.ONE)).thenReturn(null);

        List<BottleTypeDTO> bottleTypeDTOs = bottleTypeFacade.findBySize(BigDecimal.ONE);

        assertNull(bottleTypeDTOs);
        verify(bottleTypeService).findBySize(BigDecimal.ONE);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testCreateBottleType() {
        bottleTypeFacade.createBottleType(bottleTypeDTO);

        verify(bottleTypeService).createBottleType(bottleType);
        verify(beanMappingService).mapTo(bottleTypeDTO, BottleType.class);
    }
}
