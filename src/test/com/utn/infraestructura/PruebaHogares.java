package com.utn.infraestructura;

import com.utn.dominio.Hogares;
import com.utn.infraestructura.hogares.HogaresResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import static com.utn.infraestructura.FixtureHogares.*;

public class PruebaHogares {

    private Hogares hogares;

    @BeforeEach
    void inicializar() {
        hogares = Mockito.mock(Hogares.class);
    }

    @Test
    public void se_obtienen_los_hogares_enviando_un_offset_valido_correctamente()  {
        Mockito.when(hogares.obtener()).thenReturn(hogaresResponse);
        HogaresResponse respuesta = hogares.obtener();
        Assertions.assertTrue(respuesta.hogares.size() > 0);
    }

}