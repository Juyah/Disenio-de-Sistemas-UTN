package com.utn.casodeuso.usuario;

import com.utn.dominio.Organizaciones;
import com.utn.dominio.Personas;
import com.utn.dominio.Usuarios;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.excepcion.UsuarioYaRegistradoException;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.dominio.persona.Contacto;
import com.utn.dominio.persona.Persona;

import javax.persistence.NoResultException;

public class Registrar
{
    private final Usuarios usuarios;

    public Registrar(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public void ejecutar(String usuario, String contraseña){
        try
        {
            usuarios.obtenerPorNombreUsuario(usuario);
            throw new UsuarioYaRegistradoException();
        }
        catch (NoResultException e){
            Usuario unUsuario = new Usuario(usuario,contraseña);
            usuarios.guardar(unUsuario);
        }
    }
}
