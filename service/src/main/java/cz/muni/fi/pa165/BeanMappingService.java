package cz.muni.fi.pa165;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Jakub Fiser
 * @date 16/11/2016
 */
public interface BeanMappingService {

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
