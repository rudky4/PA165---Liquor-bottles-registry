package cz.muni.fi.pa165.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Jakub Fiser
 * @date 16/11/2016
 */
public interface BeanMappingService {

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    public  <T> Set<T> mapTo(Set<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
