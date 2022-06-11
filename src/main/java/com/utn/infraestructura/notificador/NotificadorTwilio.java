package com.utn.infraestructura.notificador;

import com.utn.dominio.Notificador;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

public class NotificadorTwilio implements Notificador {

    private static NotificadorTwilio instance;
    private static final String ID_SERVICIO = "ACf25d0dc84e759ee44aabaae28289e897";
    private static final String CLAVE_SERVICIO = "427b2790814830815dc832a4168d47bc";
    private static final String NUMERO_ORIGEN = "+16164396006";

    public static NotificadorTwilio instancia() {
        if(instance == null)
            instance = new NotificadorTwilio();
        return instance;
    }

    @Override
    public void enviar(String destinatario, String asunto, String cuerpo) {
        Twilio.init(ID_SERVICIO, CLAVE_SERVICIO);
        Message mensaje = Message.creator(new PhoneNumber(destinatario), new PhoneNumber(NUMERO_ORIGEN), cuerpo).create();
        System.out.println(mensaje.getBody());
    }

}