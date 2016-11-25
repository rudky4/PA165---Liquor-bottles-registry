package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.service.ManufacturerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Fiser
 * @date 25/11/2016
 */
public class ManufacturerFacadeTest extends AbstractFacadeTest {

    @Mock
    private ManufacturerService manufacturerService;

    @Autowired
    @InjectMocks
    private ManufacturerFacade manufacturerFacade;

    private Manufacturer manufacturer;
    private ManufacturerDTO manufacturerDTO;
    private Date date = new Date();

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        manufacturerFacade = (ManufacturerFacade) unwrapProxy(manufacturerFacade);
        ReflectionTestUtils.setField(manufacturerFacade, "manufacturerService", manufacturerService);
        ReflectionTestUtils.setField(manufacturerFacade, "beanMappingService", beanMappingService);

        manufacturer = new Manufacturer();
        manufacturer.setId(0L);
        manufacturer.setName("manufacturer1");

        manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setId(1L);
        manufacturerDTO.setName("manufacturer2");
    }

    @Test
    public void createManufacturer() {
        when(beanMappingService.mapTo(manufacturerDTO, Manufacturer.class)).thenReturn(manufacturer);

        manufacturerFacade.createManufacturer(manufacturerDTO);

        verify(beanMappingService).mapTo(manufacturerDTO, Manufacturer.class);
        verify(manufacturerService).createManufacturer(manufacturer);
    }

    @Test
    public void findAll() {
        final List<Manufacturer> manufacturerList = Collections.singletonList(manufacturer);
        when(manufacturerService.findAll()).thenReturn(manufacturerList);

        List<ManufacturerDTO> result = manufacturerFacade.findAll();

        assertNotNull(result);
        assertEquals(manufacturerList.size(), result.size());

        ManufacturerDTO resultManufacturer = result.get(0);

        assertEquals(manufacturer.getName(), resultManufacturer.getName());
        assertEquals(manufacturer.getId(), resultManufacturer.getId());
        assertNull(resultManufacturer.getTypesProduced());
        assertNull(resultManufacturer.getPersons());
    }

    @Test
    public void findById() {
        when(manufacturerService.findById(manufacturer.getId())).thenReturn(manufacturer);

        ManufacturerDTO result = manufacturerFacade.findById(manufacturer.getId());

        assertNotNull(result);
        assertEquals(result.getId(), manufacturer.getId());
        assertEquals(result.getName(), manufacturer.getName());
        assertNull(result.getPersons());
        assertNull(result.getTypesProduced());
    }

    @Test
    public void findByName() {
        when(manufacturerService.findByName(manufacturer.getName())).thenReturn(manufacturer);

        ManufacturerDTO result = manufacturerFacade.findByName(manufacturer.getName());

        assertNotNull(result);
        assertEquals(result.getName(), manufacturer.getName());
        assertEquals(result.getId(), manufacturer.getId());
        assertNull(result.getPersons());
        assertNull(result.getTypesProduced());
    }

    @Test
    public void markAllProducedBottlesSinceDateAsToxic() {
        when(beanMappingService.mapTo(manufacturerDTO, Manufacturer.class)).thenReturn(manufacturer);

        manufacturerFacade.markAllProducedBottlesSinceDateAsToxic(manufacturerDTO, date);

        verify(manufacturerService).markAllProducedBottlesSinceDateAsToxic(manufacturer, date);
    }

    @Test
    public void hasToxicProductionById() {
        when(manufacturerService.hasToxicProduction(manufacturer.getId())).thenReturn(true);

        boolean result = manufacturerFacade.hasToxicProduction(manufacturer.getId());

        verify(manufacturerService).hasToxicProduction(manufacturer.getId());
        assertTrue(result);
    }

    @Test
    public void hasToxicProductionByManufacturer() {
        when(beanMappingService.mapTo(manufacturerDTO, Manufacturer.class)).thenReturn(manufacturer);
        when(manufacturerService.hasToxicProduction(manufacturer)).thenReturn(true);

        boolean result = manufacturerFacade.hasToxicProduction(manufacturerDTO);

        verify(manufacturerService).hasToxicProduction(manufacturer);
        assertTrue(result);
    }
}
