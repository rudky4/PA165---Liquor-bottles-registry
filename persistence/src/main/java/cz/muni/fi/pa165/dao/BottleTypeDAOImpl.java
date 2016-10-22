package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.BottleType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * @author mhajas
 */
@Repository
public class BottleTypeDAOImpl implements BottleTypeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createBottleType(BottleType bottleType) {
        entityManager.persist(bottleType);
    }

    @Override
    public Collection<BottleType> getAllBottleTypes() {
        return entityManager.createQuery("Select b From BottleType b", BottleType.class).getResultList();
    }

    @Override
    public BottleType getBottleTypeById(Long id) {
        return entityManager.find(BottleType.class, id);
    }

    @Override
    public void updateBottleType(BottleType bottleType) {
        entityManager.merge(bottleType);
    }

    @Override
    public void deleteBottleType(BottleType bottleType) {
        entityManager.remove(bottleType);
    }
}
