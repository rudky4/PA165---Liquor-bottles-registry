package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public Long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
