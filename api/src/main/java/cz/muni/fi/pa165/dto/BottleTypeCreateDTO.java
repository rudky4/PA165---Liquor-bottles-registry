package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.AlcoholType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Jakub Fiser
 * @date 20/12/2016
 */
public class BottleTypeCreateDTO {

    @NotNull
    private String name;

    @DecimalMin("0")
    private BigDecimal volume;

    @DecimalMin("0.5")
    private BigDecimal size;

    @NotNull
    private AlcoholType type;

    @NotNull
    private Long manufacturerId;

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

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BottleTypeCreateDTO)) return false;

        BottleTypeCreateDTO that = (BottleTypeCreateDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getVolume() != null ? !getVolume().equals(that.getVolume()) : that.getVolume() != null) return false;
        if (getSize() != null ? !getSize().equals(that.getSize()) : that.getSize() != null) return false;
        if (getType() != that.getType()) return false;
        return getManufacturerId() != null ? getManufacturerId().equals(that.getManufacturerId()) : that.getManufacturerId() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getVolume() != null ? getVolume().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getManufacturerId() != null ? getManufacturerId().hashCode() : 0);
        return result;
    }
}
