package com.utn.dominio.organizacion;

import com.utn.dominio.EntidadPersistente;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Direccion;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.publicacion.PublicacionBusquedaAdopcion;
import com.utn.dominio.publicacion.PublicacionMascotaEnAdopcion;
import com.utn.dominio.publicacion.PublicacionMascotaEncontrada;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "organizacion")
public class Organizacion extends EntidadPersistente {

    @Column(nullable = false, length = 64)
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    private Direccion direccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private TamañoFoto tamañoFoto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private CalidadFoto calidadFoto;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private final List<Voluntario> voluntarios = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private final List<Administrador> administradores = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizacion_id")
    private List<Mascota> mascotas = new ArrayList<>();

    @ManyToMany
    private final Set<Persona> adoptantes = new HashSet<>();

    @ElementCollection
    @Column(length = 64)
    private List<String> caracteristicas;

    @ElementCollection
    @Column(length = 64)
    private List<String> preguntasDarEnAdopcion;

    @ElementCollection
    @Column(length = 64)
    private List<String> preguntasQuieroAdoptar;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizacion_id")
    private List<PublicacionMascotaEncontrada> publicacionesMascotaEncontrada;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizacion_id")
    private List<PublicacionMascotaEnAdopcion> publicacionesMascotaEnAdopcion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizacion_id")
    private List<PublicacionBusquedaAdopcion> publicacionesBusquedaAdopcion;


    public Organizacion(String nombre, Direccion direccion, TamañoFoto tamañoFoto, CalidadFoto calidadFoto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tamañoFoto = tamañoFoto;
        this.calidadFoto = calidadFoto;
        this.caracteristicas = new ArrayList<>();
        this.preguntasDarEnAdopcion = new ArrayList<>();
        this.preguntasQuieroAdoptar = new ArrayList<>();
        this.publicacionesMascotaEncontrada = new ArrayList<>();
        this.publicacionesMascotaEnAdopcion = new ArrayList<>();
        this.publicacionesBusquedaAdopcion = new ArrayList<>();
        this.mascotas = new ArrayList<>();
    }

    public void añadirCaracteristica(String unaCaracteristica) {
        this.caracteristicas.add(unaCaracteristica);
    }

    public void añadirPreguntaAdopcion(String unaPregunta) {
        this.preguntasDarEnAdopcion.add(unaPregunta);
    }

    public void añadirPreguntaQuieroAdoptar(String unaPregunta) {
        this.preguntasQuieroAdoptar.add(unaPregunta);
    }

    public void añadirAdministrador(Administrador unAdministrador) {
        this.administradores.add(unAdministrador);
    }

    public void añadirVoluntario(Voluntario unVoluntario) {
        this.voluntarios.add(unVoluntario);
    }

    public void añadirPersona(Persona unaPersona) {
        this.adoptantes.add(unaPersona);
    }

    public void eliminarPreguntaAdopcion(String unaPregunta) {
        this.preguntasDarEnAdopcion.remove(unaPregunta);
    }

    public void añadirPublicacionBusquedaAdopcion(PublicacionBusquedaAdopcion publicacion) {
        this.publicacionesBusquedaAdopcion.add(publicacion);
    }

    public void añadirPublicacionMascotaEnAdopcion(PublicacionMascotaEnAdopcion publicacion) {
        this.publicacionesMascotaEnAdopcion.add(publicacion);
    }

    public void añadirPublicacionMascotaEncontrada(PublicacionMascotaEncontrada publicacion) {
        this.publicacionesMascotaEncontrada.add(publicacion);
    }

    public List<Persona> adoptantesActivos() {
        return adoptantes.stream()
                .filter(Persona::isAdoptante)
                .collect(Collectors.toList());
    }

    public void añadirMascota(Mascota unaMascota) {
        this.mascotas.add(unaMascota);
        unaMascota.setOrganizacion(this);
    }

    // Accessors
    public TamañoFoto getTamañoFoto() {
        return tamañoFoto;
    }

    public CalidadFoto getCalidadFoto() {
        return calidadFoto;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public List<PublicacionMascotaEncontrada> getPublicacionesMascotaEncontrada() {
        return publicacionesMascotaEncontrada;
    }

    public List<PublicacionMascotaEnAdopcion> getPublicacionesMascotaEnAdopcion() {
        return publicacionesMascotaEnAdopcion;
    }

    public List<PublicacionBusquedaAdopcion> getPublicacionesBusquedaAdopcion() {
        return publicacionesBusquedaAdopcion;
    }

    public List<String> getPreguntasDarEnAdopcion() {
        return preguntasDarEnAdopcion;
    }

    public void setTamañoFoto(TamañoFoto tamañoFoto) {
        this.tamañoFoto = tamañoFoto;
    }

    public void setCalidadFoto(CalidadFoto calidadFoto) {
        this.calidadFoto = calidadFoto;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    // Hibernate
    public Organizacion() {

    }

}