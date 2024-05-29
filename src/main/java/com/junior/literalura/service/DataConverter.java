package com.junior.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getInfo(String json, Class<T> clase) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json).get("results").get(0);
            return objectMapper.treeToValue(jsonNode, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean bookExist(String json) {
        try {
            return !objectMapper.readTree(json).get("results").toString().equals("[]");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
