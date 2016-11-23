package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author rk
 * @date 2016-10-25
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Laboratory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
        
    @Column(nullable = false)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "laboratory")
    private List<Person> persons;

    @OneToMany
    private List<Bottle> bottlesToCheck;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Bottle> getBottlesToCheck() {
        return bottlesToCheck;
    }

    public void setBottlesToCheck(List<Bottle> bottlesToCheck) {
        this.bottlesToCheck = bottlesToCheck;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Laboratory)) {
            return false;
        }
        Laboratory lab = (Laboratory)obj;
        return (name != null ? name.equals(lab.getName()) : lab.getName() == null);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 21;
    }       
}
