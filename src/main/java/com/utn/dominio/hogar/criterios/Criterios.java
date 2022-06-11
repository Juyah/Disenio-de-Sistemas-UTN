package com.utn.dominio.hogar.criterios;

import com.utn.dominio.hogar.ValidacionHogar;

import java.util.ArrayList;
import java.util.List;

public class Criterios {

    private static Criterios instance;
    private final List<ValidacionHogar> criterios;

    public static Criterios getInstance() {
        if (instance == null)
            instance = new Criterios();
        return instance;
    }

    private Criterios() {
        this.criterios = new ArrayList<ValidacionHogar>() {{
            add(new CapacidadDisponible());
            add(new Cercania());
            add(new TamañoMascota());
            add(new TipoAnimal());
        }};
    }

    public List<ValidacionHogar> getCriterios() {
        return criterios;
    }
}
