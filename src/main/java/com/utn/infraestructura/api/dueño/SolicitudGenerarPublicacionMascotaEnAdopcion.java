package com.utn.infraestructura.api.dueño;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Sexo;
import com.utn.dominio.animal.Tamaño;

import java.util.List;

public class SolicitudGenerarPublicacionMascotaEnAdopcion {

    @JsonProperty
    private String nombreMascota;

    @JsonProperty
    private Animal tipoAnimal;

    @JsonProperty
    private String apodo;

    @JsonProperty
    private int edad;

    @JsonProperty
    private Sexo sexo;

    @JsonProperty
    private Tamaño tamaño;

    @JsonProperty
    private String descripcionFisica;

    @JsonProperty
    private List<String> fotos;

    @JsonProperty
    private List<String> caracteristicas;

    @JsonProperty
    private List<String> respuestasFormulario;

    @JsonProperty
    private String nombreOrganizacion;


        public String getNombreMascota() {
            return this.nombreMascota;
        }

        public void setNombreMascota(String nombreMascota) {
            this.nombreMascota = nombreMascota;
        }

        public Animal getTipoAnimal() {
            return this.tipoAnimal;
        }
        public void setTipoAnimal(Animal tipoAnimal) {
            this.tipoAnimal = tipoAnimal;
        }

        public String getApodoMascota() {
            return this.apodo;
        }
        public void setApodoMascota(String apodo) {
            this.apodo = apodo;
        }

        public int getEdadMascota() {
            return this.edad;
        }
        public void setEdadMascota(int edad) {
            this.edad = edad;
        }

        public Sexo getSexoMascota() {
            return this.sexo;
        }

        public void setSexoMascota(Sexo sexo) {
            this.sexo = sexo;
        }

        public Tamaño getTamañoMascota() {
            return this.tamaño;
        }

        public void setTamañoMascota(Tamaño tamaño) {
            this.tamaño = tamaño;
        }

        public String getDescripcionFisicaMascota() {
            return this.descripcionFisica;
        }
        public void setDescripcionFisicaMascota(String descripcionFisica) {
            this.descripcionFisica = descripcionFisica;
        }

        public List<String> getFotosMascota() {
            return this.fotos;
        }
        public void setFotosMascota(List<String> fotos) {
            this.fotos = fotos;
        }

        public List<String> getCaracteristicasMascota() {
            return this.caracteristicas;
        }
        public void setCaracteristicasMascota(List<String> caracteristicas) {
            this.caracteristicas = caracteristicas;
        }

        public List<String> getRespuestasFormulario() {
            return this.respuestasFormulario;
        }

        public void setRespuestasFormulario(List<String> respuestasFormulario) {
            this.respuestasFormulario = respuestasFormulario;
        }

        public String getNombreOrganizacion() {
            return this.nombreOrganizacion;
         }

        public void setNombreOrganizacion(String nombreOrganizacion) {
            this.nombreOrganizacion = nombreOrganizacion;
        }
}
