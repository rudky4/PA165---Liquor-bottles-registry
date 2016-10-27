package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceContext;
import cz.muni.fi.pa165.dao.LaboratoryDAO;
import cz.muni.fi.pa165.entity.Laboratory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author mhajas
 */
@ContextConfiguration(classes = PersistenceContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LaboratoryDaoTest {

    @Autowired
    private LaboratoryDAO laboratoryDAO;

    @Test
    public void createLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("SPECIAL LAB");

        laboratoryDAO.createLaboratory(lab);

        List<Laboratory> actual = new ArrayList<>(laboratoryDAO.getAllLaboratories());

        assertEquals("Number of Laboratories in database should be 1", 1, actual.size());
        assertEquals("Laboratory names should be the same", lab.getName(), actual.get(0).getName());
    }
    @Test(expected = IllegalArgumentException.class )
    public void createLaboratoryNull() {
        laboratoryDAO.createLaboratory(null);
    }


    @Test(expected = ValidationException.class )
    public void createLaboratoryNullName() {
        Laboratory lab = new Laboratory();
        lab.setName(null);
        laboratoryDAO.createLaboratory(lab);
        laboratoryDAO.getAllLaboratories();
    }

    @Test(expected = PersistenceException.class )
    public void createLaboratoryDuplicateName() {
        Laboratory lab = new Laboratory();
        Laboratory lab1 = new Laboratory();
        lab.setName("SPECIAL LAB");
        lab1.setName("SPECIAL LAB");
        laboratoryDAO.createLaboratory(lab);
        laboratoryDAO.createLaboratory(lab1);
        laboratoryDAO.getAllLaboratories();
    }

    @Test
    public void getLaboratoryByIdTest() {
        Laboratory lab = new Laboratory();
        Laboratory lab1 = new Laboratory();
        lab.setName("SPECIAL LAB");
        lab1.setName("SPECIAL LAB1");

        laboratoryDAO.createLaboratory(lab);
        laboratoryDAO.createLaboratory(lab1);

        Laboratory actual = laboratoryDAO.getLaboratoryById(lab1.getId());

        assertEquals(lab1.getName(), actual.getName());
    }

    @Test
    public void updateLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("SPECIAL LAB");

        laboratoryDAO.createLaboratory(lab);

        lab.setName("CHANGED NAME");
        laboratoryDAO.updateLaboratory(lab);

        List<Laboratory> actual = new ArrayList<>(laboratoryDAO.getAllLaboratories());
        assertEquals("Number of Laboratories in database should be 1", 1, actual.size());
        assertEquals("Laboratory name should be changed", "CHANGED NAME", actual.get(0).getName());
    }

    @Test
    public void deleteLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("SPECIAL LAB");

        laboratoryDAO.deleteLaboratory(lab);

        List<Laboratory> actual = new ArrayList<>(laboratoryDAO.getAllLaboratories());
        assertEquals("Number of Laboratories in database should be 0", 0, actual.size());
    }

}
