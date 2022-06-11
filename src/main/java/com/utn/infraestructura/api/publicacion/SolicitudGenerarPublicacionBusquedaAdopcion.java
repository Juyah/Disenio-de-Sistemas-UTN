package com.utn.infraestructura.api.publicacion;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.persona.TipoDocumento;

public class SolicitudGenerarPublicacionBusquedaAdopcion {

    @JsonProperty
    private String nombreOrganizacion;

    @JsonProperty
    private List<String> comodidades;

    @JsonProperty
    private Integer numeroDocumentoAdoptante;

    @JsonProperty
    private TipoDocumento tipoDocumentoAdoptante;

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public List<String> getComodidades() {
        return comodidades;
    }

    public Integer getNumeroDocumentoAdoptante() {
        return numeroDocumentoAdoptante;
    }

    public TipoDocumento getTipoDocumentoAdoptante() {
        return tipoDocumentoAdoptante;
    }

}
