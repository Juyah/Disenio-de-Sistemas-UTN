package com.utn.casodeuso.administrador;

import com.utn.dominio.Administradores;
import com.utn.dominio.Organizaciones;
import com.utn.dominio.organizacion.Administrador;
import com.utn.dominio.organizacion.Organizacion;

import java.util.List;

public class ActualizarCaracteristicas
{

    Administradores administradores;

    public ActualizarCaracteristicas(Administradores administradores) {
        this.administradores = administradores;
    }

    public void ejecutar(String nombreUsuario, String unaCaracteristica)
    {
        Administrador unAdministrador = this.administradores.obtenerPorNombreUsuario(nombreUsuario);
        unAdministrador.añadirCaracteristica(unaCaracteristica);
        administradores.guardar(unAdministrador);
    }
}
