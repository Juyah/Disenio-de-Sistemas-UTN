package com.utn.dominio.persona;

import java.util.List;
import java.util.ArrayList;

import com.utn.dominio.EntidadPersistente;
import com.utn.dominio.notificacion.mensaje.Mensaje;
import com.utn.dominio.notificacion.MedioDeComunicacion;

import javax.persistence.*;

@Entity
@Table(name = "contacto")
public class Contacto extends EntidadPersistente {

    @Column(nullable = false, length = 32)
    private String nombre;

    @Column(nullable = false, length = 32)
    private String apellido;

    @Column(nullable = false, length = 32)
    private String telefono;

    @Column(nullable = false, length = 64)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<MedioDeComunicacion> mediosDeComunicacion = new ArrayList<>();

    public Contacto(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public void notificar(Mensaje mensaje) {
        mensaje.agregarEmailDestino(this.email);
        mensaje.agregarTelefonoDestino(this.telefono);
        mediosDeComunicacion.stream()
                .filter(MedioDeComunicacion::esPreferido)
                .forEach(medioDeComunicacion -> medioDeComunicacion.enviar(mensaje));
    }

    public void añadirMedioDeComunicacion(MedioDeComunicacion unMedioDeComunicacion) {
        mediosDeComunicacion.add(unMedioDeComunicacion);
    }

    // Accessors
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setMediosDeComunicacion(List<MedioDeComunicacion> mediosDeComunicacion) {
        this.mediosDeComunicacion = mediosDeComunicacion;
    }

    // Hibernate
    public Contacto() {

    }
}