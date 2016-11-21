package cz.muni.fi.pa165.configuration;

import cz.muni.fi.pa165.service.PersonServiceImpl;
import cz.muni.fi.pa165.config.PersistenceContext;
import cz.muni.fi.pa165.facade.PersonFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jakub Fiser
 * @date 16/11/2016
 */
@Configuration
@Import(PersistenceContext.class)
@ComponentScan(basePackageClasses = {PersonServiceImpl.class, PersonFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        // currently mapping is being done only by properties name, type conversion is applied if necessary
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }
}
