package com.utn.dominio.notificacion;

import com.utn.dominio.EntidadPersistente;
import com.utn.dominio.notificacion.mensaje.Mensaje;

import javax.persistence.*;

@Entity
@Table(name = "medioDeComunicacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 16)
public abstract class MedioDeComunicacion extends EntidadPersistente {

    @Column(nullable = false)
    private boolean esPreferido;

    public MedioDeComunicacion(boolean esPreferido) {
        this.esPreferido = esPreferido;
    }

    public abstract void enviar(Mensaje mensaje);

    // Accessors
    public boolean esPreferido() {
        return this.esPreferido;
    }

    // Hibernate
    public MedioDeComunicacion() { }
}