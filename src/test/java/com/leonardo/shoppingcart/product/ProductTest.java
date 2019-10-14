package com.leonardo.shoppingcart.product;

import org.apache.commons.lang3.SerializationUtils;
import org.javamoney.moneta.FastMoney;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() {
        this.product = new Product("Original Product", FastMoney.of(10L, "EUR"));
    }

    @Test
    public void testNullArguments() {
        assertThatThrownBy(() -> {
            new Product(null, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void productIsSerializable() {
        final byte[] serializedMyProduct = SerializationUtils.serialize(product);
        final Product deserializedProduct = SerializationUtils.deserialize(serializedMyProduct);
        assertEquals(product, deserializedProduct);
    }

}