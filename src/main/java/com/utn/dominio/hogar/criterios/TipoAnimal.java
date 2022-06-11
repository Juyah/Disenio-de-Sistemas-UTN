package com.utn.dominio.hogar.criterios;

import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.hogar.ValidacionHogar;

import com.utn.infraestructura.hogares.Hogar;

public class TipoAnimal implements ValidacionHogar {

    @Override
    public boolean ejecutar(Hogar hogar, Persona personaRescatista, Mascota mascota) {
        return mascota.getAnimal() == Animal.GATO ? hogar.admisiones.gatos : hogar.admisiones.perros;
    }

}