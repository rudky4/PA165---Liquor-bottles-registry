package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.ManufacturerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * @author Martin Sumera
 */
@Service
@Transactional
public class ManufacturerFacadeImpl implements ManufacturerFacade {

    @Inject
    private ManufacturerService manufacturerService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createManufacturer(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = beanMappingService.mapTo(manufacturerDTO, Manufacturer.class);
        manufacturerService.createManufacturer(manufacturer);
    }

    @Override
    public List<ManufacturerDTO> findAll() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        return manufacturers == null ? null : beanMappingService.mapTo(manufacturers, ManufacturerDTO.class);
    }

    @Override
    public void markAllProducedBottlesSinceDateAsToxic(ManufacturerDTO manufacturerDTO, Date date) {
        Manufacturer manufacturer = beanMappingService.mapTo(manufacturerDTO, Manufacturer.class);
        manufacturerService.markAllProducedBottlesSinceDateAsToxic(manufacturer, date);
    }

    @Override
    public boolean hasToxicProduction(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = beanMappingService.mapTo(manufacturerDTO, Manufacturer.class);
        return manufacturerService.hasToxicProduction(manufacturer);
    }

    @Override
    public boolean hasToxicProduction(Long id) {
        return manufacturerService.hasToxicProduction(id);
    }

    @Override
    public ManufacturerDTO findById(Long id) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        return manufacturer == null ? null : beanMappingService.mapTo(manufacturer, ManufacturerDTO.class);
    }

    @Override
    public ManufacturerDTO findByName(String name) {
        Manufacturer manufacturer = manufacturerService.findByName(name);
        return manufacturer == null ? null : beanMappingService.mapTo(manufacturer, ManufacturerDTO.class);
    }

}
