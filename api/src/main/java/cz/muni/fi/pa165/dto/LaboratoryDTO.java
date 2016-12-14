package cz.muni.fi.pa165.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author rk
 * @date 2016-11-22
 */
public class LaboratoryDTO {
    
    private Long id;
    private String name;
    
    private List<PersonDTO> persons;
    private List<BottleDTO> bottlesToCheck;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDTO> persons) {
        this.persons = persons;
    }

    public List<BottleDTO> getBottlesToCheck() {
        return bottlesToCheck;
    }

    public void setBottlesToCheck(List<BottleDTO> bottlesToCheck) {
        this.bottlesToCheck = bottlesToCheck;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 21;
    }     

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof LaboratoryDTO)) {
            return false;
        }
        LaboratoryDTO lab = (LaboratoryDTO)obj;
        return (name != null ? name.equals(lab.getName()) : lab.getName() == null);
    }

    @Override
    public String toString() {
        return "LaboratoryDTO{" + "id=" + id + ", name=" + name + "}";
    }
}
