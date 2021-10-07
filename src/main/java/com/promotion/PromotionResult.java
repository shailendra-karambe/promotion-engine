package com.promotion;

/**
 * Holds the result after applying promotions
 * @author Shailendra
 *
 */
public record PromotionResult(String promotion, int discount, boolean applied) {

    public String getPromotion() {
        return promotion;
    }

    public int getDiscount() {
        return discount;
    }

    public boolean isApplied() {
        return applied;
    }
}
