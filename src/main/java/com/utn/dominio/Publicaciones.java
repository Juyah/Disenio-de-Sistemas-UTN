package com.utn.dominio;

import com.utn.dominio.publicacion.Publicacion;

public interface Publicaciones {
    void guardar(Publicacion publicacion);
    Publicacion obtenerPorId(int idPublicacion);
}