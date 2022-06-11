package com.utn.infraestructura.api.voluntario;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.persona.Direccion;

import java.util.List;

public class RespuestaPublicacionMascotaEncontradaID {

    @JsonProperty
    private int id;

    @JsonProperty
    private Double latitud;

    @JsonProperty
    private Double longitud;

    @JsonProperty
    private String estadoMascota;

    @JsonProperty
    private List<String> fotosMascota;

    public RespuestaPublicacionMascotaEncontradaID(int id, Double latitud, Double longitud, String estadoMascota, List<String> fotosMascota) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estadoMascota = estadoMascota;
        this.fotosMascota = fotosMascota;
    }

    public int getId() {
        return id;
    }

}
