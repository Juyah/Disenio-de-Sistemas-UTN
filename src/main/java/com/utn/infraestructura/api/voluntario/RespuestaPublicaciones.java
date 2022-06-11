package com.utn.infraestructura.api.voluntario;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RespuestaPublicaciones {

    @JsonProperty
    List<RespuestaPublicacionMascotaEncontradaID> publicacionesMascotaEncontrada;

    @JsonProperty
    List<RespuestaPublicacionMascotaEnAdopcionID> publicacionesMascotaEnAdopcion;

    public RespuestaPublicaciones(List<RespuestaPublicacionMascotaEncontradaID> publicacionesMascotaEncontrada, List<RespuestaPublicacionMascotaEnAdopcionID> publicacionesMascotaEnAdopcion) {
        this.publicacionesMascotaEncontrada = publicacionesMascotaEncontrada;
        this.publicacionesMascotaEnAdopcion = publicacionesMascotaEnAdopcion;
    }
}
