package com.utn.infraestructura.notificador;

import com.utn.dominio.Notificador;
import com.utn.dominio.excepcion.EmailNoEnviadoException;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

public class NotificadorEmail implements Notificador {

    private static NotificadorEmail instance;
    private static final String EMAIL_ORIGEN = "rescatedepatitas.dds.g6.k3002@gmail.com";
    private static final String CONTRASEÑA_EMAIL_ORIGEN = "ddsg6k3002";

    public static NotificadorEmail instancia() {
        if(instance == null)
            instance = new NotificadorEmail();
        return instance;
    }

    @Override
    public void enviar(String destinatario, String asunto, String cuerpo) {
        Properties propiedades = this.propiedades();
        Session sesion = Session.getDefaultInstance(propiedades);
        MimeMessage mensaje = new MimeMessage(sesion);
        try {
            mensaje.setFrom(new InternetAddress(EMAIL_ORIGEN));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(EMAIL_ORIGEN, CONTRASEÑA_EMAIL_ORIGEN);
            transporte.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
            transporte.close();
        } catch (MessagingException ex) {
            throw new EmailNoEnviadoException();
        }
    }

    private Properties propiedades() {
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        return propiedades;
    }

}