package com.utn.infraestructura.api.dueño;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Sexo;
import com.utn.dominio.animal.Tamaño;
import com.utn.dominio.persona.TipoDocumento;

import java.util.HashMap;
import java.util.List;

public class SolicitudRegistrarMascota {

    @JsonProperty
    private String organizacion;

    @JsonProperty
    private int numeroDocumento;

    @JsonProperty
    private TipoDocumento tipoDocumento;

    @JsonProperty
    private String nombre;

    @JsonProperty
    private Animal tipoAnimal;

    @JsonProperty
    private String apodo;

    @JsonProperty
    private int edad;

    @JsonProperty
    private Sexo sexo;

    @JsonProperty
    private Tamaño tamanio;

    @JsonProperty
    private String descripcionFisica;

    @JsonProperty
    private List<String> fotos;

    @JsonProperty
    private HashMap<String, String> caracteristicas;

    public String getOrganizacion() {
        return organizacion;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public Animal getTipoAnimal() {
        return tipoAnimal;
    }

    public String getApodo() {
        return apodo;
    }

    public int getEdad() {
        return edad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Tamaño getTamanio() {
        return tamanio;
    }

    public String getDescripcionFisica() {
        return descripcionFisica;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public HashMap<String, String> getCaracteristicas() {
        return caracteristicas;
    }
}