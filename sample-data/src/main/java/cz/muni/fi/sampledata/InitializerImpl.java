package cz.muni.fi.sampledata;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.enums.PersonRole;
import cz.muni.fi.pa165.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Jakub Fiser
 * @date 04/12/2016
 */
@Component
@Transactional
public class InitializerImpl implements Initializer {

    @Inject
    private BottleService bottleService;
    @Inject
    private BottleTypeService bottleTypeService;
    @Inject
    private StoreService storeService;
    @Inject
    private ManufacturerService manufacturerService;
    @Inject
    private TimeService timeService;
    @Inject
    private PersonService personService;

    @Override
    public void loadData() {
        createManufacturers();
        createBottleTypes();
        createBottles();
        createPersons();
        createStores();
    }

    private void createPersons() {
        Person p = new Person();
        p.setLogin("admin");
        p.setName("Michal");
        p.setEmail("michal@liquor-repository.com");
        p.setRole(PersonRole.CUSTOMER);
        personService.registerPerson(p, "admin");

        p = new Person();
        p.setLogin("admin2");
        p.setName("Rudolf");
        p.setEmail("rudolf@liquor-repository.com");
        p.setRole(PersonRole.MANUFACTURER);
        personService.registerPerson(p, "admin");

        p = new Person();
        p.setLogin("police");
        p.setName("Policajt Janko");
        p.setEmail("police@police.com");
        p.setRole(PersonRole.POLICE);
        personService.registerPerson(p, "police");
    }

    private void createStores() {
        Store store1 = new Store();
        store1.setName("Store 1");
        store1.setPersons(Collections.emptyList());
        store1.setBottles(Collections.emptyList());

        Store store2 = new Store();
        store2.setName("Store 2");
        store2.setPersons(Collections.emptyList());
        store2.setBottles(Collections.emptyList());

        Store store3 = new Store();
        store3.setName("Store 3");
        store3.setPersons(Collections.emptyList());
        store3.setBottles(Collections.emptyList());

        storeService.createStore(store1);
        storeService.createStore(store2);
        storeService.createStore(store3);
    }

    private void createManufacturers() {
        Manufacturer manufacturer1 = new Manufacturer();
        manufacturer1.setName("Manufacturer 1");
        manufacturer1.setPersons(Collections.emptyList());
        Manufacturer manufacturer2 = new Manufacturer();
        manufacturer2.setPersons(Collections.emptyList());
        manufacturer2.setName("Manufacturer 2");

        manufacturerService.createManufacturer(manufacturer1);
        manufacturerService.createManufacturer(manufacturer2);
    }

    private void createBottleTypes() {
        for (Manufacturer manufacturer : manufacturerService.findAll()) {
            BottleType bottleType1 = new BottleType();
            bottleType1.setName("Bottle Type 1 " + manufacturer.getName());
            bottleType1.setType(AlcoholType.COGNAC);
            bottleType1.setVolume(BigDecimal.TEN);
            bottleType1.setSize(BigDecimal.ONE);
            bottleType1.setManufacturedBy(manufacturer);

            BottleType bottleType2 = new BottleType();
            bottleType2.setName("Bottle Type 2 "  + manufacturer.getName());
            bottleType2.setType(AlcoholType.RUM);
            bottleType2.setVolume(BigDecimal.TEN);
            bottleType2.setSize(BigDecimal.ONE);
            bottleType2.setManufacturedBy(manufacturer);

            bottleTypeService.createBottleType(bottleType1);
            bottleTypeService.createBottleType(bottleType2);
        }
    }

    private void createBottles() {
        List<BottleType> bottleTypes = bottleTypeService.findAll();
        Date date = timeService.getCurrentDate();

        for(int i=0; i<bottleTypes.size(); i++) {
            Bottle bottle = new Bottle();
            bottle.setToxic(true);
            bottle.setProduced(date);
            bottle.setStickerID("StickerIDT" + i);
            bottle.setBottleType(bottleTypes.get(i));
            bottleService.createBottle(bottle);
        }

        for(int i=0; i<bottleTypes.size(); i++) {
            Bottle bottle = new Bottle();
            bottle.setToxic(false);
            bottle.setProduced(date);
            bottle.setStickerID("StickerIDNT" + i);
            bottle.setBottleType(bottleTypes.get(i));
            bottleService.createBottle(bottle);
        }
    }
}
