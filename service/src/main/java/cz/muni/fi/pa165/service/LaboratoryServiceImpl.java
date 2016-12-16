package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.LaboratoryDAO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Laboratory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.inject.Inject;

/**
 *
 * @author rk
 * @date 2016-11-22
 */
@Service
public class LaboratoryServiceImpl implements LaboratoryService{
    @Inject
    private LaboratoryDAO laboratoryDAO;
    
    @Override
    public void createLaboratory(Laboratory laboratory) {
        laboratoryDAO.save(laboratory);
    }

    @Override
    public List<Laboratory> findAll() {
        return laboratoryDAO.findAll();
    }

    @Override
    public Laboratory findById(Long id) {
        return laboratoryDAO.findOne(id);
    }

    @Override
    public Laboratory findByName(String name) {
        return laboratoryDAO.findByName(name);
    }   
    
    @Override
    public Laboratory findWithLeastBottles() {
        Laboratory lab = findByBottlesToCheckCountAsc();
        return lab;
    }

    @Override
    public List<Bottle> getBottlesToCheck(Long id){
        return laboratoryDAO.findOne(id).getBottlesToCheck();
    }


    private Laboratory findByBottlesToCheckCountAsc() {
        List<Laboratory> labs = laboratoryDAO.findAll();
        if(labs == null || labs.isEmpty()) {
            return null;
        }
        Laboratory result = labs.get(0);
        Laboratory toCompare;
        for (int i=1; i<labs.size(); i++) {
            toCompare = labs.get(i);
            if(toCompare.getBottlesToCheck().size() < result.getBottlesToCheck().size()) {
                result = toCompare;
            }
        }
        return result;
    }
}
