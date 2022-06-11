package com.utn.infraestructura.api.persona;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DatosContacto
{
    @JsonProperty
    private String nombre;

    @JsonProperty
    private String apellido;

    @JsonProperty
    private String telefono;

    @JsonProperty
    private String email;

    @JsonProperty
    private List<String> mediosComunicacion;

    public String getNombre()
    {
        return nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public String getEmail()
    {
        return email;
    }

    public List<String> getMediosComunicacion()
    {
        return mediosComunicacion;
    }

    public void setNombre(String unNombre)
    {
        this.nombre = unNombre;
    }

    public void setApellido(String unApellido)
    {
        this.apellido = unApellido;
    }

    public void setTelefono(String unTelefono)
    {
        this.telefono = unTelefono;
    }

    public void setEmail(String unEmail)
    {
        this.email = unEmail;
    }

    public void setMediosComunicacion(List<String> mediosComunicacion)
    {
        this.mediosComunicacion = mediosComunicacion;
    }
}
