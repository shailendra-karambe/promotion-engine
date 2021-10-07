package com.promotion;

import com.promotion.common.Item;
import com.promotion.common.Product;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the promotion applied to Product of type B
 * @author Shailendra
 *
 */
public class ProductBPromotion implements Promotion {

    private final String name;
    private final boolean isActive;

    public ProductBPromotion(boolean isActive) {
        this.name = "Promotion on B";
        this.isActive = isActive;
    }

    @Override
    public Optional<PromotionResult> apply(List<Item> items) {
        if (items != null && !items.isEmpty()) {
            int discountedPrice = 45;
            int howManyQuantity = 2;
            //Get the Item of product type A, will be one Item
            Optional<Item> applicableItem = items.stream().filter(itm -> itm.getProduct() == Product.B).findFirst();
            if (applicableItem.isPresent()) {
                Item item = applicableItem.get();
                int quantity = item.getQuantity();
                //Per 'howManyQuantity' quantity price is 'discountedPrice', rest is at unit price
                int productCountAtDiscountedPrice = quantity / howManyQuantity;
                int totalDiscountedPrice = (discountedPrice * productCountAtDiscountedPrice) +
                        (item.getPricePerUnit() * (quantity % howManyQuantity));
                int calculatedDiscount = item.totalPrice() - totalDiscountedPrice;
                item.setDiscount(calculatedDiscount);
                item.setAppliedPromotion(name());
                return Optional.of(new PromotionResult(item.getAppliedPromotion(), item.getDiscount(), true));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public String name() {
        return this.name;
    }
}
