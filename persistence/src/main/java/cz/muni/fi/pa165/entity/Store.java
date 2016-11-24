package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Martin Sumera
 */
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    @OneToMany
    private List<Person> persons;

    @OneToMany
    private List<Bottle> bottles;

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

    public List<Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(List<Bottle> bottles) {
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
        if (!(other instanceof Store)) {
            return false;
        }

        Store otherStore = (Store) other;
        return (getName() != null ? getName().equals(otherStore.getName()) : otherStore.getName() == null);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 21;
    } 
}
