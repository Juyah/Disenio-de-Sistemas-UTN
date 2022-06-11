package com.utn.dominio.notificacion.estrategia;

import com.utn.dominio.notificacion.MedioDeComunicacion;
import com.utn.dominio.notificacion.mensaje.Mensaje;
import com.utn.infraestructura.notificador.NotificadorEmail;

import javax.persistence.*;

@Entity
@DiscriminatorValue("email")
public class Email extends MedioDeComunicacion {

    public Email(boolean esPreferido) {
        super(esPreferido);
    }

    public Email() { }

    @Override
    public void enviar(Mensaje mensaje) {
        System.out.println(mensaje.cuerpo());
        NotificadorEmail.instancia().enviar(mensaje.emailDestino(), mensaje.asunto(), mensaje.cuerpo());
        System.out.println("Mensaje enviado por email!");
    }

}