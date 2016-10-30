package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceContext;
import cz.muni.fi.pa165.dao.StoreDAO;
import cz.muni.fi.pa165.entity.Store;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jakub Fiser
 *         27/10/2016
 */
@ContextConfiguration(classes = PersistenceContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class StoreDAOTest {

    @Autowired
    private StoreDAO storeDAO;

    private Store store;

    @Before
    public void initTest() {
        store = new Store();
        store.setName("Store 1");
        storeDAO.createStore(store);
    }

    @Test
    public void createStoreTest() {
        List<Store> stores = new ArrayList<>(storeDAO.getAllStores());

        Assert.assertEquals(1, stores.size());

        Store persistStore = stores.get(0);

        Assert.assertEquals(store, persistStore);
        Assert.assertEquals(store.getName(), persistStore.getName());
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void createNullNameStoreTest() {
        Store store2 = new Store();
        storeDAO.createStore(store2);

        storeDAO.getAllStores();
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void createNonUniqueNameStoreTest() {
        Store store2 = new Store();
        store2.setName(store.getName());
        storeDAO.createStore(store2);

        storeDAO.getAllStores();
    }

    @Test
    public void searchExistingStoreTest() {
        Store store2 = storeDAO.getStoreById(store.getId());

        Assert.assertNotNull(store2);
        Assert.assertEquals(store, store2);
        Assert.assertEquals(store.getName(), store2.getName());
    }

    @Test
    public void updateStoreTest() {
        String newName = "newName";
        store.setName(newName);
        storeDAO.updateStore(store);

        Store updatedStore = storeDAO.getStoreById(store.getId());

        Assert.assertEquals(store, updatedStore);
        Assert.assertEquals(store.getName(), updatedStore.getName());
        Assert.assertEquals(updatedStore.getName(), newName);
    }

    @Test
    public void deleteStoreTest() {
        storeDAO.deleteStore(store);

        Assert.assertNull(storeDAO.getStoreById(store.getId()));
    }
}