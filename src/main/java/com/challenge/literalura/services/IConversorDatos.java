package com.challenge.literalura.services;

public interface IConversorDatos {
    <T> T obtenerDatos(String json, Class<T> clase);

}
