package com.utn.casodeuso.dueño;

import com.utn.dominio.Personas;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.persona.TipoDocumento;

import java.util.List;

public class ObtenerMascotas {

    private final Personas personas;

    public ObtenerMascotas(Personas personas) {
        this.personas = personas;
    }

    public List<Mascota> ejecutar(int numeroDocumento, TipoDocumento tipoDocumento) {
        Persona persona = personas.obtenerPorNumeroDocumento(numeroDocumento, tipoDocumento);
        return persona.getMascotas();
    }
}
