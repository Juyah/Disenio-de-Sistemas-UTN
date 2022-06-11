package com.utn.infraestructura.api.rescatista;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.persona.TipoDocumento;

import java.util.List;

public class SolicitudMascotaEncontradaChapita {
    @JsonProperty
    private int numeroDocumentoRescatista;

    @JsonProperty
    private TipoDocumento tipoDocumentoRescatista;

    @JsonProperty
    private int idMascota;

    @JsonProperty
    private String estado;

    @JsonProperty
    private Double latitud;

    @JsonProperty
    private Double longitud;

    @JsonProperty
    private List<String> fotos;

    public int getNumeroDocumentoRescatista() {
        return numeroDocumentoRescatista;
    }

    public TipoDocumento getTipoDocumentoRescatista() {
        return tipoDocumentoRescatista;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public String getEstado() {
        return estado;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public List<String> getFotos() {
        return fotos;
    }

}
