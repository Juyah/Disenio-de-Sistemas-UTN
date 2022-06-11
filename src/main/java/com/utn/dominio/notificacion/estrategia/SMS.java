package com.utn.dominio.notificacion.estrategia;

import com.utn.dominio.notificacion.MedioDeComunicacion;
import com.utn.dominio.notificacion.mensaje.Mensaje;
import com.utn.infraestructura.notificador.NotificadorTwilio;

import javax.persistence.*;

@Entity
@DiscriminatorValue("sms")
public class SMS extends MedioDeComunicacion {

    public SMS(boolean esPreferido) {
        super(esPreferido);
    }

    public SMS() { }

    @Override
    public void enviar(Mensaje mensaje) {
        System.out.println(mensaje.cuerpo());
        NotificadorTwilio.instancia().enviar(mensaje.telefonoDestino(), mensaje.asunto(), mensaje.cuerpo());
        System.out.println("Mensaje enviado por correo SMS!");
    }
}