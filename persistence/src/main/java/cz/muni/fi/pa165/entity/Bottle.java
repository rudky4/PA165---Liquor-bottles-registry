package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author mhajas
 */
@Entity
public class Bottle {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String stickerID;

    private boolean toxic;

    @Column(nullable = false)
    @NotNull
    private Date produced;

    @ManyToOne
    private BottleType bottleType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isToxic() {
        return toxic;
    }

    public void setToxic(boolean toxic) {
        this.toxic = toxic;
    }

    public Date getProduced() {
        return produced;
    }

    public void setProduced(Date produced) {
        this.produced = produced;
    }

    public String getStickerID() {
        return stickerID;
    }

    public void setStickerID(String stickerID) {
        this.stickerID = stickerID;
    }

    public BottleType getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleType bottleType) {
        this.bottleType = bottleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Bottle))
            return false;
        Bottle other = (Bottle) o;
        if (getStickerID() != null ? !getStickerID().equals(other.getStickerID()) : other.getStickerID() != null)
            return false;
        return getBottleType() != null ? getBottleType().equals(other.getBottleType()) : other.getBottleType() == null;
    }

    @Override
    public int hashCode() {
        int result = getStickerID() != null ? getStickerID().hashCode() : 0;
        result = 31 * result + (getBottleType() != null ? getBottleType().hashCode() : 0);
        return result;
    }
}
