package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Martin Sumera
 */
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    @OneToMany
    private List<Person> persons;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturedBy")
    private List<BottleType> typesProduced;

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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<BottleType> getTypesProduced() {
        return typesProduced;
    }

    public void setTypesProduced(List<BottleType> typesProduced) {
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
        if (!(other instanceof Manufacturer)) {
            return false;
        }

        Manufacturer otherManufacturer = (Manufacturer) other;
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
}
