package com.utn.infraestructura.api.persona;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.utn.dominio.persona.Contacto;
import com.utn.dominio.persona.Direccion;
import com.utn.dominio.persona.Documento;
import com.utn.dominio.persona.TipoDocumento;
import com.utn.dominio.publicacion.Preferencia;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class SolicitudRegistrarPersona
{
    @JsonProperty
    private String nombreUsuario;

    @JsonProperty
    private DatosContacto contactoPersonal;

    @JsonProperty
    private LocalDate fechaNacimiento;

    @JsonProperty
    private Documento documento;

    @JsonProperty
    private Double latitud;

    @JsonProperty
    private Double longitud;

    @JsonProperty
    private DatosPreferencia preferencia;

    @JsonProperty
    private List<DatosContacto> unosContactos;

    @JsonProperty
    private int radioHogares;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public DatosContacto getContactoPersonal() {
        return contactoPersonal;
    }

    public void setContactoPersonal(DatosContacto contactoPersonal) {
        this.contactoPersonal = contactoPersonal;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<DatosContacto> getUnosContactos() {
        return unosContactos;
    }

    public void setUnosContactos(List<DatosContacto> unosContactos) {
        this.unosContactos = unosContactos;
    }

    public int getRadioHogares() {
        return radioHogares;
    }

    public void setRadioHogares(int radioHogares) {
        this.radioHogares = radioHogares;
    }

    public DatosPreferencia getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(DatosPreferencia preferencia) {
        this.preferencia = preferencia;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
