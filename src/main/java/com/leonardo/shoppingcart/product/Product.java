package com.leonardo.shoppingcart.product;

import com.leonardo.shoppingcart.base.BaseDocument;
import lombok.EqualsAndHashCode;
import org.javamoney.moneta.FastMoney;
import org.springframework.data.mongodb.core.index.TextIndexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Product extends BaseDocument<String> implements Serializable {

    private static final long serialVersionUID = -7087034090684719098L;

    @NotNull
    @NotBlank
    @TextIndexed
    private String name;

    @NotNull
    @EqualsAndHashCode.Exclude
    private FastMoney price;

    public Product(String name, FastMoney price) {
        this(null, name, price);
    }

    public Product(String id, String name, FastMoney price) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        }

        if (name == null || price == null) {
            throw new IllegalArgumentException("Product fields should not be null");
        }

        this.name = name;

        if (price.isLessThan(0)) {
            throw new IllegalArgumentException("Price should not be negative");
        }

        this.price = price;
    }
}
