package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mhajas
 */
@ContextConfiguration(classes = PersistenceContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbstractDAOTest {
}
