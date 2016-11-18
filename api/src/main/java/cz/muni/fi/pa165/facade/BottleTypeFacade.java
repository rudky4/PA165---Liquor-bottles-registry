package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.enums.AlcoholType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author mhajas
 */
public interface BottleTypeFacade {
    void createBottleType(BottleTypeDTO type);

    List<BottleTypeDTO> findAll();

    List<BottleTypeDTO> findByAlcoholType(AlcoholType type);

    List<BottleTypeDTO> findWithHigherVolume(BigDecimal volume);

    List<BottleTypeDTO> findBySize(BigDecimal size);

    BottleTypeDTO findById(Long id);
}
