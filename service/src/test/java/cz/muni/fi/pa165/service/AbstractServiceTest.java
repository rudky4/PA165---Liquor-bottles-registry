package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.config.ServiceConfiguration;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * @author Jakub Fiser
 * @date 16/11/2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {

    @Inject
    protected BeanMappingService beanMappingService;
}
