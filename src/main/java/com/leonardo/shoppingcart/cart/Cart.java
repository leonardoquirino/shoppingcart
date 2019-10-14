package com.leonardo.shoppingcart.cart;

import com.leonardo.shoppingcart.base.BaseDocument;
import com.leonardo.shoppingcart.product.Product;
import lombok.Data;
import org.javamoney.moneta.FastMoney;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Document
public class Cart extends BaseDocument<String> implements Serializable {
    private static final long serialVersionUID = -3103102409684208158L;

    @NotNull
    @NotBlank
    private String clientId;

    @NotNull
    private List<CartItem> itemList;

    public Cart(String clientId, List<CartItem> itemList) {
        this(null, clientId, itemList);
    }

    public Cart(String id, String clientId, List<CartItem> itemList) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        }

        if (clientId == null || itemList == null) {
            throw new IllegalArgumentException("Cart fields should not be null");
        }

        this.clientId = clientId;
        this.itemList = itemList;
    }

    public FastMoney getCartTotal() {
        FastMoney total = FastMoney.of(0, "EUR");
        for (CartItem item : itemList) {
            total = total.add(item.getProduct().getPrice().multiply(item.getQuantity()));
        }

        return total;
    }

    public void addItem(CartItem item) {
        for (CartItem itemIter : itemList) {
            if (itemIter.equals(item)) {
                itemIter.addQuantity(item.getQuantity());
                return;
            }
        }

        itemList.add(item);
    }

    public void changeItemQuantity(Product product, Long qty) {
        CartItem item = new CartItem(product, qty);
        for (CartItem itemIter : itemList) {
            if (itemIter.equals(item)) {
                itemIter.setQuantity(qty);
                break;
            }
        }
    }

    public void subtractItem(CartItem item) {
        for (CartItem itemIter : itemList) {
            if (itemIter.equals(item)) {
                if (itemIter.getQuantity() > item.getQuantity()) {
                    itemIter.setQuantity(itemIter.getQuantity() - item.getQuantity());
                } else {
                    itemList.remove(itemIter);
                }
                break;
            }
        }
    }
}
