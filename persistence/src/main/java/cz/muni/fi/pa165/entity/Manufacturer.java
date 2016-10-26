package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
        if (getId() != otherManufacturer.getId()) {
            return false;
        }
        if (getName() != null ?
                !getName().equals(otherManufacturer.getName())
                :
                otherManufacturer.getName() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
