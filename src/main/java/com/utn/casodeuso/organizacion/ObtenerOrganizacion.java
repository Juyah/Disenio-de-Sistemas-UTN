package com.utn.casodeuso.organizacion;

import com.utn.dominio.Organizaciones;
import com.utn.dominio.organizacion.Organizacion;

import java.util.List;

public class ObtenerOrganizacion {
    private final Organizaciones organizaciones;

    public ObtenerOrganizacion(Organizaciones organizaciones) {
        this.organizaciones = organizaciones;
    }

    public Organizacion ejecutar(String nombreOrganizaion) {
        return organizaciones.obtenerPorNombre(nombreOrganizaion);
    }
}
