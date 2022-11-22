package ru.javaops.topjava.util.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.javaops.topjava.model.Item;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.LinkedList;

@Slf4j
public class HashMapConverter implements AttributeConverter<LinkedList<Item>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(LinkedList<Item> customerInfo) {

        String customerInfoJson = null;
        try {

            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public LinkedList<Item> convertToEntityAttribute(String customerInfoJSON) {

        LinkedList<Item> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON,
                    new TypeReference<LinkedList<Item>>() {});
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return customerInfo;
    }
}