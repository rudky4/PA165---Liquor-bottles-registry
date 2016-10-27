package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Manufacturer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by martin on 26/10/16.
 */
public interface ManufacturerDAO extends CrudRepository<Manufacturer, Long> {

    List<Manufacturer> findAll();

}
