package com.utn.infraestructura.api.publicacion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.persona.TipoDocumento;

import java.util.HashMap;
import java.util.Map;

public class SolicitudPublicarMascotaEnAdopcion {

    @JsonProperty
    private TipoDocumento tipoDocumento;

    @JsonProperty
    private int numeroDocumento;

    @JsonProperty
    private String nombreMascota;

    @JsonProperty
    private HashMap<String, String> preguntasRespuestas;

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public HashMap<String, String> getPreguntasRespuestas() {
        return preguntasRespuestas;
    }
}
