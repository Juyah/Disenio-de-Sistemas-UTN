package com.utn.casodeuso.organizacion;

import com.utn.dominio.Normalizador;
import com.utn.dominio.Organizaciones;
import com.utn.dominio.organizacion.Organizacion;

public class NormalizarFoto {

    private final Normalizador normalizador;
    private final Organizaciones organizaciones;

    public NormalizarFoto(Normalizador normalizador, Organizaciones organizaciones) {
        this.normalizador = normalizador;
        this.organizaciones = organizaciones;
    }

    public String ejecutar(String nombreOrganizacion, String fotoOriginal) {
        Organizacion organizacion = organizaciones.obtenerPorNombre(nombreOrganizacion);

        return normalizador.ejecutar(fotoOriginal, organizacion.getCalidadFoto(), organizacion.getTamañoFoto());
    }

}
