package com.utn.casodeuso.dueño;

import com.utn.dominio.Personas;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.notificacion.mensaje.Mensaje;
import com.utn.dominio.notificacion.mensaje.MensajeConfirmarMascotaEncontrada;
import com.utn.dominio.persona.TipoDocumento;

public class ConfirmarMascotaEncontrada {

    private final Personas personas;

    public ConfirmarMascotaEncontrada(Personas personas) {
        this.personas = personas;
    }

    public void ejecutar(int numeroDocumentoRescatista, String tipoDocumentoRescatista, int numeroDocumentoDueño, String tipoDocumentoDueño) {
        Persona personaRescatista = personas.obtenerPorNumeroDocumento(numeroDocumentoRescatista, TipoDocumento.buscar(tipoDocumentoRescatista));
        Persona personaDueño = personas.obtenerPorNumeroDocumento(numeroDocumentoDueño, TipoDocumento.buscar(tipoDocumentoDueño));
        Mensaje mensaje = new MensajeConfirmarMascotaEncontrada(personaDueño);
        personaRescatista.notificar(mensaje);
    }

}