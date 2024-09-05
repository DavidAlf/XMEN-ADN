package com.projects.xmen_adn.infrastructure.adapter.mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

@Component
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
