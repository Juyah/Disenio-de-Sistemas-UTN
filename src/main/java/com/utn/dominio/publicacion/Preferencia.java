package com.utn.dominio.publicacion;

import com.utn.dominio.EntidadPersistente;
import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Sexo;
import com.utn.dominio.animal.Tamaño;

import javax.persistence.*;

@Entity
@Table(name = "preferencia")
public class Preferencia extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 8)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private Tamaño tamaño;

    public Preferencia(Sexo sexo, Animal animal, Tamaño tamaño) {
        this.animal = animal;
        this.sexo = sexo;
        this.tamaño = tamaño;
    }

    // Accessors
    public Animal getAnimal() {
        return animal;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Tamaño getTamaño() {
        return tamaño;
    }

    // Hibernate
    public Preferencia() {

    }
}