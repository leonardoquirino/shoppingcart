package com.leonardo.shoppingcart.config;

import com.leonardo.shoppingcart.converters.FastMoneyToLongConverter;
import com.leonardo.shoppingcart.converters.LongToFastMoneyConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.CustomConversions;

import java.util.Arrays;

public class CustomConvertersConfig {
    @Bean
    public CustomConversions customConversions() {
        return new CustomConversions(
                CustomConversions.StoreConversions.NONE,
                Arrays.asList(
                        new FastMoneyToLongConverter(),
                        new LongToFastMoneyConverter()));
    }
}
