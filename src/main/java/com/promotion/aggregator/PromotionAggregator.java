package com.promotion.aggregator;

import java.util.List;
import java.util.Optional;

import com.promotion.PromotionResult;

/**
 * This interface is to define strategy - how to combine the promotions.
 * Whether apply promotions or use highest, to give an example.
 * @author Shailendra
 *
 */
public interface PromotionAggregator {
	/**
	 * Decides the promotions
	 * @param promotionResults All promotions applicable
	 * @return Final result in the form of {@link PromotionAggregatorResult}
	 */
	Optional<PromotionAggregatorResult> decide(List<PromotionResult> promotionResults);
}
