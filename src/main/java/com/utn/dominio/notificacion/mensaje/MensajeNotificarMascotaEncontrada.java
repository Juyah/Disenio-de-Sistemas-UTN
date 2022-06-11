package com.utn.dominio.notificacion.mensaje;

import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Direccion;
import com.utn.dominio.persona.Persona;

import java.util.List;

public class MensajeNotificarMascotaEncontrada extends Mensaje {

    public MensajeNotificarMascotaEncontrada(Persona personaRescatista, Mascota mascota, String estado,
                                             Direccion direccionEncontrada, List<String> fotos) {
        super("Hola, soy " + personaRescatista.getNombre() + " y encontré a " + mascota.getNombre() + ". " +
            "Mi número es " + personaRescatista.getTelefono() + " y mi email es " + personaRescatista.getEmail() +
            "\n El estado en que la encontre es " + estado + ", en esta direccion " +
            direccionEncontrada.getLongitud() + "," + direccionEncontrada.getLatitud() + ".", "[Importante] Rescate de Patitas");
    }

}