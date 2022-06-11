package com.utn.casodeuso.dueño;

import com.utn.dominio.Personas;
import com.utn.dominio.Organizaciones;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.dominio.persona.TipoDocumento;
import com.utn.dominio.publicacion.PublicacionMascotaEnAdopcion;

import java.util.List;
import java.util.Map;


public class GenerarPublicacionMascotaEnAdopcion {

    private final Personas personas;

    public GenerarPublicacionMascotaEnAdopcion(Personas personas) {
        this.personas = personas;
    }

    public void ejecutar(int numeroDocumentoDueño, TipoDocumento tipoDocumento, String nombreMascota, Map<String, String> preguntasRespuestas) {
        Persona personaDueño = personas.obtenerPorNumeroDocumento(numeroDocumentoDueño, tipoDocumento);
        Mascota mascotaDeDueño = personaDueño.buscarMascota(nombreMascota);
        Organizacion organizacion = mascotaDeDueño.getOrganizacion();

        PublicacionMascotaEnAdopcion publicacion = new PublicacionMascotaEnAdopcion(personaDueño, mascotaDeDueño, preguntasRespuestas);
        organizacion.añadirPublicacionMascotaEnAdopcion(publicacion);

        personas.guardar(personaDueño);
    }

}
