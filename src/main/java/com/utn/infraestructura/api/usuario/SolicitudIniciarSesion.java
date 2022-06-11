package com.utn.infraestructura.api.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitudIniciarSesion {

    @JsonProperty
    private String nombreUsuario;

    @JsonProperty
    private String contrasenia;

    public String nombreUsuario() {
        return this.nombreUsuario;
    }

    public String contrasenia() {
        return this.contrasenia;
    }
}