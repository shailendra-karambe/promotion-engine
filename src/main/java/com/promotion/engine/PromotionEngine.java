package com.promotion.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.promotion.Promotion;
import com.promotion.PromotionResult;
import com.promotion.aggregator.PromotionAggregator;
import com.promotion.aggregator.PromotionAggregatorResult;
import com.promotion.common.Cart;
import com.promotion.common.Item;

/**
 * The class to apply the promotions, all logic of deciding and applying
 * promotions recides here.
 * 
 * @author Shailendra
 *
 */
public class PromotionEngine {
	private final List<Promotion> promotions;
	private final PromotionAggregator promotionAggregator;

	private PromotionEngine(List<Promotion> promotions, PromotionAggregator promotionAggregator) {
		this.promotions = promotions;
		this.promotionAggregator = promotionAggregator;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public PromotionAggregator getPromotionAggregator() {
		return promotionAggregator;
	}

	public void apply(Cart cart) {
		if (cart == null || cart.items().isEmpty()) {
			throw new RuntimeException("Cart can not be empty");
		}

		List<Item> items = cart.items();
		List<PromotionResult> promotionResults = this.promotions.stream().filter(Promotion::isActive)
				.map(promotion -> promotion.apply(items)).filter(Optional::isPresent).map(Optional::get)
				.collect(Collectors.toList());
		Optional<PromotionAggregatorResult> promotionAggregatorResult = this.promotionAggregator
				.decide(promotionResults);
		promotionAggregatorResult.ifPresent(aggregatorResult -> cart.setDiscount(aggregatorResult.discount()));
	}

	public static class Builder {
		private final List<Promotion> promotions = new ArrayList<>();
		private PromotionAggregator promotionAggregator;

		public Builder addPromotion(Promotion promotion) {
			if (promotion != null) {
				this.promotions.add(promotion);
			}
			return this;
		}

		public Builder addPromotionAggregator(PromotionAggregator promotionAggregator) {
			if (promotionAggregator != null) {
				this.promotionAggregator = promotionAggregator;
			}
			return this;
		}

		public PromotionEngine build() {
			if (this.promotions.isEmpty() || this.promotionAggregator == null) {
				throw new RuntimeException("Promotions and Promotion Aggregator is required");
			}
			return new PromotionEngine(this.promotions, this.promotionAggregator);
		}
	}
}
