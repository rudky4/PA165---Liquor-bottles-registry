package cz.muni.fi.pa165.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import cz.muni.fi.pa165.enums.AlcoholType;

import java.math.BigDecimal;

/**
 * @author mhajas
 */
public class BottleTypeDTO {
    private Long id;

    @NotNull
    private String name;

    @DecimalMin("0")
    private BigDecimal volume;

    @DecimalMin("0.5")
    private BigDecimal size;

    private AlcoholType type;

    private ManufacturerDTO manufacturedBy;

    private boolean deleted;

    private int bottleCount = 0;

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

    public ManufacturerDTO getManufacturedBy() {
        return manufacturedBy;
    }

    public void setManufacturedBy(ManufacturerDTO manufacturedBy) {
        this.manufacturedBy = manufacturedBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getBottleCount() {
        return bottleCount;
    }

    public void setBottleCount(int bottleCount) {
        this.bottleCount = bottleCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof BottleTypeDTO))
            return false;
        BottleTypeDTO other = (BottleTypeDTO) o;
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

    @Override
    public String toString() {
        return "BottleTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                ", size=" + size +
                ", type=" + type +
                '}';
    }
}
