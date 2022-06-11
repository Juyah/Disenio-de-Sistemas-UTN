package com.utn.casodeuso.organizacion;

import java.util.List;
import com.utn.dominio.Organizaciones;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.dominio.publicacion.PublicacionMascotaEnAdopcion;

public class ObtenerPublicacionesMascotaEnAdopcion {

    private final Organizaciones organizaciones;

    public ObtenerPublicacionesMascotaEnAdopcion(Organizaciones organizaciones) {
        this.organizaciones = organizaciones;
    }

    public List<PublicacionMascotaEnAdopcion> ejecutar(String nombreOrganizacion) {
        Organizacion organizacion = organizaciones.obtenerPorNombre(nombreOrganizacion);
        return organizacion.getPublicacionesMascotaEnAdopcion();
    }

}