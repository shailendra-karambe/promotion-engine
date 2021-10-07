package com.promotion.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Cart object to hold all items and applicable discount
 * @author Shailendra
 *
 */
public class Cart {

    private final List<Item> items;
    private int total;
    private int discount;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Item item) {
        if (item != null) {
            this.items.add(item);
        }
    }

    public boolean remove(Item item) {
        return this.items.remove(item);
    }

    public List<Item> items() {
        return Collections.synchronizedList(this.items);
    }

    public int total() {
        if (this.total == 0) {
            this.total = this.items.stream().map(Item::totalPrice).reduce(0, Integer::sum);
        }
        return this.total;
    }

    public int discount() {
        return this.discount;
    }

    public int finalPrice() {
        return this.total() - this.discount();
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
