/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import java.util.List;

/**
 *
 * @author rk
 * @date 2016-11-22
 */
public class ManufacturerDTO {
    
    private Long id;
    private String name;
    private List<PersonDTO> persons;
    private List<BottleTypeDTO> typesProduced;

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

    public List<BottleTypeDTO> getTypesProduced() {
        return typesProduced;
    }

    public void setTypesProduced(List<BottleTypeDTO> typesProduced) {
        this.typesProduced = typesProduced;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ManufacturerDTO)) {
            return false;
        }

        ManufacturerDTO otherManufacturer = (ManufacturerDTO) other;
        return (getName() != null ?
                getName().equals(otherManufacturer.getName())
                :
                otherManufacturer.getName() == null);
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ManufacturerDTO{" + "id=" + id + ", name=" + name + ", persons=" + persons + ", typesProduced=" + typesProduced + '}';
    }
    
    
}
