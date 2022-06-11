package com.utn.infraestructura.api.persona;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.persona.TipoDocumento;

public class RespuestaDocumento {
    @JsonProperty
    private int numero;

    @JsonProperty
    private TipoDocumento tipo;

    public RespuestaDocumento(int numero, TipoDocumento tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }
}
