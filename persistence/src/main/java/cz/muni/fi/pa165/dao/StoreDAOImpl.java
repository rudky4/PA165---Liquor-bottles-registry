package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Store;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * @author Martin Sumera
 */
@Repository
public class StoreDAOImpl implements StoreDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createStore(Store store) {
        entityManager.persist(store);
    }

    @Override
    public Collection<Store> getAllStores() {
        return entityManager.createQuery("SELECT s FROM Store s", Store.class).getResultList();
    }

    @Override
    public Store getStoreById(Long id) {
        return entityManager.find(Store.class, id);
    }

    @Override
    public void updateStore(Store store) {
        entityManager.merge(store);
    }

    @Override
    public void deleteStore(Store store) {
        entityManager.remove(store);
    }
}
