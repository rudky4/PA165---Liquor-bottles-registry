package cz.muni.fi.pa165.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.muni.fi.config.SampleDataConfiguration;
import cz.muni.fi.pa165.controllers.BottleTypeController;
import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.mixin.LaboratoryDTOMixin;
import cz.muni.fi.pa165.mixin.ManufacturerDTOMixin;
import cz.muni.fi.pa165.mixin.PersonDTOMixin;
import cz.muni.fi.pa165.mixin.StoreDTOMixin;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Jakub Fiser
 * @date 04/12/2016
 */
@Configuration
@EnableWebMvc
@Import({ServiceConfiguration.class, SampleDataConfiguration.class})
@ComponentScan(basePackageClasses = {BottleTypeController.class})
public class RestConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }

    private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH));

        objectMapper.addMixIn(LaboratoryDTO.class, LaboratoryDTOMixin.class);
        objectMapper.addMixIn(ManufacturerDTO.class, ManufacturerDTOMixin.class);
        objectMapper.addMixIn(PersonDTO.class, PersonDTOMixin.class);
        objectMapper.addMixIn(StoreDTO.class, StoreDTOMixin.class);

        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
