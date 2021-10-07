package com.promotion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.promotion.aggregator.AllPromotionsAppliedAggregator;
import com.promotion.common.Cart;
import com.promotion.common.Item;
import com.promotion.common.Product;
import com.promotion.engine.PromotionEngine;

public class PromotionEngineTest {
	@Test
    void testScenarioA() {
        Item a = new Item(Product.A, 50, 1);
        Item b = new Item(Product.B, 30, 1);
        Item c = new Item(Product.C, 20, 1);
        Cart cart = new Cart();
        cart.add(a);
        cart.add(b);
        cart.add(c);
        PromotionEngine promotionEngine = new PromotionEngine.Builder().addPromotion(new ProductAPromotion(true))
                .addPromotion(new ProductBPromotion(true)).addPromotion(new ProductCAndDPromotion(true))
                .addPromotionAggregator(new AllPromotionsAppliedAggregator()).build();
        promotionEngine.apply(cart);
        Assertions.assertEquals(100, cart.finalPrice());
        Assertions.assertEquals(100, cart.total());
        Assertions.assertEquals(0, cart.discount());
    }
}
