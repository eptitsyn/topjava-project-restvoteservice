package com.ptitsyn.restvote.util.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptitsyn.restvote.model.Dish;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Converter
public class ListConverter implements AttributeConverter<List<Dish>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Dish> dishes) {

        String customerInfoJson = null;
        try {

            customerInfoJson = objectMapper.writeValueAsString(dishes);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public List<Dish> convertToEntityAttribute(String dishesJSON) {

        List<Dish> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(dishesJSON,
                    new TypeReference<List<Dish>>() {
                    });
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return customerInfo;
    }
}
