package com.utn.infraestructura.api.administrador;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.organizacion.CalidadFoto;
import com.utn.dominio.organizacion.TamañoFoto;

import java.util.List;

public class RespuestaAccesoAdmin
{
    @JsonProperty
    private List<String> usuariosAdministradores;

    @JsonProperty
    private CalidadFoto calidadFoto;

    @JsonProperty
    private TamañoFoto tamanioFoto;

    @JsonProperty
    private List<String> caracteristicas;

    public void setUsuariosAdministradores(List<String> usuariosAdministradores) {
        this.usuariosAdministradores = usuariosAdministradores;
    }

    public void setCalidadFoto(CalidadFoto calidadFoto) {
        this.calidadFoto = calidadFoto;
    }

    public void setTamanioFoto(TamañoFoto tamanioFoto) {
        this.tamanioFoto = tamanioFoto;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
