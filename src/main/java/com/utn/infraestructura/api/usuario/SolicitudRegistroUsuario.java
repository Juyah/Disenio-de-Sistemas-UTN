package com.utn.infraestructura.api.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitudRegistroUsuario
{
    @JsonProperty
    private String nombreUsuario;

    @JsonProperty
    private String contrasenia;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
