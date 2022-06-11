package com.utn.infraestructura.api.persona;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatosPreferencia {
    @JsonProperty
    private String tipoAnimal;

    @JsonProperty
    private String sexoAnimal;

    @JsonProperty
    private String tamanioAnimal;

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getSexoAnimal() {
        return sexoAnimal;
    }

    public void setSexoAnimal(String sexoAnimal) {
        this.sexoAnimal = sexoAnimal;
    }

    public String getTamanioAnimal() {
        return tamanioAnimal;
    }

    public void setTamanioAnimal(String tamanioAnimal) {
        this.tamanioAnimal = tamanioAnimal;
    }
}
