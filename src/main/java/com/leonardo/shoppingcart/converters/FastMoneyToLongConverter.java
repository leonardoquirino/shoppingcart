package com.leonardo.shoppingcart.converters;

import org.bson.Document;
import org.javamoney.moneta.FastMoney;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
public class FastMoneyToLongConverter implements Converter<FastMoney, Document> {

    @Override
    public Document convert(FastMoney source) {
        Document doc = new Document();
        doc.put("price", source.getNumber().longValueExact() * 100);
        return doc;
    }
}
