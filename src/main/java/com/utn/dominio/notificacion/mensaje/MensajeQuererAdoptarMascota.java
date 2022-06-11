package com.utn.dominio.notificacion.mensaje;

import com.utn.dominio.persona.Persona;

public class MensajeQuererAdoptarMascota extends Mensaje {

    public MensajeQuererAdoptarMascota(Persona personaAdoptante, String nombreMascota) {
        super("Hola, soy " + personaAdoptante.getNombre() + ". Vi a tu mascota " + nombreMascota + " en la plataforma. " +
            "Mi número es " + personaAdoptante.getTelefono() + " y mi email es " + personaAdoptante.getEmail(),
            "[Importante] Rescate de Patitas");
    }

}