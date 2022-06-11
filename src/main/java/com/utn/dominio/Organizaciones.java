package com.utn.dominio;

import com.utn.dominio.organizacion.Organizacion;

import java.util.List;

public interface Organizaciones {
    List<Organizacion> obtenerTodas();
    Organizacion obtenerPorNombre(String nombreOrganizacion);
    void guardar(Organizacion organizacion);
}
