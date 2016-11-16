package cz.muni.fi.pa165;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;

import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * @author Jakub Fiser
 * @date 16/11/2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AbstractServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
}
