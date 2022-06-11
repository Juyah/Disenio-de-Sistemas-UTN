package com.utn.casodeuso.organizacion;

import com.utn.dominio.Organizaciones;
import com.utn.dominio.organizacion.Organizacion;

import java.util.List;

public class GenerarPreguntaDarAdopcion {

    private final Organizaciones organizaciones;

    public GenerarPreguntaDarAdopcion(Organizaciones organizaciones) {
        this.organizaciones = organizaciones;
    }

    public void ejecutar(String nombreOrganizacion, List<String> preguntasAdopcion) {
        Organizacion organizacion = organizaciones.obtenerPorNombre(nombreOrganizacion);

        preguntasAdopcion.forEach(unaPregunta -> organizacion.añadirPreguntaAdopcion(unaPregunta));

        organizaciones.guardar(organizacion);
    }

}