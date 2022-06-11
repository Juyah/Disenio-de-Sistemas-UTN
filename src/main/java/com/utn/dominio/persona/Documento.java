package com.utn.dominio.persona;

import com.utn.dominio.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "documento")
public class Documento extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private TipoDocumento tipo;

    @Column
    private Integer numero;

    public Documento(TipoDocumento tipo, Integer numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    // Accessors
    public TipoDocumento getTipo() {
        return tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    // Hibernate
    public Documento() {

    }
}