package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.AlcoholType;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author mhajas
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "volume", "size"})
})
public class BottleType {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal volume;

    @Column(nullable = false)
    private BigDecimal size;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturedBy;

    public AlcoholType type;

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

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public AlcoholType getType() {
        return type;
    }

    public void setType(AlcoholType type) {
        this.type = type;
    }

    public Manufacturer getManufacturedBy() {
        return manufacturedBy;
    }

    public void setManufacturedBy(Manufacturer manufacturedBy) {
        this.manufacturedBy = manufacturedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof BottleType))
            return false;
        BottleType other = (BottleType) o;
        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) return false;
        if (getVolume() != null ? !getVolume().equals(other.getVolume()) : other.getVolume() != null) return false;
        return getSize() != null ? getSize().equals(other.getSize()) : other.getSize() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getVolume() != null ? getVolume().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        return result;
    }
}
