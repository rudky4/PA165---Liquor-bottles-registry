package cz.muni.fi.sampledata;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.enums.PersonRole;
import cz.muni.fi.pa165.service.BottleService;
import cz.muni.fi.pa165.service.BottleTypeService;
import cz.muni.fi.pa165.service.ManufacturerService;
import cz.muni.fi.pa165.service.PersonService;
import cz.muni.fi.pa165.service.TimeService;
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
        Manufacturer manufacturer = manufacturerService.findAll().get(0);

        BottleType bottleType1 = new BottleType();
        bottleType1.setName("Bottle Type 1");
        bottleType1.setType(AlcoholType.COGNAC);
        bottleType1.setVolume(BigDecimal.TEN);
        bottleType1.setSize(BigDecimal.ONE);
        bottleType1.setManufacturedBy(manufacturer);

        BottleType bottleType2 = new BottleType();
        bottleType2.setName("Bottle Type 2");
        bottleType2.setType(AlcoholType.RUM);
        bottleType2.setVolume(BigDecimal.TEN);
        bottleType2.setSize(BigDecimal.ONE);
        bottleType2.setManufacturedBy(manufacturer);

        bottleTypeService.createBottleType(bottleType1);
        bottleTypeService.createBottleType(bottleType2);
    }

    private void createBottles() {
        List<BottleType> bottleTypes = bottleTypeService.findAll();
        Date date = timeService.getCurrentDate();

        for(int i=0; i<bottleTypes.size(); i++) {
            Bottle bottle = new Bottle();
            bottle.setToxic(false);
            bottle.setProduced(date);
            bottle.setStickerID("StickerID" + i);
            bottle.setBottleType(bottleTypes.get(i));
            bottleService.createBottle(bottle);
        }
    }
}
