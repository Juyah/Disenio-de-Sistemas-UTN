package com.utn.dominio;

import com.utn.dominio.organizacion.Voluntario;

public interface Voluntarios {
    Voluntario obtenerPorNombreUsuario(String nombreUsuario);
    void guardar(Voluntario voluntario);
}
