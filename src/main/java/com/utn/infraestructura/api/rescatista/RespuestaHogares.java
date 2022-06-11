package com.utn.infraestructura.api.rescatista;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RespuestaHogares {

    @JsonProperty
    private String nombre;

    @JsonProperty
    private String direccion;

    @JsonProperty
    private double latitud;

    @JsonProperty
    private double longitud;

    @JsonProperty
    private String telefono;

    @JsonProperty
    private List<String> caracteristicas;

    public RespuestaHogares(String nombre, String direccion, double latitud, double longitud, String telefono, List<String> caracteristicas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;
        this.caracteristicas = caracteristicas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
