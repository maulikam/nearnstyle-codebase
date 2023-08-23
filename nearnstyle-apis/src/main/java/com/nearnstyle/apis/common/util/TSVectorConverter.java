package com.nearnstyle.apis.common.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TSVectorConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}

