package com.utn.dominio.publicacion;

import java.util.List;
import java.util.ArrayList;

import com.utn.dominio.persona.Persona;

import javax.persistence.*;

@Entity
@Table(name = "publicacionBusquedaAdopcion")
public class PublicacionBusquedaAdopcion extends Publicacion {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preferencia_id")
    private Preferencia preferencia;

    @ElementCollection
    @Column(nullable = true, length = 64)
    private List<String> comodidades;

    public PublicacionBusquedaAdopcion(Persona personaAdoptante, List<String> comodidades) {
        super(personaAdoptante);
        this.preferencia = personaAdoptante.getPreferencia();
        this.comodidades = new ArrayList<>();
        this.comodidades.addAll(comodidades);
    }

    // Hibernate
    public PublicacionBusquedaAdopcion() {

    }
}