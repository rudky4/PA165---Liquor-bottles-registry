package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.service.LaboratoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Sumera
 */
public class LaboratoryFacadeTest extends AbstractFacadeTest {

    @Mock
    private LaboratoryService laboratoryService;

    @Autowired
    @InjectMocks
    private LaboratoryFacade laboratoryFacade;

    private Laboratory laboratory;

    private LaboratoryDTO laboratoryDTO;

    private Long laboratoryId = 1L;

    private String laboratoryName = "name";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        laboratory = new Laboratory();
        laboratory.setName(laboratoryName);

        laboratoryDTO = new LaboratoryDTO();
        laboratoryDTO.setName(laboratoryName);
    }

    @Test
    public void testFindAll() {
        when(laboratoryService.findAll()).thenReturn(Collections.singletonList(laboratory));

        List<LaboratoryDTO> laboratories = laboratoryFacade.findAll();

        assertEquals(laboratory.getName(), laboratories.get(0).getName());
        verify(laboratoryService).findAll();
        verify(beanMappingService).mapTo(Collections.singletonList(laboratory), LaboratoryDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(laboratoryService.findAll()).thenReturn(null);

        List<LaboratoryDTO> laboratories = laboratoryFacade.findAll();

        assertNull(laboratories);
        verify(laboratoryService).findAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(laboratoryService.findById(laboratoryId)).thenReturn(laboratory);

        LaboratoryDTO laboratoryDTO = laboratoryFacade.findById(laboratoryId);

        assertNotNull(laboratoryDTO);
        assertEquals(laboratory.getName(), laboratoryDTO.getName());
        verify(laboratoryService).findById(laboratoryId);
        verify(beanMappingService).mapTo(laboratory, LaboratoryDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(laboratoryService.findById(laboratoryId)).thenReturn(null);

        LaboratoryDTO laboratoryDTO = laboratoryFacade.findById(laboratoryId);

        assertNull(laboratoryDTO);
        verify(laboratoryService).findById(laboratoryId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(laboratoryService.findByName(laboratoryName)).thenReturn(laboratory);

        LaboratoryDTO laboratoryDTO = laboratoryFacade.findByName(laboratoryName);

        assertNotNull(laboratoryDTO);
        assertEquals(laboratory.getName(), laboratoryDTO.getName());
        verify(laboratoryService).findByName(laboratoryName);
        verify(beanMappingService).mapTo(laboratory, LaboratoryDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(laboratoryService.findByName(laboratoryName)).thenReturn(null);

        LaboratoryDTO laboratoryDTO = laboratoryFacade.findByName(laboratoryName);

        assertNull(laboratoryDTO);
        verify(laboratoryService).findByName(laboratoryName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testCreateLaboratory() {
        laboratoryFacade.createLaboratory(laboratoryDTO);

        verify(laboratoryService).createLaboratory(laboratory);
        verify(beanMappingService).mapTo(laboratoryDTO, Laboratory.class);
    }

}
