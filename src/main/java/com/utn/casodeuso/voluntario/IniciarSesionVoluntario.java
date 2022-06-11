package com.utn.casodeuso.voluntario;

import com.utn.dominio.Administradores;
import com.utn.dominio.Voluntarios;
import com.utn.dominio.excepcion.CredencialesInvalidasException;
import com.utn.dominio.excepcion.UsuarioNoEncontradoException;
import com.utn.dominio.organizacion.Administrador;
import com.utn.dominio.organizacion.Voluntario;

import javax.persistence.NoResultException;

public class IniciarSesionVoluntario {
    private final Voluntarios voluntarios;

    public IniciarSesionVoluntario(Voluntarios voluntarios) {
        this.voluntarios = voluntarios;
    }

    public Voluntario ejecutar(String nombreUsuario, String contraseña) {
        try {
            Voluntario unVoluntario = voluntarios.obtenerPorNombreUsuario(nombreUsuario);
            unVoluntario.iniciarSesion(nombreUsuario, contraseña);
            return unVoluntario;
        } catch (NoResultException | CredencialesInvalidasException e) {
            throw new UsuarioNoEncontradoException();
        }

    }
}
