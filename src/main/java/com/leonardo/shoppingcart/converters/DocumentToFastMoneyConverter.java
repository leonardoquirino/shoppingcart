package com.leonardo.shoppingcart.converters;

import org.bson.Document;
import org.javamoney.moneta.FastMoney;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@ReadingConverter
@Component
public class DocumentToFastMoneyConverter implements Converter<Document, FastMoney> {

    @Override
    public FastMoney convert(Document source) {

        return FastMoney.of( (Long) source.get("price"), "EUR").divide(100);
    }
}
