package com.utn.dominio.hogar.criterios;

import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.hogar.ValidacionHogar;
import com.utn.infraestructura.hogares.Hogar;

public class CapacidadDisponible implements ValidacionHogar {

    @Override
    public boolean ejecutar(Hogar hogar, Persona personaRescatista, Mascota mascota) {
        return hogar.lugares_disponibles > 0;
    }

}