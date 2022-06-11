package com.utn.infraestructura.api.administrador;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SolicitudActualizarCaracteristicas
{
    @JsonProperty
    private String nuevaCaracteristica;

    public String getNuevaCaracteristica() {
        return nuevaCaracteristica;
    }
}
