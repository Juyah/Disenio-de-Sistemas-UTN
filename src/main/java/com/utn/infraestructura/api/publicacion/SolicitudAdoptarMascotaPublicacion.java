package com.utn.infraestructura.api.publicacion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.persona.TipoDocumento;

public class SolicitudAdoptarMascotaPublicacion {

    @JsonProperty
    private int numeroDocDuenio;

    @JsonProperty
    private TipoDocumento tipoDocumentoDuenio;

    @JsonProperty
    private String nombreMascota;

    @JsonProperty
    private int numeroDocAdoptante;

    @JsonProperty
    private TipoDocumento tipoDocumentoAdoptante;

    public int getNumeroDocDuenio() {
        return numeroDocDuenio;
    }

    public TipoDocumento getTipoDocumentoDuenio() {
        return tipoDocumentoDuenio;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public int getNumeroDocAdoptante() {
        return numeroDocAdoptante;
    }

    public TipoDocumento getTipoDocumentoAdoptante() {
        return tipoDocumentoAdoptante;
    }

}
