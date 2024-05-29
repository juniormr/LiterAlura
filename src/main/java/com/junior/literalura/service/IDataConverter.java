package com.junior.literalura.service;

public interface IDataConverter {
    <T> T getInfo(String json, Class<T> clase);
}
