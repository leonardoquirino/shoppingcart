package com.leonardo.shoppingcart.cart;

import com.leonardo.shoppingcart.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CartItem {
    @NotNull
    private Product product;

    @NotNull
    @EqualsAndHashCode.Exclude
    private Long quantity;

    public void subtractQuantity(Long quantity) {
        if (this.getQuantity() > quantity) {
            this.quantity = (this.getQuantity() - quantity);
        }

        this.quantity = 0L;
    }

    public void addQuantity(Long quantity) {
        this.quantity += quantity;
    }
}
