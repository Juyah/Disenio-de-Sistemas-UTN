package com.utn.infraestructura;

import com.utn.dominio.excepcion.EmailNoEnviadoException;
import com.utn.dominio.Notificador;
import com.utn.infraestructura.notificador.NotificadorEmail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import static com.utn.infraestructura.FixtureNotificadorEmail.*;

public class PruebaNotificadorEmail {

    private Notificador notificadorEmail;

    @BeforeEach
    void inicializar() {
        notificadorEmail = new NotificadorEmail();
    }

    @Test
    public void se_envia_un_email_correctamente() {
        notificadorEmail.enviar(emailDestinoValido, asunto, cuerpo);
    }

    @Test
    public void se_envia_un_email_y_lanza_una_excepcion() {
        Assertions.assertThrows(EmailNoEnviadoException.class, () ->
            notificadorEmail.enviar(emailDestinoInvalido, asunto, cuerpo));
    }

}