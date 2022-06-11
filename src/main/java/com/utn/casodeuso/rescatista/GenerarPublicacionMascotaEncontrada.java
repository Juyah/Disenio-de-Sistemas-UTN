package com.utn.casodeuso.rescatista;

import com.utn.dominio.Personas;
import com.utn.dominio.Organizaciones;

import com.utn.dominio.persona.Persona;
import com.utn.dominio.persona.Direccion;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.dominio.persona.TipoDocumento;
import com.utn.dominio.publicacion.PublicacionMascotaEncontrada;

import java.util.List;

public class GenerarPublicacionMascotaEncontrada {

    private final Personas personas;
    private final Organizaciones organizaciones;

    public GenerarPublicacionMascotaEncontrada(Organizaciones organizaciones, Personas personas) {
        this.organizaciones = organizaciones;
        this.personas = personas;
    }

    public void ejecutar(int numeroDoc, TipoDocumento tipoDoc, String nombreOrganizacion, Direccion direccionMascotaEncontrada, String estadoMascota, List<String> fotos) {
        Organizacion organizacion = organizaciones.obtenerPorNombre(nombreOrganizacion);
        Persona personaRescatista = personas.obtenerPorNumeroDocumento(numeroDoc, tipoDoc);

        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(personaRescatista, direccionMascotaEncontrada, estadoMascota);
        publicacion.setFotosMascota(fotos);
        organizacion.añadirPublicacionMascotaEncontrada(publicacion);

        organizaciones.guardar(organizacion);
    }
}