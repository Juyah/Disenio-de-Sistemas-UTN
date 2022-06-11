package com.utn.infraestructura.api.voluntario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitudAceptarPublicacion {

    @JsonProperty
    private Integer id;

    public Integer getId() {
        return id;
    }
}
