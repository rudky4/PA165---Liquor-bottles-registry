package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Laboratory;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rk
 * @date 2016-10-25
 */
@Repository
public class LaboratoryDAOImpl implements LaboratoryDAO{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createLaboratory(Laboratory laboratory) {
        em.persist(laboratory);
    }

    @Override
    public Collection<Laboratory> getAllLaboratories() {
        return em.createQuery("Select lab From Laboratory lab", Laboratory.class).getResultList();
    }

    @Override
    public Laboratory getLaboratoryById(Long id) {
        return em.find(Laboratory.class, id);
    }

    @Override
    public void updateLaboratory(Laboratory laboratory) {
        em.merge(laboratory);
    }

    @Override
    public void deleteLaboratory(Laboratory laboratory) {
        em.remove(laboratory);
    }
    
}
