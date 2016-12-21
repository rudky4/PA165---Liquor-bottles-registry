package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static cz.muni.fi.pa165.enums.AlcoholType.*;
import static cz.muni.fi.pa165.enums.PersonRole.*;

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
    @Inject
    private LaboratoryService labService;

    @Override
    public void loadData() {
        createManufacturers();
        createBottleTypes();
        createStores();
        createLaboratories();
        createPersons();
        createBottles();
    }

    private void createManufacturers() {
        createManufacturer("Bohemia bottles", Collections.emptyList());
        createManufacturer("Brno Líh", Collections.emptyList());
        createManufacturer("Absolut", Collections.emptyList());
    }

    private void createBottleTypes() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        Manufacturer bohemia = manufacturers.get(0);
        Manufacturer brnoLih = manufacturers.get(1);
        Manufacturer absolut = manufacturers.get(2);

        createBottleType("Rumíček", RUM, 40, 1, bohemia);
        createBottleType("Vodečka", VODKA, 35, 1, bohemia);
        createBottleType("Vodka jemná", VODKA, 20, 1, bohemia);

        createBottleType("Brnenská whisky", WHISKEY, 38, 2, brnoLih);
        createBottleType("Brnenský cognac", COGNAC, 42, 1, brnoLih);

        createBottleType("Absolut Vodka", VODKA, 40, 1, absolut);
        createBottleType("Absolut Strong", VODKA, 65, 1, absolut);
    }

    private void createStores() {
        createStore("Tesco", Collections.emptyList());
        createStore("Kaufland", Collections.emptyList());
        createStore("Brněnka", Collections.emptyList());
        createStore("Potraviny u Adámka", Collections.emptyList());
        createStore("Na rohu", Collections.emptyList());
    }

    private void createLaboratories(){
        createLaboratory("Národní lihový ústav");
        createLaboratory("LihoLab Praha");
    }

    private void createPersons() {
        createPolice("admin", "admin", "Michal", "michal@liquor.com");
        createPolice("police", "police", "Policajt", "police@liquor.com");

        List<Manufacturer> manufacturers = manufacturerService.findAll();
        createManufacturerPerson("manufacturer", "manufacturer", "Janko", "manu@liquor.com", manufacturers.get(0));
        createManufacturerPerson("manu1", "manu1", "Peter", "manu1@liquor.com", manufacturers.get(1));

        List<Laboratory> laboratories = labService.findAll();
        createLabPerson("laboratory", "laboratory", "Michal", "labo@liquor.com", laboratories.get(0));
        createLabPerson("lab1", "lab1", "Tom", "labo1@liquor.com", laboratories.get(1));

        List<Store> stores = storeService.findAll();
        createStoreOwner("store", "store", "Johny", "store@liquor.com", stores.get(0));
        createStoreOwner("store1", "store1", "Johny", "store1@liquor.com", stores.get(1));
    }

    private void createBottles() {
        List<BottleType> bottleTypes = bottleTypeService.findAll();
        List<Store> stores = storeService.findAll();
        List<Laboratory> laboratories = labService.findAll();

        // Nontoxic in store
        for(int si=0; si<stores.size() - 2; si++) {
            for (int bi = 0; bi < bottleTypes.size(); bi++) {
                String id = "ID25" + bi + "38" + si;
                createBottle(false, id, bottleTypes.get(bi), stores.get(si));
            }
        }

        // Toxic in store
        for(int si=0; si<stores.size() - 2; si++) {
            for (int bi = 0; bi < bottleTypes.size(); bi++) {
                String id = "ID74" + bi + si + "4";
                createBottle(true, id, bottleTypes.get(bi), stores.get(si));
            }
        }

        // Another nontoxic in store
        for(int si=0; si<stores.size() - 1; si++) {
            for (int bi = 0; bi < bottleTypes.size(); bi++) {
                String id1 = "ID00" + bi + "00" + si;
                createBottle(false, id1, bottleTypes.get(bi), stores.get(si));
            }
        }

        //  In store and in lab
        for(int si=0; si<stores.size() - 2; si++) {
            for (int bi = 0; bi < bottleTypes.size(); bi++) {
                String id = "ID65" + bi + si + "4";
                Laboratory lab = laboratories.get(si % 2);
                createBottle(true, id, bottleTypes.get(bi), stores.get(si), lab);
            }
        }
    }

    private void createManufacturer(String name, List<Person> persons) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setPersons(persons);
        manufacturerService.createManufacturer(manufacturer);
    }

    private void createBottleType(String name, AlcoholType type, int vol, int size, Manufacturer manufacturer) {
        BottleType bottleType = new BottleType();
        bottleType.setName(name);
        bottleType.setType(type);
        bottleType.setVolume(new BigDecimal(vol));
        bottleType.setSize(new BigDecimal(size));
        bottleType.setManufacturedBy(manufacturer);
        bottleTypeService.createBottleType(bottleType);
    }

    private void createStore(String name, List<Person> persons) {
        Store store = new Store();
        store.setName(name);
        store.setPersons(persons);
        storeService.createStore(store);
    }

    private void createLaboratory(String name) {
        Laboratory lab = new Laboratory();
        lab.setName(name);
        labService.createLaboratory(lab);
    }

    private void createStoreOwner(String login, String password, String name, String mail, Store store) {
        Person person = new Person();
        person.setLogin(login);
        person.setName(name);
        person.setEmail(mail);
        person.setRole(STORE_OWNER);
        person.setStore(store);
        personService.registerPerson(person, password);
    }

    private void createLabPerson(String login, String password, String name, String mail, Laboratory lab) {
        Person person = new Person();
        person.setLogin(login);
        person.setName(name);
        person.setEmail(mail);
        person.setRole(LAB);
        person.setLaboratory(lab);
        personService.registerPerson(person, password);
    }

    private void createManufacturerPerson(String login, String password, String name, String mail, Manufacturer manu) {
        Person person = new Person();
        person.setLogin(login);
        person.setName(name);
        person.setEmail(mail);
        person.setRole(MANUFACTURER);
        person.setManufacturer(manu);
        personService.registerPerson(person, password);
    }

    private void createPolice(String login, String password, String name, String mail) {
        Person person = new Person();
        person.setLogin(login);
        person.setName(name);
        person.setEmail(mail);
        person.setRole(POLICE);
        personService.registerPerson(person, password);
    }

    private void createBottle(boolean toxic, String stickerId, BottleType bottleType, Store store) {
        Bottle bottle = new Bottle();
        bottle.setToxic(toxic);
        bottle.setProduced(timeService.getCurrentDate());
        bottle.setStickerID(stickerId);
        bottle.setBottleType(bottleType);
        bottle.setStore(store);
        bottleService.createBottle(bottle);
    }

    private void createBottle(boolean toxic, String stickerId, BottleType bottleType, Store store, Laboratory lab) {
        Bottle bottle = new Bottle();
        bottle.setToxic(toxic);
        bottle.setProduced(timeService.getCurrentDate());
        bottle.setStickerID(stickerId);
        bottle.setBottleType(bottleType);
        bottle.setStore(store);
        bottle.setLaboratory(lab);
        bottleService.createBottle(bottle);
    }

}
