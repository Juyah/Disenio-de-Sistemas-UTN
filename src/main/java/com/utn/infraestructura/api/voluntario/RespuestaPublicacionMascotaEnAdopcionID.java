package com.utn.infraestructura.api.voluntario;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RespuestaPublicacionMascotaEnAdopcionID {

    @JsonProperty
    private String nombreMascota;

    @JsonProperty
    private String descripcion;

    @JsonProperty
    private List<String> fotos;

    @JsonProperty
    private int id;

    public RespuestaPublicacionMascotaEnAdopcionID(String nombreMascota, String descripcion, List<String> fotos, int idPublicacion) {
        this.nombreMascota = nombreMascota;
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.id = idPublicacion;
    }

    public int getId() {
        return id;
    }
}
