package com.leonardo.shoppingcart.converters;

import org.javamoney.moneta.FastMoney;
import org.springframework.core.convert.converter.Converter;

public class FastMoneyToLongConverter implements Converter<FastMoney, Long> {

    @Override
    public Long convert(FastMoney source) {
        return source.getNumber().longValueExact() * 100;
    }
}
