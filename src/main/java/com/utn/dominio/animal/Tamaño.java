package com.utn.dominio.animal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Tamaño {

    CHICO("CHICO"),
    MEDIANO("MEDIANO"),
    GRANDE("GRANDE");

    private final String descripcion;

    Tamaño(String descripcion){
        this.descripcion = descripcion;
    }

    public static Tamaño buscar(String descripcion) {
        return Arrays.stream(Tamaño.values())
                .filter(tamaño -> tamaño.descripcion.equals(descripcion))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static List<String> getDescripciones() {
        return Arrays.stream(Tamaño.values())
                .map(tamaño -> tamaño.descripcion)
                .collect(Collectors.toList());
    }
}