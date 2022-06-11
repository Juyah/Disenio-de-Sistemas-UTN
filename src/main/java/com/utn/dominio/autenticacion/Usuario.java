package com.utn.dominio.autenticacion;

import com.utn.dominio.EntidadPersistente;
import com.utn.dominio.excepcion.CredencialesInvalidasException;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario extends EntidadPersistente {

    @Column(nullable = false, length = 32)
    private String usuario;

    @Column(nullable = false, length = 128)
    private String contrasenia;

    public Usuario(String usuario, String contrasenia) {
        ValidadorContraseña.ejecutar(contrasenia);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public void iniciarSesion(String usuario, String contraseña) {
        if(!this.usuario.equals(usuario) || !this.contrasenia.equals(contraseña))
            throw new CredencialesInvalidasException();
    }

    // Accessors
    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    // Hibernate
    public Usuario() {}
}