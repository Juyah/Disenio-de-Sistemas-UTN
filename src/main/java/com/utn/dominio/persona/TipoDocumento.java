package com.utn.dominio.persona;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TipoDocumento {

    DNI("DNI"),
    LICENCIA("LICENCIA"),
    PASAPORTE("PASAPORTE");

    TipoDocumento(String descripcion){
        this.descripcion = descripcion;
    }

    private final String descripcion;

    public static TipoDocumento buscar(String descripcion) {
        return Arrays.stream(TipoDocumento.values())
            .filter(tipoDocumento -> tipoDocumento.descripcion.equals(descripcion))
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }

    public static List<String> getDescripciones() {
        return Arrays.stream(TipoDocumento.values())
                .map(tipoDocumento -> tipoDocumento.descripcion)
                .collect(Collectors.toList());
    }

}
