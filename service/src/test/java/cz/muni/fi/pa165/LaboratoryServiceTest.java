package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.LaboratoryDAO;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.service.LaboratoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Sumera
 */
public class LaboratoryServiceTest extends AbstractServiceTest {

    @Mock
    private LaboratoryDAO laboratoryDAO;

    @InjectMocks
    @Autowired
    private LaboratoryService laboratoryService;

    private Long laboratoryId = 1L;

    private String laboratoryName = "laboratoryName";

    private Laboratory laboratory;

    private List<Laboratory> laboratories = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        laboratory = createLaboratory("laboratory");

        laboratories.add(createLaboratory("firstLaboratory"));
        laboratories.add(createLaboratory("secondLaboratory"));
    }

    @Test
    public void createBottleTypeWillNotCrash() {
        laboratoryService.createLaboratory(laboratory);
        verify(laboratoryDAO).save(laboratory);
    }

    @Test
    public void findAllWithEmptyList() {
        when(laboratoryDAO.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), laboratoryService.findAll());
    }

    @Test
    public void findAllWithNonEmptyList() {
        when(laboratoryDAO.findAll()).thenReturn(laboratories);

        assertEquals(laboratories, laboratoryService.findAll());
    }

    @Test
    public void findByIdReturnsNull() {
        when(laboratoryDAO.findOne(laboratoryId)).thenReturn(null);

        assertNull(laboratoryService.findById(laboratoryId));
    }

    @Test
    public void findByIdReturnsLaboratory() {
        when(laboratoryDAO.findOne(laboratoryId)).thenReturn(laboratory);

        assertEquals(laboratory, laboratoryService.findById(laboratoryId));
    }

    @Test
    public void findByNameReturnsNull() {
        when(laboratoryDAO.findByName(laboratoryName)).thenReturn(null);

        assertNull(laboratoryService.findByName(laboratoryName));
    }

    @Test
    public void findByNameReturnsLaboratory() {
        when(laboratoryDAO.findByName(laboratoryName)).thenReturn(laboratory);

        assertEquals(laboratory, laboratoryService.findByName(laboratoryName));
    }

    private Laboratory createLaboratory(String name) {
        Laboratory laboratory = new Laboratory();
        laboratory.setName(name);
        return laboratory;
    }

}
