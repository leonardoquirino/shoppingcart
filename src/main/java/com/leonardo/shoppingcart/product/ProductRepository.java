package com.leonardo.shoppingcart.product;

import java.util.List;

import org.javamoney.moneta.FastMoney;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>{

    List<Product> findProductsByPrice(FastMoney price);
    List<Product> findProductsByPriceBetween(FastMoney priceGT, FastMoney priceLT, Sort sort);
    List<Product> findAllByOrderByScoreDesc(TextCriteria criteria);
}
