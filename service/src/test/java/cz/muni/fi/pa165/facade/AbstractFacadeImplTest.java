package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.BeanMappingServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;

/**
 * @author Martin Sumera
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractFacadeImplTest {

    @Spy
    @Inject
    protected BeanMappingService beanMappingService;

}