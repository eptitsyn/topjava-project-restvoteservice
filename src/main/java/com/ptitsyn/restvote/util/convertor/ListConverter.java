package com.ptitsyn.restvote.util.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptitsyn.restvote.model.MenuItem;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Converter
public class ListConverter implements AttributeConverter<List<MenuItem>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<MenuItem> menuItems) {

        String customerInfoJson = null;
        try {

            customerInfoJson = objectMapper.writeValueAsString(menuItems);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public List<MenuItem> convertToEntityAttribute(String dishesJSON) {

        List<MenuItem> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(dishesJSON,
                    new TypeReference<List<MenuItem>>() {
                    });
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return customerInfo;
    }
}
