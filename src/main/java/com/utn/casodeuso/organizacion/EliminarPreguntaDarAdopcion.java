package com.utn.casodeuso.organizacion;

import com.utn.dominio.Organizaciones;
import com.utn.dominio.organizacion.Organizacion;

import java.util.List;

public class EliminarPreguntaDarAdopcion {

    private final Organizaciones organizaciones;

    public EliminarPreguntaDarAdopcion(Organizaciones organizaciones) {
        this.organizaciones = organizaciones;
    }

    public void ejecutar(String nombreOrganizacion, List<String> preguntasAdopcion){
        Organizacion organizacion = organizaciones.obtenerPorNombre(nombreOrganizacion);

        preguntasAdopcion.forEach(unaPregunta -> organizacion.eliminarPreguntaAdopcion(unaPregunta));

        organizaciones.guardar(organizacion);
    }

}
