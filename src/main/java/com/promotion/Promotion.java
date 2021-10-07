package com.promotion;

import com.promotion.common.Item;

import java.util.List;
import java.util.Optional;

/**
 * The interface for defining strategy of applying promotions.
 * The implementing class will decide the logic of applying the promotions
 * @author Shailendra
 *
 */
public interface Promotion {
	/**
	 * Apply the promotion to supplied items
	 * @param items List of {@link Item}
	 * @return Promotion result with discount if any
	 */
    Optional<PromotionResult> apply(List<Item> items);

    /**
     * Whether promotion is active
     * @return true/false
     */
    boolean isActive();

    /**
     * Name of promotion
     * @return Name
     */
    String name();
}
