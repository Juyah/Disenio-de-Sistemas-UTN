package com.utn.dominio.hogar.criterios;

import com.utn.dominio.animal.Mascota;
import com.utn.dominio.animal.Tamaño;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.hogar.ValidacionHogar;
import com.utn.infraestructura.hogares.Hogar;

public class TamañoMascota implements ValidacionHogar {

    @Override
    public boolean ejecutar(Hogar hogar, Persona personaRescatista, Mascota mascota) {
        return (mascota.getTamaño() == Tamaño.CHICO) != hogar.patio;
    }

}