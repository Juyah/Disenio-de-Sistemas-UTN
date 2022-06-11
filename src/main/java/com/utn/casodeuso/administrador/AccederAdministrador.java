package com.utn.casodeuso.administrador;

import com.utn.dominio.Administradores;
import com.utn.dominio.Organizaciones;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.excepcion.CredencialesInvalidasException;
import com.utn.dominio.excepcion.NoEsAdministradorDeLaOrganizacionException;
import com.utn.dominio.excepcion.UsuarioNoEncontradoException;
import com.utn.dominio.organizacion.Administrador;
import com.utn.dominio.organizacion.Organizacion;

import javax.persistence.NoResultException;

public class AccederAdministrador
{
    protected final Administradores administradores;

    public AccederAdministrador(Administradores administradores)
    {
        this.administradores = administradores;
    }

    public Organizacion ejecutar(String nombreUsuario, String contraseña)
    {
        try
        {
            Administrador unAdministrador = administradores.obtenerPorNombreUsuario(nombreUsuario);
            unAdministrador.iniciarSesion(nombreUsuario, contraseña);
            return unAdministrador.getOrganizacion();
        }
        catch(NoResultException | CredencialesInvalidasException e)
        {
            throw new UsuarioNoEncontradoException();
        }
    }
}
