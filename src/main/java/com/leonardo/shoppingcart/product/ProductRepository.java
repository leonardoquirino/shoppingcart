package com.leonardo.shoppingcart.product;

import org.javamoney.moneta.FastMoney;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String>{

    List<Product> findProductsByPrice(FastMoney price);
    List<Product> findProductsByPriceBetween(FastMoney priceGT, FastMoney priceLT, Sort sort);
}
