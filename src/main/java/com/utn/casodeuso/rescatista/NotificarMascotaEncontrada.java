package com.utn.casodeuso.rescatista;

import com.utn.dominio.Personas;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.notificacion.mensaje.Mensaje;
import com.utn.dominio.notificacion.mensaje.MensajeNotificarMascotaEncontrada;
import com.utn.dominio.persona.Direccion;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.persona.TipoDocumento;

import java.util.List;

public class NotificarMascotaEncontrada {

    private final Personas personas;

    public NotificarMascotaEncontrada(Personas personas) {
        this.personas = personas;
    }

    public void ejecutar(int numeroDocumentoRescatista, TipoDocumento tipoDocumentoRescatista, int idMascota, String estado,
                         Direccion direccionEncontrada, List<String> fotos) {
        Persona personaDueño = personas.obtenerPorIdMascota(idMascota);
        Persona personaRescatista = personas.obtenerPorNumeroDocumento(numeroDocumentoRescatista, tipoDocumentoRescatista);


        Mascota mascota = personaDueño.buscarMascotaPorId(idMascota);
        Mensaje mensaje = new MensajeNotificarMascotaEncontrada(personaRescatista, mascota, estado, direccionEncontrada, fotos);

        personaDueño.notificar(mensaje);
    }

}