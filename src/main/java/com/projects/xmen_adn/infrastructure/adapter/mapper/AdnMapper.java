package com.projects.xmen_adn.infrastructure.adapter.mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.projects.xmen_adn.infrastructure.config.util.Mapper;

@Mapper
public class AdnMapper implements DynamoDBTypeConverter<String, String[]> {

    @Override
    public String convert(String[] object) {
        return Arrays.stream(object).collect(Collectors.joining(","));
    }

    @Override
    public String[] unconvert(String object) {
        return object.split(",");
    }
}
