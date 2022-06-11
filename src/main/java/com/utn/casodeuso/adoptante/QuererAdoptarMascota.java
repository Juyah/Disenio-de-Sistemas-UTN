package com.utn.casodeuso.adoptante;

import com.utn.dominio.Personas;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.notificacion.mensaje.Mensaje;
import com.utn.dominio.notificacion.mensaje.MensajeQuererAdoptarMascota;
import com.utn.dominio.persona.TipoDocumento;

public class QuererAdoptarMascota {

    private final Personas personas;

    public QuererAdoptarMascota(Personas personas) {
        this.personas = personas;
    }

    public void ejecutar(int numeroDocDuenio, TipoDocumento tipoDocumentoDuenio, String nombreMascota, int numeroDocAdoptante, TipoDocumento tipoDocumentoAdoptante) {
        Persona personaAdoptante = personas.obtenerPorNumeroDocumento(numeroDocAdoptante, tipoDocumentoAdoptante);
        Persona personaDueño = personas.obtenerPorNumeroDocumento(numeroDocDuenio, tipoDocumentoDuenio);

        Mensaje mensaje = new MensajeQuererAdoptarMascota(personaAdoptante, nombreMascota);
        personaDueño.notificar(mensaje);
    }

}