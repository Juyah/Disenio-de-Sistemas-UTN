package com.utn.dominio.animal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Sexo {

    MACHO("MACHO"),
    HEMBRA("HEMBRA");

    Sexo(String descripcion){
        this.descripcion = descripcion;
    }

    private final String descripcion;

    public static Sexo buscar(String descripcion) {
        return Arrays.stream(Sexo.values())
                .filter(sexo -> sexo.descripcion.equals(descripcion))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static List<String> getDescripciones() {
        return Arrays.stream(Sexo.values())
                .map(sexo -> sexo.descripcion)
                .collect(Collectors.toList());
    }

}