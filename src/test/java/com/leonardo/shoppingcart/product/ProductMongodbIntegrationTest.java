package com.leonardo.shoppingcart.product;

import com.leonardo.shoppingcart.DemoApplication;
import com.leonardo.shoppingcart.config.CustomConvertersConfig;
import org.javamoney.moneta.FastMoney;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = DemoApplication.class)
@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
@Import(CustomConvertersConfig.class)
public class ProductMongodbIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertProductTest() {
        Product product = new Product("Product Insert", FastMoney.of(4L, "EUR"));
        Product saved = productRepository.insert(product);
        assertNotNull(saved.getId());
        assertNotNull(saved.getName());
        assertNotNull(saved.getPrice());
        assertEquals(saved.getName(), product.getName());
        assertEquals(saved.getPrice(), product.getPrice());
    }

    @Test
    public void deleteProductTest() {
        Product product = new Product("Product Insert", FastMoney.of(4L, "EUR"));
        Product saved = productRepository.insert(product);
        assertNotNull(saved.getId());
        String id = product.getId();
        productRepository.deleteById(id);
        assertEquals(productRepository.existsById(id), Boolean.FALSE);
    }

    @Test
    public void findByIdProductTest() {
        Product product = new Product("Product Insert", FastMoney.of(4L, "EUR"));
        Product saved = productRepository.insert(product);
        assertNotNull(saved.getId());
        String id = product.getId();
        product = productRepository.findById(id).get();
        assertEquals(product, saved);
    }

    @Test
    public void updateProductTest() {
        Product product = new Product("Product Insert", FastMoney.of(4L, "EUR"));
        Product saved = productRepository.insert(product);
        Assert.assertNotNull(saved.getId());
        FastMoney updatePrice = FastMoney.of(5L, "EUR");
        saved.setPrice(FastMoney.of(5L, "EUR"));
        saved = productRepository.save(saved);
        Assert.assertTrue(saved.getPrice().equals(updatePrice));
    }
}
