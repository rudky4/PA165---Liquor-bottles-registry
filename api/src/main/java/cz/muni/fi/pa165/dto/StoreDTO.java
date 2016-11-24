package cz.muni.fi.pa165.dto;

import java.util.List;

/**
 *
 * @author rk
 * @date 2016-11-22
 */
public class StoreDTO {
    private Long id;
    private String name;
    
    private List<PersonDTO> persons;
    private List<BottleDTO> bottles;

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

    public List<BottleDTO> getBottles() {
        return bottles;
    }

    public void setBottles(List<BottleDTO> bottles) {
        this.bottles = bottles;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof StoreDTO)) {
            return false;
        }

        StoreDTO otherStore = (StoreDTO) other;
        return (getName() != null ? getName().equals(otherStore.getName()) : otherStore.getName() == null);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 21;
    }    

    @Override
    public String toString() {
        return "StoreDTO{" + "id=" + id + ", name=" + name + ", persons=" + persons + ", bottles=" + bottles + '}';
    }    
}
