package com.utn.dominio.persona;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.utn.dominio.EntidadPersistente;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.publicacion.Preferencia;
import com.utn.dominio.notificacion.mensaje.Mensaje;
import com.utn.dominio.excepcion.MascotaNoEncontradaException;

import javax.persistence.*;

@Entity
@Table(name = "persona")
public class Persona extends EntidadPersistente {

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contactoPersonal_id")
    private Contacto contactoPersonal;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    private Documento documento;

    @OneToOne(cascade = CascadeType.ALL)
    private Direccion direccion;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Contacto> contactos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private List<Mascota> mascotas = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Preferencia preferencia;

    @Column
    private boolean esAdoptante;

    @Column
    private int radioHogares;

    public Persona(Usuario usuario, Contacto contactoPersonal, LocalDate fechaNacimiento, Documento documento,
                   Direccion direccion, Preferencia preferencia, List<Contacto> otrosContactos, Integer radioHogares) {
        this.usuario = usuario;
        this.contactoPersonal = contactoPersonal;
        this.fechaNacimiento = fechaNacimiento;
        this.documento = documento;
        this.direccion = direccion;
        this.preferencia = preferencia;
        this.contactos = otrosContactos;
        this.radioHogares = radioHogares;
        this.mascotas = new ArrayList<>();
        this.esAdoptante = false;
    }

    public void notificar(Mensaje mensaje) {
        this.contactoPersonal.notificar(mensaje);
        this.contactos.forEach(unContacto -> unContacto.notificar(mensaje));
    }

    public Mascota buscarMascota(String nombreMascota) {
        return mascotas.stream()
                .filter(unaMascota -> unaMascota.getNombre().equals(nombreMascota))
                .findFirst().orElseThrow(MascotaNoEncontradaException::new);
    }

    public void añadirMascota(Mascota mascota) {
        mascotas.add(mascota);
        mascota.setDuenio(this);
    }

    public Mascota buscarMascotaPorId(int id){
        return mascotas.stream()
                .filter(unaMascota -> unaMascota.getId() == id)
                .findFirst().orElseThrow(MascotaNoEncontradaException::new);
    }

    // Accessors
    public boolean isAdoptante() {
        return esAdoptante;
    }

    public String getNombre() {
        return this.contactoPersonal.getNombre();
    }

    public String getTelefono() {
        return this.contactoPersonal.getTelefono();
    }

    public String getEmail() {
        return this.contactoPersonal.getEmail();
    }

    public int getNumeroDocumento() {
        return this.documento.getNumero();
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public int getRadioHogares() {
        return this.radioHogares;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Contacto getContactoPersonal() {
        return contactoPersonal;
    }

    public Documento getDocumento() {
        return documento;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public Preferencia getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(Preferencia preferencia) {
        this.preferencia = preferencia;
    }

    public void setEsAdoptante(boolean esAdoptante) {
        this.esAdoptante = esAdoptante;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Hibernate
    public Persona() {}
}