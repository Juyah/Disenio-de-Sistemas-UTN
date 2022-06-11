package com.utn.casodeuso.administrador;

import com.utn.dominio.Administradores;
import com.utn.dominio.organizacion.Administrador;

public class ActualizarAdministradores {

    Administradores administradores;

    public ActualizarAdministradores(Administradores administradores) {
        this.administradores = administradores;
    }

    public void ejecutar(String usuarioCreador, String usuarioNuevo, String contrasenia) {
        Administrador unAdministrador = this.administradores.obtenerPorNombreUsuario(usuarioCreador);
        unAdministrador.darAltaNuevoAdministrador(usuarioNuevo, contrasenia);
        administradores.guardar(unAdministrador);
    }

}
