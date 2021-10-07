package com.promotion.aggregator;

/**
 * Holds the final discount
 * @author Shailendra
 *
 */
public record PromotionAggregatorResult(int discount) {
    @Override
    public int discount() {
        return discount;
    }
}
