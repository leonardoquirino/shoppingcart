package com.leonardo.shoppingcart.product;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.SerializationUtils;
import org.javamoney.moneta.FastMoney;
import org.junit.Before;
import org.junit.Test;

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
    public void testNullName() {
        assertThatThrownBy(() -> {
            new Product(null, FastMoney.MIN_VALUE);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testNullPrice() {
        assertThatThrownBy(() -> {
            new Product("Product", null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    
    @Test
    public void testNegativePrice() {
        assertThatThrownBy(() -> {
            new Product("Product", FastMoney.of(-1, "EUR"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void productIsSerializable() {
        final byte[] serializedMyProduct = SerializationUtils.serialize(product);
        final Product deserializedProduct = SerializationUtils.deserialize(serializedMyProduct);
        assertEquals(product, deserializedProduct);
    }

}