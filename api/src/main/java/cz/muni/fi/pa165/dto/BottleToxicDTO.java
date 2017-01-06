package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Jakub Fiser
 * @date 21/12/2016
 */
public class BottleToxicDTO {

    @NotNull
    private Long id;

    @NotNull
    private Boolean toxic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getToxic() {
        return toxic;
    }

    public void setToxic(Boolean toxic) {
        this.toxic = toxic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BottleToxicDTO)) return false;

        BottleToxicDTO that = (BottleToxicDTO) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getToxic() != null ? getToxic().equals(that.getToxic()) : that.getToxic() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getToxic() != null ? getToxic().hashCode() : 0);
        return result;
    }
}
