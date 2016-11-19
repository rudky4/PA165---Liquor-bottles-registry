package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Laboratory;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author rk
 * @date 2016-11-18
 */
public interface LaboratoryService {
    void registerLaboratory(Laboratory laboratory);
    List<Laboratory> findAll();
    Laboratory findLaboratoryById(Long laboratoryId);
    Laboratory findLaboratoryByName(String name);
}
