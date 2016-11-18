package cz.muni.fi.pa165.dto;

import java.util.Date;

/**
 * @author mhajas
 */
public class BottleDTO {

    private Long id;

    private String stickerID;

    private boolean toxic;

    private Date produced;

    private BottleTypeDTO bottleType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStickerID() {
        return stickerID;
    }

    public void setStickerID(String stickerID) {
        this.stickerID = stickerID;
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

    public BottleTypeDTO getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleTypeDTO bottleType) {
        this.bottleType = bottleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof BottleDTO))
            return false;
        BottleDTO other = (BottleDTO) o;
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

    @Override
    public String toString() {
        return "BottleDTO{" +
                "id=" + id +
                ", stickerID='" + stickerID + '\'' +
                ", toxic=" + toxic +
                ", produced=" + produced +
                ", bottleType=" + bottleType +
                '}';
    }
}
