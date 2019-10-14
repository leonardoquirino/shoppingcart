package com.leonardo.shoppingcart.config;

import com.leonardo.shoppingcart.converters.DocumentToFastMoneyConverter;
import com.leonardo.shoppingcart.converters.FastMoneyToLongConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomConvertersConfig {
    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(
                Arrays.asList(
                        new FastMoneyToLongConverter(),
                        new DocumentToFastMoneyConverter()));
    }
}
