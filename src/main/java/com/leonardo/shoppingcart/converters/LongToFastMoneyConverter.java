package com.leonardo.shoppingcart.converters;

import org.javamoney.moneta.FastMoney;
import org.springframework.core.convert.converter.Converter;

public class LongToFastMoneyConverter implements Converter<Long, FastMoney> {

    @Override
    public FastMoney convert(Long source) {

        return FastMoney.of(source, "EUR").divide(100);
    }
}
