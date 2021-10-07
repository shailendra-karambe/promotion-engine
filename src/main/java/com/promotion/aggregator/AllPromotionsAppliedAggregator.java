package com.promotion.aggregator;
import java.util.List;
import java.util.Optional;

import com.promotion.PromotionResult;

/**
 * This strategy applies the all promotions
 * @author Shailendra
 *
 */
public class AllPromotionsAppliedAggregator implements PromotionAggregator {
    @Override
    public Optional<PromotionAggregatorResult> decide(List<PromotionResult> promotionResults) {
        if (promotionResults != null && !promotionResults.isEmpty()) {
            int totalDiscount = promotionResults.stream().map(PromotionResult::discount).reduce(0, Integer::sum);
            return Optional.of(new PromotionAggregatorResult(totalDiscount));
        }
        return Optional.empty();
    }
}