package com.utn.dominio.hogar.criterios;

import java.util.List;
import java.util.ArrayList;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.hogar.ValidacionHogar;
import com.utn.infraestructura.hogares.Hogar;

public class CumpleCaracteristicas implements ValidacionHogar {

    @Override
    public boolean ejecutar(Hogar hogar, Persona personaRescatista, Mascota mascota) {
        List<String> unasCaracteristicas = new ArrayList<>(mascota.getCaracteristicas().values());
        return !unasCaracteristicas.isEmpty() && hogar.caracteristicas.containsAll(unasCaracteristicas);
    }

}