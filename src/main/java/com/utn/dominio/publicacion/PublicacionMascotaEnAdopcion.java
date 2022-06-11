package com.utn.dominio.publicacion;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;

import javax.persistence.*;

@Entity
@Table(name = "publicacionMascotaEnAdopcion")
public class PublicacionMascotaEnAdopcion extends Publicacion {

    @ElementCollection
    @Column(nullable = true, length = 64)
    private Map<String, String> preguntasRespuestas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mascota_id")
    private Mascota mascota;

    public PublicacionMascotaEnAdopcion(Persona personaDueño, Mascota mascota, Map<String, String> preguntasRespuestas) {
        super(personaDueño);
        this.mascota = mascota;
        this.preguntasRespuestas = preguntasRespuestas;
    }

    public Preferencia obtenerFisionomia() {
        return new Preferencia(mascota.getSexo(), mascota.getAnimal(), mascota.getTamaño());
    }

    // Accessors
    public Mascota getMascota() {
        return mascota;
    }

    // Hibernate
    public PublicacionMascotaEnAdopcion() {

    }
}