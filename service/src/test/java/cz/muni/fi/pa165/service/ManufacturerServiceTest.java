package cz.muni.fi.pa165.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import cz.muni.fi.pa165.AbstractServiceTest;
import cz.muni.fi.pa165.dao.ManufacturerDAO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.exceptions.RegisterServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Jakub Fiser
 * @date 24/11/2016
 */
public class ManufacturerServiceTest extends AbstractServiceTest {

    @Mock
    private ManufacturerDAO manufacturerDAO;

    @Mock
    private BottleService bottleService;

    @Autowired
    @InjectMocks
    private ManufacturerService manufacturerService;

    private Manufacturer manufacturer1;
    private Manufacturer manufacturer2;
    private Bottle bottle1;
    private Bottle bottle2;
    private Date date = new Date();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        manufacturer1 = new Manufacturer();
        manufacturer1.setId(1L);
        manufacturer1.setName("Manufacturer1");

        manufacturer2 = new Manufacturer();
        manufacturer2.setId(2L);
        manufacturer2.setName("Manufacturer2");

        bottle1 = new Bottle();
        bottle1.setId(1L);
        bottle1.setStickerID("id1");
        bottle1.setProduced(date);
        bottle1.setToxic(false);

        bottle2 = new Bottle();
        bottle2.setId(2L);
        bottle2.setStickerID("id2");
        bottle2.setProduced(date);
        bottle2.setToxic(false);
    }

    @Test
    public void createManufacturer() {
        manufacturerService.createManufacturer(manufacturer1);
        verify(manufacturerDAO).save(manufacturer1);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createManufacturerNullName() {
        when(manufacturerDAO.save(manufacturer1)).thenThrow(ConstraintViolationException.class);
        manufacturerService.createManufacturer(manufacturer1);
    }

    @Test(expected = DataAccessException.class)
    public void createManufacturerNonUniqueName() {
        when(manufacturerDAO.save(manufacturer1)).thenThrow(DataIntegrityViolationException.class);
        manufacturerService.createManufacturer(manufacturer1);
    }

    @Test
    public void findAll() {
        List<Manufacturer> list = Arrays.asList(manufacturer1, manufacturer2);
        when(manufacturerDAO.findAll()).thenReturn(list);
        List<Manufacturer> result = manufacturerService.findAll();

        Assert.assertNotNull(result);
        Assert.assertEquals(list, result);
    }

    @Test
    public void findAllEmpty() {
        when(manufacturerDAO.findAll()).thenReturn(Collections.emptyList());
        List<Manufacturer> result = manufacturerService.findAll();

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findByName() {
        when(manufacturerDAO.findByName(manufacturer1.getName())).thenReturn(manufacturer1);
        Manufacturer result = manufacturerService.findByName(manufacturer1.getName());

        Assert.assertNotNull(result);
        Assert.assertEquals(result, manufacturer1);
    }

    @Test
    public void findByNameNonExisting() {
        final String nonExistingName = "Manufacturer NonExisting";
        when(manufacturerDAO.findByName(nonExistingName)).thenReturn(null);
        Manufacturer result = manufacturerService.findByName(nonExistingName);

        Assert.assertNull(result);
    }

    @Test
    public void hasToxicProductionByIdNoBottles() {
        when(manufacturerDAO.findOne(manufacturer1.getId())).thenReturn(manufacturer1);
        when(bottleService.getAllBottlesFromManufacturer(manufacturer1)).thenReturn(Collections.emptyList());
        boolean result = manufacturerService.hasToxicProduction(manufacturer1.getId());

        Assert.assertFalse(result);
    }

    @Test
    public void hasToxicProductionByIdFalse() {
        List<Bottle> bottleList = Arrays.asList(bottle1, bottle2);
        when(manufacturerDAO.findOne(manufacturer1.getId())).thenReturn(manufacturer1);
        when(bottleService.getAllBottlesFromManufacturer(manufacturer1)).thenReturn(bottleList);
        boolean result = manufacturerService.hasToxicProduction(manufacturer1.getId());

        Assert.assertFalse(result);
    }

    @Test
    public void hasToxicProductionByIdTrue() {
        bottle1.setToxic(true);
        List<Bottle> bottleList = Arrays.asList(bottle1, bottle2);
        when(manufacturerDAO.findOne(manufacturer1.getId())).thenReturn(manufacturer1);
        when(bottleService.getAllBottlesFromManufacturer(manufacturer1)).thenReturn(bottleList);
        boolean result = manufacturerService.hasToxicProduction(manufacturer1.getId());

        Assert.assertTrue(result);
    }

    @Test(expected = RegisterServiceException.class)
    public void hasToxicProductionByIdNonExisting() {
        when(manufacturerDAO.findOne(manufacturer1.getId())).thenReturn(null);
        manufacturerService.hasToxicProduction(manufacturer1.getId());
    }

    @Test
    public void hasToxicProductionByManufacturerFalse() {
        List<Bottle> bottleList = Arrays.asList(bottle1, bottle2);
        when(bottleService.getAllBottlesFromManufacturer(manufacturer1)).thenReturn(bottleList);
        boolean result = manufacturerService.hasToxicProduction(manufacturer1);

        Assert.assertFalse(result);
    }

    @Test
    public void hasToxicProductionByManufacturerTrue() {
        bottle1.setToxic(true);
        List<Bottle> bottleList = Arrays.asList(bottle1, bottle2);
        when(bottleService.getAllBottlesFromManufacturer(manufacturer1)).thenReturn(bottleList);
        boolean result = manufacturerService.hasToxicProduction(manufacturer1);

        Assert.assertTrue(result);
    }

    @Test
    public void hasToxicProductionByManufacturerEmpty() {
        when(bottleService.getAllBottlesFromManufacturer(manufacturer1)).thenReturn(Collections.emptyList());
        boolean result = manufacturerService.hasToxicProduction(manufacturer1);

        Assert.assertFalse(result);
    }

    @Test
    public void markAllProducedBottlesSinceDateAsToxic() {
        List<Bottle> list = Arrays.asList(bottle1, bottle2);
        when(bottleService.getAllBottlesFromManufacturerFromDate(manufacturer1, date)).thenReturn(list);
        manufacturerService.markAllProducedBottlesSinceDateAsToxic(manufacturer1, date);

        verify(bottleService).setToxic(bottle1);
        verify(bottleService).setToxic(bottle2);
    }
}