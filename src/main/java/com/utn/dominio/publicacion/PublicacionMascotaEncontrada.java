package com.utn.dominio.publicacion;

import java.util.List;
import java.util.ArrayList;

import com.utn.dominio.persona.Direccion;
import com.utn.dominio.persona.Persona;
import javax.persistence.*;

@Entity
@Table(name = "publicacionMascotaEncontrada")
public class PublicacionMascotaEncontrada extends Publicacion {

    @OneToOne(cascade = CascadeType.ALL)
    private Direccion ubicacionMascota;

    @Column(nullable = false ,length = 128)
    private String estadoMascota;

    @ElementCollection
    @Column(columnDefinition = "MEDIUMTEXT")
    private List<String> fotosMascota;

    public PublicacionMascotaEncontrada(Persona personaRescatista, Direccion ubicacionMascota, String estadoMascota) {
        super(personaRescatista);
        this.ubicacionMascota = ubicacionMascota;
        this.estadoMascota = estadoMascota;
        this.fotosMascota = new ArrayList<>();
    }

    public void agregarFoto(String foto) {
        this.fotosMascota.add(foto);
    }

    // Accessors
    public Direccion getUbicacionMascota() {
        return ubicacionMascota;
    }

    public String getEstadoMascota() {
        return estadoMascota;
    }

    public List<String> getFotosMascota() {
        return fotosMascota;
    }

    public void setFotosMascota(List<String> fotosMascota) {
        this.fotosMascota = fotosMascota;
    }

    // Hibernate
    public PublicacionMascotaEncontrada() {

    }
}