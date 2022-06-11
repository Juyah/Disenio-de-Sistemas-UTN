package com.utn.casodeuso.usuario;

import com.utn.dominio.Usuarios;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.excepcion.CredencialesInvalidasException;
import com.utn.dominio.excepcion.UsuarioNoEncontradoException;

import javax.persistence.NoResultException;

public class IniciarSesion {

    private final Usuarios usuarios;

    public IniciarSesion(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario ejecutar(String nombreUsuario, String contraseña) {
        try
        {
            Usuario unUsuario = usuarios.obtenerPorNombreUsuario(nombreUsuario);
            unUsuario.iniciarSesion(nombreUsuario, contraseña);
            return unUsuario;
        }
        catch(NoResultException | CredencialesInvalidasException e)
        {
            throw new UsuarioNoEncontradoException();
        }
    }
}