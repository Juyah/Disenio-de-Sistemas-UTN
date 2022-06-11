package com.utn.dominio.notificacion.estrategia;

import com.utn.dominio.notificacion.MedioDeComunicacion;
import com.utn.dominio.notificacion.mensaje.Mensaje;
import com.utn.infraestructura.notificador.NotificadorTwilio;

import javax.persistence.*;

@Entity
@DiscriminatorValue("whatsapp")
public class WhatsApp extends MedioDeComunicacion {

    @Transient
    private static final String PREFIJO = "whatsapp:";

    public WhatsApp(boolean esPreferido) {
        super(esPreferido);
    }

    public WhatsApp() { }

    @Override
    public void enviar(Mensaje mensaje) {
        System.out.println(mensaje.cuerpo());
        NotificadorTwilio.instancia().enviar(PREFIJO + mensaje.telefonoDestino(), mensaje.asunto(), mensaje.cuerpo());
        System.out.println("Mensaje enviado por WhatsApp!");
    }

}