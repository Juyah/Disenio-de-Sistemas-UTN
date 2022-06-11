package com.utn.dominio.hogar.criterios;

import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.hogar.ValidacionHogar;

import com.utn.infraestructura.hogares.Hogar;

public class Cercania implements ValidacionHogar {

    @Override
    public boolean ejecutar(Hogar hogar, Persona personaRescatista, Mascota mascota) {
        double latitudRescatista = personaRescatista.getDireccion().getLatitud();
        double longitudRescatista = personaRescatista.getDireccion().getLongitud();
        double latitudHogar = hogar.ubicacion.latitud;
        double longitudHogar = hogar.ubicacion.longitud;
        double diferenciaLatitud = latitudHogar - latitudRescatista;
        if(diferenciaLatitud < 0)
            diferenciaLatitud = diferenciaLatitud * (-1);
        double diferenciaLongitud = longitudHogar - longitudRescatista;
        if(diferenciaLongitud < 0)
            diferenciaLongitud = diferenciaLongitud * (-1);
        return diferenciaLatitud <= personaRescatista.getRadioHogares() &&
            diferenciaLongitud <= personaRescatista.getRadioHogares();
    }

}