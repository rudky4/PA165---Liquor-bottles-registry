package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Jakub Fiser
 * @date 20/12/2016
 */
public class BottleCreateDTO {

    @NotNull
    private String stickerID;

    @NotNull
    private BottleTypeDTO bottleType;

    @NotNull
    private Long storeId;

    public String getStickerID() {
        return stickerID;
    }

    public void setStickerID(String stickerID) {
        this.stickerID = stickerID;
    }

    public BottleTypeDTO getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleTypeDTO bottleType) {
        this.bottleType = bottleType;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BottleCreateDTO)) return false;

        BottleCreateDTO that = (BottleCreateDTO) o;

        if (getStickerID() != null ? !getStickerID().equals(that.getStickerID()) : that.getStickerID() != null)
            return false;
        if (getBottleType() != null ? !getBottleType().equals(that.getBottleType()) : that.getBottleType() != null)
            return false;
        return getStoreId() != null ? getStoreId().equals(that.getStoreId()) : that.getStoreId() == null;
    }

    @Override
    public int hashCode() {
        int result = getStickerID() != null ? getStickerID().hashCode() : 0;
        result = 31 * result + (getBottleType() != null ? getBottleType().hashCode() : 0);
        result = 31 * result + (getStoreId() != null ? getStoreId().hashCode() : 0);
        return result;
    }
}
