package pojos;

import java.util.List;

public class Promotion {
    private String promotionId;
    private Integer orderId;
    private List<String> promoArea = null;
    private String promoType;
    private Boolean showPrice;
    private Boolean showText;
    private LocalizedTexts localizedTexts;
    private List<Property> properties = null;
    private List<Image> images = null;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<String> getPromoArea() {
        return promoArea;
    }

    public void setPromoArea(List<String> promoArea) {
        this.promoArea = promoArea;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public Boolean getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Boolean showPrice) {
        this.showPrice = showPrice;
    }

    public Boolean getShowText() {
        return showText;
    }

    public void setShowText(Boolean showText) {
        this.showText = showText;
    }

    public LocalizedTexts getLocalizedTexts() {
        return localizedTexts;
    }

    public void setLocalizedTexts(LocalizedTexts localizedTexts) {
        this.localizedTexts = localizedTexts;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
