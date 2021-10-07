package com.promotion.common;

/**
 * Item represents the product purchased by Customer. 
 * The discount will be calculated based on promotions that get applied.
 * @author Shailendra
 *
 */
public class Item {
    private final Product product;
    private final int pricePerUnit;
    private final int quantity;
    private int discount;
    private String appliedPromotion;

    public Item(Product product, int pricePerUnit, int quantity) {
        this.product = product;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getAppliedPromotion() {
        return appliedPromotion;
    }

    public void setAppliedPromotion(String appliedPromotion) {
        this.appliedPromotion = appliedPromotion;
    }

    public int totalPrice() {
        return this.quantity * this.pricePerUnit;
    }

    public int finalPrice() {
        return (this.quantity * this.pricePerUnit) - this.discount;
    }
}
