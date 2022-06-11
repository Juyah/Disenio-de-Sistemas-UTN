package com.utn.infraestructura.api.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitudCerrarSesion {

    @JsonProperty
    private String nombreUsuario;

    public String nombreUsuario() {
        return this.nombreUsuario;
    }

}