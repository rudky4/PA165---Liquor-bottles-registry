package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceConfiguration;
import cz.muni.fi.pa165.dao.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mhajas
 */
@ContextConfiguration(classes = PersistenceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public abstract class AbstractDAOTest {
    @Autowired
    protected BottleDAO bottleDAO;

    @Autowired
    protected BottleTypeDAO bottleTypeDAO;

    @Autowired
    protected LaboratoryDAO laboratoryDAO;

    @Autowired
    protected ManufacturerDAO manufacturerDAO;

    @Autowired
    protected StoreDAO storeDAO;

    @Autowired
    protected PersonDAO personDAO;
}
