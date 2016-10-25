package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author mhajas
 */
@Repository
public class BottleDAOImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BottleDAO bottleDAO;

    public void createBottle(Bottle b) {
        bottleDAO.save(b);
    }

    public List<Bottle> getAllBottles() {
        return bottleDAO.findAll();
    }


}
