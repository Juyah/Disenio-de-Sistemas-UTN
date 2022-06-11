package com.utn.casodeuso.administrador;

import com.utn.dominio.Administradores;
import com.utn.dominio.excepcion.CredencialesInvalidasException;
import com.utn.dominio.excepcion.UsuarioNoEncontradoException;
import com.utn.dominio.organizacion.Administrador;

import javax.persistence.NoResultException;

public class IniciarSesionAdmin {
    private final Administradores administradores;

    public IniciarSesionAdmin(Administradores administradores) {
        this.administradores = administradores;
    }

    public Administrador ejecutar(String nombreUsuario, String contraseña) {
        try {
            Administrador unAdministrador = administradores.obtenerPorNombreUsuario(nombreUsuario);
            unAdministrador.iniciarSesion(nombreUsuario, contraseña);
            return unAdministrador;
        } catch (NoResultException | CredencialesInvalidasException e) {
            throw new UsuarioNoEncontradoException();
        }
    }
}

