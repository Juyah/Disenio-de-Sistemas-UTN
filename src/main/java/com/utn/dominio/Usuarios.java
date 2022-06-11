package com.utn.dominio;

import com.utn.dominio.autenticacion.Usuario;

public interface Usuarios {
    Usuario obtenerPorNombreUsuario(String nombreUsuario);
    void guardar(Usuario unUsuario);
}