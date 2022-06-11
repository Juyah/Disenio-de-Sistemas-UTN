package com.utn.casodeuso.rescatista;

import com.utn.dominio.Hogares;
import com.utn.dominio.Mascotas;
import com.utn.dominio.Personas;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.hogar.ValidacionHogar;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.persona.TipoDocumento;
import com.utn.infraestructura.hogares.Hogar;
import com.utn.infraestructura.hogares.HogaresResponse;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarHogarTransitoSinID {
    private final Personas personas;
    private final Hogares hogares;
    private final List<ValidacionHogar> validacionesHogar;

    public BuscarHogarTransitoSinID(Personas personas, Mascotas mascotas, Hogares hogares, List<ValidacionHogar> validacionesHogar) {
        this.personas = personas;
        this.hogares = hogares;
        this.validacionesHogar = validacionesHogar;
    }

    public List<Hogar> ejecutar(int numDocRescatista, TipoDocumento tipoDocRescatista, Mascota mascota) {
        Persona persona = personas.obtenerPorNumeroDocumento(numDocRescatista, tipoDocRescatista);
        HogaresResponse respuesta = hogares.obtener();
        return respuesta.hogares.stream().filter(hogar ->
                validacionesHogar.stream().allMatch(validacionHogar ->
                        validacionHogar.ejecutar(hogar, persona, mascota))).collect(Collectors.toList());
    }
}
