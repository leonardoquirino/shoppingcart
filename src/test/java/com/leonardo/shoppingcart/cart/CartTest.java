package com.leonardo.shoppingcart.cart;

import com.leonardo.shoppingcart.product.Product;
import org.javamoney.moneta.FastMoney;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CartTest {
    private Cart cart;
    private CartItem item3;

    @Before
    public void setUp() throws Exception {
        List<CartItem> cartItemList = new ArrayList<>();
        item3 = new CartItem(new Product("Prod3", FastMoney.of(3, "EUR")), 3L);
        cartItemList.add(new CartItem(new Product("Prod1", FastMoney.of(1, "EUR")), 1L));
        cartItemList.add(new CartItem(new Product("Prod2", FastMoney.of(2, "EUR")), 2L));
        cartItemList.add(item3);

        cart = new Cart("Cliente", cartItemList);
    }

    @Test
    public void getCartTotal() {
        assertEquals(FastMoney.of(14, "EUR"), cart.getCartTotal());
    }

    @Test
    public void getCartTotalAndAddItem() {
        cart.addItem(new CartItem(new Product("Prod4", FastMoney.of(4, "EUR")), 4L));

        assertEquals(FastMoney.of(30, "EUR"), cart.getCartTotal());
    }

    @Test
    public void testChangeQuantity() {
        cart.changeItemQuantity(item3.getProduct(), 1L);
        assertEquals(FastMoney.of(8, "EUR"), cart.getCartTotal());
    }

    @Test
    public void getCartTotalAndAddQuantity() {
        CartItem addItem = new CartItem(item3.getProduct(), 1L);
        cart.addItem(addItem);
        assertEquals(cart.getItemList().get(2).getQuantity(), new Long(4));
    }

    @Test
    public void getCartTotalAndRemoveItem() {
        CartItem removeItem = new CartItem(item3.getProduct(), 3L);
        cart.subtractItem(removeItem);

        assertEquals(cart.getItemList().contains(item3), Boolean.FALSE);
    }

    @Test
    public void getCartTotalAndSubtractQuantity() {
        cart.subtractItem(new CartItem(new Product("Prod2", FastMoney.of(2, "EUR")), 1L));

        assertEquals(FastMoney.of(12, "EUR"), cart.getCartTotal());
    }
}