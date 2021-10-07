package com.promotion;

import com.promotion.common.Item;
import com.promotion.common.Product;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the promotion applied to Product of type C and D
 * @author Shailendra
 *
 */
public class ProductCAndDPromotion implements Promotion {

    private final String name;
    private final boolean isActive;

    public ProductCAndDPromotion(boolean isActive) {
        this.name = "Promotion on C & D";
        this.isActive = isActive;
    }

    @Override
    public Optional<PromotionResult> apply(List<Item> items) {
        if (items != null && !items.isEmpty()) {
            int discountedPrice = 30;
            //Per One qunatity of C and D price is 'discountedPrice'
            Optional<Item> productOfTypeC = items.stream().filter(itm -> itm.getProduct() == Product.C).findFirst();
            Optional<Item> productOfTypeD = items.stream().filter(itm -> itm.getProduct() == Product.D).findFirst();
            if (productOfTypeC.isPresent() && productOfTypeD.isPresent()) {
                Item itemOfTypeC = productOfTypeC.get();
                Item itemOfTypeD = productOfTypeD.get();
                int priceOfProductCAndD = itemOfTypeC.getPricePerUnit() + itemOfTypeD.getPricePerUnit();
                int discountedPriceOfProductC = (int) ((itemOfTypeC.getPricePerUnit() / (float) priceOfProductCAndD) * discountedPrice);
                int discountedPriceOfProductD = discountedPrice - discountedPriceOfProductC;
                if (itemOfTypeC.getQuantity() < itemOfTypeD.getQuantity()) {
                	//Type C products are less in quantity than Type D, so all products of Type C are at discounted price
                    itemOfTypeC.setDiscount(itemOfTypeC.totalPrice() - (discountedPriceOfProductC * itemOfTypeC.getQuantity()));
                    int discountOnDType = itemOfTypeD.totalPrice() - (discountedPriceOfProductD * itemOfTypeC.getQuantity())
                            - ((itemOfTypeD.getQuantity() - itemOfTypeC.getQuantity()) * itemOfTypeD.getPricePerUnit());
                    itemOfTypeD.setDiscount(discountOnDType);
                } else if (itemOfTypeC.getQuantity() > itemOfTypeD.getQuantity()) {
                	//Type D products are less in quantity than Type C, so all products of Type D are at discounted price
                    int discountOnCType = itemOfTypeC.totalPrice() - (discountedPriceOfProductC * itemOfTypeD.getQuantity())
                            - ((itemOfTypeC.getQuantity() - itemOfTypeD.getQuantity()) * itemOfTypeC.getPricePerUnit());
                    itemOfTypeC.setDiscount(discountOnCType);
                    itemOfTypeD.setDiscount(itemOfTypeD.totalPrice() - (discountedPriceOfProductD * itemOfTypeD.getQuantity()));
                } else {
                	//Both products of Type C and D will be at 'discountedPrice' per unit
                    int discountOnCType = itemOfTypeC.totalPrice() - (discountedPriceOfProductC * itemOfTypeD.getQuantity())
                            - ((itemOfTypeC.getQuantity() - itemOfTypeD.getQuantity()) * itemOfTypeC.getPricePerUnit());
                    itemOfTypeC.setDiscount(discountOnCType);
                    int discountOnDType = itemOfTypeD.totalPrice() - (discountedPriceOfProductD * itemOfTypeC.getQuantity())
                            - ((itemOfTypeD.getQuantity() - itemOfTypeC.getQuantity()) * itemOfTypeD.getPricePerUnit());
                    itemOfTypeD.setDiscount(discountOnDType);
                }
                itemOfTypeC.setAppliedPromotion(name());
                itemOfTypeD.setAppliedPromotion(name());
                return Optional.of(new PromotionResult(name(), itemOfTypeC.getDiscount() + itemOfTypeD.getDiscount(), true));
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
