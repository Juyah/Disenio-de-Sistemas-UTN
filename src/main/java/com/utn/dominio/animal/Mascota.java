package com.utn.dominio.animal;

import com.utn.dominio.EntidadPersistente;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.dominio.persona.Persona;

import javax.persistence.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@Entity
@Table(name = "mascota")
public class Mascota extends EntidadPersistente {

    @Column(nullable = false, length = 32)
    private String nombre;

    @Column(nullable = true, length = 32)
    private String apodo;

    @Column(nullable = false)
    private int edad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 8)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private Tamaño tamaño;

    @Column(nullable = false, length = 512)
    private String descripcionFisica;

    @ManyToOne(cascade = CascadeType.ALL)
    private Organizacion organizacion;

    @ElementCollection()
    @Column(columnDefinition = "MEDIUMTEXT")
    private List<String> fotos;

    @ElementCollection
    private Map<String, String> caracteristicas;

    @ManyToOne(cascade = CascadeType.ALL)
    private Persona duenio;

    public Mascota(String nombre, String apodo, int edad, Animal animal, Sexo sexo, Tamaño tamaño, String descripcionFisica) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.edad = edad;
        this.animal = animal;
        this.sexo = sexo;
        this.tamaño = tamaño;
        this.descripcionFisica = descripcionFisica;
        this.fotos = new ArrayList<>();
        this.caracteristicas = new HashMap<>();
    }

    public void añadirFoto(String rutaFoto)
    {
        this.fotos.add(rutaFoto);
    }

    public void añadirCaracteristica(String unaCaracteristica, String unaRespuesta)
    {
        this.caracteristicas.put(unaCaracteristica,unaRespuesta);
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    // Accessors
    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Tamaño getTamaño() {
        return tamaño;
    }

    public String getDescripcionFisica() {
        return descripcionFisica;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setDuenio(Persona duenio) {
        this.duenio = duenio;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setTamaño(Tamaño tamaño) {
        this.tamaño = tamaño;
    }

    // Hibernate
    public Mascota() {

    }
}