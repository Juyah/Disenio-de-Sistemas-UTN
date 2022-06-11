package com.utn.dominio.notificacion.mensaje;

import com.utn.dominio.publicacion.Publicacion;

public class MensajePublicacionQuieroAdoptar extends Mensaje {

    public MensajePublicacionQuieroAdoptar(Publicacion publicacion) {
        super("cuerpo", "[Importante] Rescate de Patitas");
    }

}