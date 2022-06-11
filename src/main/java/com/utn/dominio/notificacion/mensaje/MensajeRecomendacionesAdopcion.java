package com.utn.dominio.notificacion.mensaje;

import com.utn.dominio.persona.Persona;

import java.util.List;

public class MensajeRecomendacionesAdopcion extends Mensaje {

    public MensajeRecomendacionesAdopcion(Persona personaAdoptante, List<String> recomendaciones) {
        super("cuerpo", "[Importante] Rescate de Patitas");
    }

}