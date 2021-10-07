package com.promotion.aggregator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.promotion.PromotionResult;

/**
 * This class is strategy to apply promotion of highest value only 
 * @author Shailendra
 *
 */
public class HighestPromotionAggregator implements PromotionAggregator {
	@Override
	public Optional<PromotionAggregatorResult> decide(List<PromotionResult> promotionResults) {
		if (promotionResults != null && !promotionResults.isEmpty()) {
			Optional<PromotionResult> promotionResult = promotionResults.stream()
					.max(Comparator.comparingInt(PromotionResult::discount));
			return Optional.of(new PromotionAggregatorResult(promotionResult.get().discount()));
		}
		return Optional.empty();
	}
}
