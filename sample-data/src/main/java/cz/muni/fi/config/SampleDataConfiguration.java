package cz.muni.fi.config;

import cz.muni.fi.sampledata.Initializer;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.sampledata.InitializerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * @author Jakub Fiser
 * @date 04/12/2016
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {InitializerImpl.class})
public class SampleDataConfiguration {

    @Autowired
    private Initializer initializer;

    @PostConstruct
    public void dataLoading() {
        initializer.loadData();
    }
}
