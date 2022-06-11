package com.utn.dominio.notificacion.mensaje;

import com.utn.dominio.persona.Persona;

public class MensajeConfirmarMascotaEncontrada extends Mensaje {

    public MensajeConfirmarMascotaEncontrada(Persona personaDueño) {
        super("Hola, soy " + personaDueño.getNombre() + "y encontré a mi mascota en una de tus publicaciones."
            + "Mi número es " + personaDueño.getTelefono() + " y mi email es " + personaDueño.getEmail(),
            "[Importante] Rescate de Patitas");
    }

}