package com.utn.infraestructura.api.rescatista;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.persona.TipoDocumento;

import java.util.List;

public class SolicitudMascotaEncontradaPublicacion {

    @JsonProperty
    private String nombreOrganizacion;

    @JsonProperty
    private Integer numeroDocumentoRescatista;

    @JsonProperty
    private TipoDocumento tipoDocumentoRescatista;

    @JsonProperty
    private String estado;

    @JsonProperty
    private Double latitud;

    @JsonProperty
    private Double longitud;

    @JsonProperty
    private List<String> fotos;

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public Integer getNumeroDocumentoRescatista() {
        return numeroDocumentoRescatista;
    }

    public TipoDocumento getTipoDocumentoRescatista() {
        return tipoDocumentoRescatista;
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
