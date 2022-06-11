package com.utn.dominio;

import com.utn.dominio.organizacion.Administrador;

public interface Administradores {
    Administrador obtenerPorNombreUsuario(String nombreUsuario);
    void guardar(Administrador unAdministrador);
}
