package com.utn.casodeuso;

import com.utn.dominio.persona.Direccion;
import com.utn.dominio.persona.Persona;
import com.utn.casodeuso.rescatista.NotificarMascotaEncontrada;
import com.utn.dominio.excepcion.MascotaNoEncontradaException;

import com.utn.dominio.Personas;
import com.utn.dominio.Notificador;

import com.utn.dominio.persona.TipoDocumento;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.mockito.Mockito.atLeast;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static com.utn.casodeuso.FixtureNotificarMascotaEncontrada.*;

public class PruebaNotificarMascotaEncontrada {

    private Personas personas;
    private Notificador notificador;
    private NotificarMascotaEncontrada notificarMascotaEncontrada;

    @BeforeEach
    void inicializar() {
        personas = Mockito.mock(Personas.class);
        notificarMascotaEncontrada = new NotificarMascotaEncontrada(personas);
        notificador = Mockito.mock(Notificador.class);
        inicializarEstrategiasDeNotificacion(notificador);
    }

/*    @Test
    public void se_notifica_al_dueño_que_se_encontro_su_mascota() {
        dado_un_rescatista_y_un_dueño_con_mascota_existente();
        dados_que_los_dueños_y_rescatistas_estan_registrados(personaDueño, personaRescatista);
        dada_que_la_notificacion_al_dueño_es_exitosa(numeroSMSRescatista, numeroDueño, asuntoMascotaEncontrada, mensajeMascotaEncontrada);
        cuando_notificamos_al_dueño_que_se_encontro_su_mascota(numeroDocumentoRescatista, tipoDocumentoRescatista, numeroDocumentoDueño, tipoDocumentoDueño, nombreMascotaEncontradaExistente);
        entonces_al_dueño_se_le_envia_al_menos_un_mensaje(asuntoMascotaEncontrada, mensajeMascotaEncontrada);
    }

    @Test
    public void no_se_notifica_al_dueño_cuando_una_mascota_encontrada_no_le_corresponde() {
        dado_un_rescatista_y_un_dueño_con_mascota_existente();
        dados_que_los_dueños_y_rescatistas_estan_registrados(personaDueño, personaRescatista);
        Assertions.assertThrows(MascotaNoEncontradaException.class, () ->
            cuando_notificamos_al_dueño_que_se_encontro_su_mascota(numeroDocumentoRescatista, tipoDocumentoRescatista, numeroDocumentoDueño, tipoDocumentoDueño, nombreMascotaEncontradaNoExistente));
    }*/

    private void dado_un_rescatista_y_un_dueño_con_mascota_existente() {
        inicializarMascota();
        inicializarDueño();
        inicializarRescatista();
    }

    private void dados_que_los_dueños_y_rescatistas_estan_registrados(Persona personaDueño, Persona personaRescatista) {
        Mockito.when(personas.obtenerPorNumeroDocumento(numeroDocumentoRescatista, tipoDocumentoRescatista)).thenReturn(personaRescatista);
        Mockito.when(personas.obtenerPorNumeroDocumento(numeroDocumentoDueño, tipoDocumentoDueño)).thenReturn(personaDueño);
    }

    private void dada_que_la_notificacion_al_dueño_es_exitosa(String origen, String destino, String asunto, String mensaje) {
        Mockito.doNothing().when(notificador).enviar(destino, asunto, mensaje);
    }

    private void cuando_notificamos_al_dueño_que_se_encontro_su_mascota(int numeroDocumentoRescatista, TipoDocumento tipoDocumentoRescatista, int idMascota, String estado, Direccion direccion, List<String> fotos) {
        notificarMascotaEncontrada.ejecutar(numeroDocumentoRescatista, tipoDocumentoRescatista, idMascota, estado, direccion, fotos);
    }

    private void entonces_al_dueño_se_le_envia_al_menos_un_mensaje(String asunto, String mensaje) {
       Mockito.verify(notificador, atLeast(1)).enviar(any(String.class), eq(asunto), eq(mensaje));
    }

}
