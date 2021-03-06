package cz.muni.fi.pa165.config;

import cz.muni.fi.pa165.sampledata.Initializer;
import cz.muni.fi.pa165.sampledata.InitializerImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Jakub Fiser
 * @date 04/12/2016
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {InitializerImpl.class})
public class SampleDataConfiguration {

    @Inject
    private Initializer initializer;

    @PostConstruct
    public void dataLoading() {
        initializer.loadData();
    }
}
