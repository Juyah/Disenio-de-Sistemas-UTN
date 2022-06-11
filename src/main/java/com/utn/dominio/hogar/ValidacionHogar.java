package com.utn.dominio.hogar;

import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;
import com.utn.infraestructura.hogares.Hogar;

public interface ValidacionHogar {
    boolean ejecutar(Hogar hogar, Persona personaRescatista, Mascota mascota);
}