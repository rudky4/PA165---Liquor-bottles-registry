package cz.muni.fi.pa165.dto;

import java.util.List;

/**
 * @author Martin Sumera
 */
public class ManufacturerDTO {

    private Long id;

    private String name;

    private List<PersonDTO> persons;


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



    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof ManufacturerDTO)) {
            return false;
        }

        ManufacturerDTO m = (ManufacturerDTO) obj;

        boolean isNameEqual = name != null ? name.equals(m.getName()) : m.getName() == null;
        boolean isPersonsEqual = persons != null ? persons.equals(m.getPersons()) : m.getPersons() == null;

        return isNameEqual && isPersonsEqual;
    }

    @Override
    public int hashCode() {
        int result = 31 * (name != null ? name.hashCode() : 0);
        result += 21 * (persons != null ? persons.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ManufacturerDTO{" +
                "id=" + id +
                ", name='" + name;
    }

}
