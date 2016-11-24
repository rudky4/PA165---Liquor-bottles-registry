package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Store;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.List;


/**
 * @author Jakub Fiser
 *         27/10/2016
 */
public class StoreDAOTest extends AbstractDAOTest {

    private Store store;

    @Before
    public void initTest() {
        store = new Store();
        store.setName("Store 1");
        storeDAO.save(store);
    }

    @Test
    public void createStoreTest() {
        List<Store> stores = storeDAO.findAll();

        Assert.assertEquals(1, stores.size());

        Store persistStore = stores.get(0);

        Assert.assertEquals(store, persistStore);
        Assert.assertEquals(store.getName(), persistStore.getName());
    }

    @Test(expected = javax.validation.ValidationException.class)
    public void createNullNameStoreTest() {
        Store store = new Store();
        storeDAO.save(store);

        storeDAO.findAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createNonUniqueNameStoreTest() {
        Store store2 = new Store();
        store2.setName(store.getName());
        storeDAO.save(store2);

        storeDAO.findAll();
    }

    @Test
    public void searchExistingStoreTest() {
        Store store2 = storeDAO.findOne(store.getId());

        Assert.assertNotNull(store2);
        Assert.assertEquals(store, store2);
        Assert.assertEquals(store.getName(), store2.getName());
    }

    @Test
    public void updateStoreTest() {
        String newName = "newName";
        store.setName(newName);
        storeDAO.save(store);

        Store updatedStore = storeDAO.findOne(store.getId());

        Assert.assertEquals(store, updatedStore);
        Assert.assertEquals(store.getName(), updatedStore.getName());
        Assert.assertEquals(updatedStore.getName(), newName);
    }

    @Test
    public void deleteStoreTest() {
        storeDAO.delete(store);

        Assert.assertNull(storeDAO.findOne(store.getId()));
    }
}