package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.enums.AlcoholType;

import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.List;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

import javax.validation.ConstraintViolationException;

/**
 * @author rk
 * @date 2016-10-25
 */
public class BottleDAOTest extends AbstractDAOTest {

    private Store store;
    private BottleType dzama_rhum;
    private Bottle b1;
    private Bottle b2;

    @Before
    public void initTest(){
        dzama_rhum = new BottleType();
        dzama_rhum.setName("Dzama Rhum");
        dzama_rhum.setSize(BigDecimal.valueOf(700));
        dzama_rhum.setType(AlcoholType.RUM);
        dzama_rhum.setVolume(BigDecimal.valueOf(40));
        bottleTypeDAO.save(dzama_rhum);

        store = new Store();
        store.setName("storeName");
        storeDAO.save(store);

        b1 = new Bottle();
        b1.setToxic(false);
        b1.setStickerID("ID1");
        b1.setProduced(new Date());
        b1.setBottleType(dzama_rhum);
        b1.setStore(store);

        b2 = new Bottle();
        b2.setToxic(false);
        b2.setStickerID("ID2");
        b2.setProduced(new Date());
        b2.setBottleType(dzama_rhum);
        b2.setStore(store);

        bottleDAO.save(b1);
        bottleDAO.save(b2);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createInsufficientBottleTest() {
        b2.setProduced(null);
        bottleDAO.save(b2);
        bottleDAO.findAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
        public void createNotUniqeStickerIDBottleTest() {
        b2.setStickerID("ID1");
        bottleDAO.save(b2);

        bottleDAO.findAll();
    }

    @Test
    public void createBottleTest() {
        List<Bottle> actual = bottleDAO.findAll();

        assertEquals(2, actual.size());
        Bottle bottle = actual.get(0);
        assertEquals(b1.getId(), bottle.getId());
        assertEquals(b1.isToxic(), bottle.isToxic());
        assertEquals(b1.getProduced(), bottle.getProduced());
        assertEquals(b1.getStickerID(), bottle.getStickerID());
        assertEquals(b1.getBottleType(), bottle.getBottleType());
        assertEquals(b1.getStore(), bottle.getStore());
    }

    @Test
    public void searchExistingBottleTest() {
        Bottle bottle = bottleDAO.findOne(b1.getId());
        Assert.assertNotNull(bottle);
        Assert.assertEquals(bottle, b1);
        Assert.assertFalse(bottle.isToxic());
        Assert.assertEquals(bottle.getBottleType(), dzama_rhum);
        Assert.assertEquals(bottle.getStickerID(), b1.getStickerID());
        Assert.assertEquals(bottle.getProduced(), b1.getProduced());
    }

    @Test
    public void searchNonexistingBottleTest() {
        Assert.assertNull(bottleDAO.findOne(Long.MAX_VALUE));
    }

    @Test
    public void updateBottleTest() {
        b1.setProduced(new Date(123)); 
        b1.setStickerID("ID9");
        b1.setToxic(true);
        
        bottleDAO.save(b1);
        List<Bottle> bottles = bottleDAO.findAll();
        
        Bottle updatedBottle = bottles.get(0);

        assertEquals(updatedBottle.getProduced(), new Date(123));
        assertEquals(updatedBottle.getStickerID(), "ID9");
        assertTrue(updatedBottle.isToxic());
    }

    @Test
    public void getAllBottlesFromManufacturerFromDateWithOne() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("name");
        manufacturerDAO.save(manufacturer);

        BottleType bottleType = new BottleType();
        bottleType.setName("Vodka Amundsen");
        bottleType.setSize(BigDecimal.valueOf(1000));
        bottleType.setType(AlcoholType.VODKA);
        bottleType.setVolume(BigDecimal.valueOf(37.5));
        bottleType.setManufacturedBy(manufacturer);
        bottleTypeDAO.save(bottleType);

        Bottle bottle = new Bottle();
        bottle.setToxic(false);
        bottle.setStickerID("ID1123");
        bottle.setProduced(new Date());
        bottle.setBottleType(bottleType);
        bottleDAO.save(bottle);

        List<Bottle> bottles = bottleDAO.getAllBottlesFromManufacturerFromDate(manufacturer, new Date(1));

        assertEquals(bottles.size(), 1);
    }

    @Test
    public void getAllBottlesFromManufacturerWithMany() {
        Manufacturer manufacturer1 = createManufacturer("nameadsdas");
        Manufacturer manufacturer2 = createManufacturer("differentfdsfdsdwq");
        Manufacturer manufacturer3 = createManufacturer("differentfdsfdsdwqdas");

        // Valid data
        Bottle first = createBottleWithBottleType(manufacturer1, new Date(11111), "2");
        Bottle second = createBottleWithBottleType(manufacturer1, new Date(111111), "22");
        Bottle third = createBottleWithBottleType(manufacturer1, new Date(8011111), "222");
        Bottle fourth = createBottleWithBottleType(manufacturer1, new Date(90), "2222");

        // Invalid data
        createBottleWithBottleType(manufacturer2, new Date(50), "22222");
        createBottleWithBottleType(manufacturer2, new Date(50), "2222222");
        createBottleWithBottleType(manufacturer3, new Date(190), "222222222");

        List<Bottle> bottles = bottleDAO.getAllBottlesFromManufacturer(manufacturer1);

        assertEquals(4, bottles.size());
        assertTrue(bottles.contains(first));
        assertTrue(bottles.contains(second));
        assertTrue(bottles.contains(third));
        assertTrue(bottles.contains(fourth));
    }

    @Test
    public void getAllBottlesFromManufacturerFromDateWithMany() {
        Manufacturer manufacturer1 = createManufacturer("name");
        Manufacturer manufacturer2 = createManufacturer("different");

        // Valid data
        Bottle first = createBottleWithBottleType(manufacturer1, new Date(11111), "1");
        Bottle second = createBottleWithBottleType(manufacturer1, new Date(111111), "11");
        Bottle third = createBottleWithBottleType(manufacturer1, new Date(8011111), "111");

        // Invalid data
        createBottleWithBottleType(manufacturer1, new Date(90), "1111");
        createBottleWithBottleType(manufacturer2, new Date(50), "111111");
        createBottleWithBottleType(manufacturer2, new Date(190), "11111111");

        List<Bottle> bottles = bottleDAO.getAllBottlesFromManufacturerFromDate(manufacturer1, new Date(100));

        assertEquals(3, bottles.size());
        assertTrue(bottles.contains(first));
        assertTrue(bottles.contains(second));
        assertTrue(bottles.contains(third));
    }
    
    @Test
    public void deleteBottleTest() {
        bottleDAO.delete(b1);
        Assert.assertEquals(bottleDAO.findAll().size(), 1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void setNullBottleTypeTest() {
        dzama_rhum = null;
        b1.setBottleType(dzama_rhum);
        bottleDAO.save(b1);
  
        List<Bottle> bottles = bottleDAO.findAll();
    }

    private Manufacturer createManufacturer(String name) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturerDAO.save(manufacturer);
        return manufacturer;
    }

    private Bottle createBottleWithBottleType(Manufacturer manufacture, Date date, String uniqueId) {
        BottleType bottleType = new BottleType();
        bottleType.setName(uniqueId);
        bottleType.setSize(BigDecimal.valueOf(1000));
        bottleType.setType(AlcoholType.RUM);
        bottleType.setVolume(BigDecimal.valueOf(37.5));
        bottleType.setManufacturedBy(manufacture);
        bottleTypeDAO.save(bottleType);

        Bottle bottle = new Bottle();
        bottle.setToxic(false);
        bottle.setStickerID(uniqueId);
        bottle.setProduced(date);
        bottle.setBottleType(bottleType);
        bottleDAO.save(bottle);

        return bottle;
    }
}
