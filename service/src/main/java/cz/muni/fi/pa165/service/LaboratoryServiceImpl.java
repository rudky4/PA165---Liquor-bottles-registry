package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.LaboratoryDAO;
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
}
