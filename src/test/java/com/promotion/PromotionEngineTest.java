package com.promotion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.promotion.aggregator.AllPromotionsAppliedAggregator;
import com.promotion.aggregator.HighestPromotionAggregator;
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
	
	@Test
    void testScenarioB() {
        Item a = new Item(Product.A, 50, 5);
        Item b = new Item(Product.B, 30, 5);
        Item c = new Item(Product.C, 20, 1);
        Cart cart = new Cart();
        cart.add(a);
        cart.add(b);
        cart.add(c);
        PromotionEngine promotionEngine = new PromotionEngine.Builder().addPromotion(new ProductAPromotion(true))
                .addPromotion(new ProductBPromotion(true)).addPromotion(new ProductCAndDPromotion(true))
                .addPromotionAggregator(new AllPromotionsAppliedAggregator()).build();
        promotionEngine.apply(cart);
        Assertions.assertEquals(370, cart.finalPrice());
        Assertions.assertEquals(420, cart.total());
        Assertions.assertEquals(50, cart.discount());
    }
	
	@Test
    void testScenarioC() {
        Item a = new Item(Product.A, 50, 3);
        Item b = new Item(Product.B, 30, 5);
        Item c = new Item(Product.C, 20, 1);
        Item d = new Item(Product.D, 15, 1);
        Cart cart = new Cart();
        cart.add(a);
        cart.add(b);
        cart.add(c);
        cart.add(d);
        PromotionEngine promotionEngine = new PromotionEngine.Builder().addPromotion(new ProductAPromotion(true))
                .addPromotion(new ProductBPromotion(true)).addPromotion(new ProductCAndDPromotion(true))
                .addPromotionAggregator(new AllPromotionsAppliedAggregator()).build();
        promotionEngine.apply(cart);
        Assertions.assertEquals(280, cart.finalPrice());
        Assertions.assertEquals(335, cart.total());
        Assertions.assertEquals(55, cart.discount());
    }
	
	@Test
    void testScenario() {
        Item a = new Item(Product.A, 50, 3);
        Item b = new Item(Product.B, 30, 5);
        Item c = new Item(Product.C, 20, 6);
        Item d = new Item(Product.D, 15, 8);
        Cart cart = new Cart();
        cart.add(a);
        cart.add(b);
        cart.add(c);
        cart.add(d);
        PromotionEngine promotionEngine = new PromotionEngine.Builder().addPromotion(new ProductAPromotion(true))
                .addPromotion(new ProductBPromotion(true)).addPromotion(new ProductCAndDPromotion(true))
                .addPromotionAggregator(new AllPromotionsAppliedAggregator()).build();
        promotionEngine.apply(cart);
        Assertions.assertEquals(460, cart.finalPrice());
        Assertions.assertEquals(540, cart.total());
        Assertions.assertEquals(80, cart.discount());
    }
	
	@Test
    void testScenarioHighestDiscount() {
        Item a = new Item(Product.A, 50, 5);
        Item b = new Item(Product.B, 30, 5);
        Item c = new Item(Product.C, 20, 1);
        Cart cart = new Cart();
        cart.add(a);
        cart.add(b);
        cart.add(c);
        PromotionEngine promotionEngine = new PromotionEngine.Builder().addPromotion(new ProductAPromotion(true))
                .addPromotion(new ProductBPromotion(true)).addPromotion(new ProductCAndDPromotion(true))
                .addPromotionAggregator(new HighestPromotionAggregator()).build();
        promotionEngine.apply(cart);
        Assertions.assertEquals(390, cart.finalPrice());
        Assertions.assertEquals(420, cart.total());
        Assertions.assertEquals(30, cart.discount());
    }
}
