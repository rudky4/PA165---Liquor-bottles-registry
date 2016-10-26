package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Store;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * @author Martin Sumera
 */
@Repository
public class ManufacturerDAOImpl implements ManufacturerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createManufacturer(Manufacturer Manufacturer) {
        entityManager.persist(Manufacturer);
    }

    @Override
    public Collection<Manufacturer> getAllManufacturers() {
        return entityManager.createQuery("SELECT s FROM Manufacturer s", Manufacturer.class).getResultList();
    }

    @Override
    public Manufacturer getManufacturerById(Long id) {
        return entityManager.find(Manufacturer.class, id);
    }

    @Override
    public void updateManufacturer(Manufacturer Manufacturer) {
        entityManager.merge(Manufacturer);
    }

    @Override
    public void deleteManufacturer(Manufacturer Manufacturer) {
        entityManager.remove(Manufacturer);
    }
}

