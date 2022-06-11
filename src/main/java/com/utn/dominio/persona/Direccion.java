package com.utn.dominio.persona;

import com.utn.dominio.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion extends EntidadPersistente {

    @Column
    private double latitud;
    @Column
    private double longitud;

    public Direccion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }


    // Accessors
    public double getLatitud() {
        return this.latitud;
    }

    public double getLongitud() {
        return this.longitud;
    }

    // Hibernate
    public Direccion() {

    }
}