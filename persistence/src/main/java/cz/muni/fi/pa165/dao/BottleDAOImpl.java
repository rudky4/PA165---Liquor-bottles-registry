package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * @author mhajas
 */
@Repository
public class BottleDAOImpl implements BottleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createBottle(Bottle bottle) {
        entityManager.persist(bottle);
    }

    @Override
    public Collection<Bottle> getAllBottles() {
        return entityManager.createQuery("Select b From Bottle b", Bottle.class).getResultList();
    }

    @Override
    public Bottle getBottleById(Long id) {
        return entityManager.find(Bottle.class, id);
    }

    @Override
    public void updateBottle(Bottle bottle) {
        entityManager.merge(bottle);
    }

    @Override
    public void deleteBottle(Bottle bottle) {
        entityManager.remove(bottle);
    }
}
