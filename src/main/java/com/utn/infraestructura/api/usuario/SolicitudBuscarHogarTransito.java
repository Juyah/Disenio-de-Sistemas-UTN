package com.utn.infraestructura.api.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Tamaño;

import java.util.List;

public class SolicitudBuscarHogarTransito {

    @JsonProperty
    private int idMascota;

    @JsonProperty
    private String nombreUsuario;

    @JsonProperty
    private Animal tipoAnimal;

    @JsonProperty
    private Tamaño tamaño;

    @JsonProperty
    private List<String> caracteristicas;

    @JsonProperty
    private int radioDeCercania;

    public void setTipoAnimal(Animal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setTamaño(Tamaño tamaño){this.tamaño = tamaño;}

    public void setCaracteristicas(List<String> caracteristicas){this.caracteristicas = caracteristicas;}

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Animal getTipoAnimal() {
        return tipoAnimal;
    }

    public Tamaño getTamaño() {return tamaño;}

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public int getIdMascota(){return idMascota;}
}
