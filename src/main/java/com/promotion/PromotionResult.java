package com.promotion;

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
