package com.leonardo.shoppingcart.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.javamoney.moneta.FastMoney;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class FastMoneyToLongConverter implements Converter<FastMoney, DBObject> {

    @Override
    public DBObject convert(FastMoney source) {
        DBObject doc = new BasicDBObject();
        doc.put("price", source.getNumber().longValueExact() * 100);
        return doc;
    }
}
