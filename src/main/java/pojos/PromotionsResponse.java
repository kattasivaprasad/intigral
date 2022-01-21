package pojos;

import java.util.List;

public class PromotionsResponse {
    private List<Promotion> promotions = null;

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}
