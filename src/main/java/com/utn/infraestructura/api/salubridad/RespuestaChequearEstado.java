package com.utn.infraestructura.api.salubridad;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RespuestaChequearEstado {

    @JsonProperty
    private final String nombreAplicacion = "rescate-de-patitas";

    @JsonProperty
    private final LocalDateTime fecha = LocalDateTime.now();

    @JsonProperty
    private final String estado = "UP!";

}