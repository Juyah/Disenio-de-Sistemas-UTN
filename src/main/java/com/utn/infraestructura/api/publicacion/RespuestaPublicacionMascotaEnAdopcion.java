package com.utn.infraestructura.api.publicacion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.persona.TipoDocumento;

import java.util.List;

public class RespuestaPublicacionMascotaEnAdopcion {

    @JsonProperty
    private String nombreMascota;

    @JsonProperty
    private String descripcion;

    @JsonProperty
    private List<String> fotos;

    @JsonProperty
    private Integer numeroDocDuenio;

    @JsonProperty
    private TipoDocumento tipoDocDuenio;

    public RespuestaPublicacionMascotaEnAdopcion(String nombreMascota, String descripcion, List<String> fotos, Integer numeroDocDuenio, TipoDocumento tipoDocDuenio) {
        this.nombreMascota = nombreMascota;
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.numeroDocDuenio = numeroDocDuenio;
        this.tipoDocDuenio = tipoDocDuenio;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public void setNumeroDocDuenio(Integer numeroDocDuenio) {
        this.numeroDocDuenio = numeroDocDuenio;
    }

    public void setTipoDocDuenio(TipoDocumento tipoDocDuenio) {
        this.tipoDocDuenio = tipoDocDuenio;
    }
}
