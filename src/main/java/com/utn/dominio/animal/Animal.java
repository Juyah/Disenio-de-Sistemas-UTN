package com.utn.dominio.animal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Animal {

    GATO("GATO"),
    PERRO("PERRO");

    Animal(String descripcion){
        this.descripcion = descripcion;
    }

    private final String descripcion;

    public static Animal buscar(String descripcion) {
        return Arrays.stream(Animal.values())
            .filter(animal -> animal.descripcion.equals(descripcion))
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }

    public static List<String> getDescripciones() {
        return Arrays.stream(Animal.values())
            .map(animal -> animal.descripcion)
            .collect(Collectors.toList());
    }
}
