package com.utn.infraestructura.job;

import com.utn.casodeuso.recomendacion.EnviarRecomendacionAdopcion;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PlanificadorRecomendacionAdopciones {

    private final EnviarRecomendacionAdopcion enviarRecomendacionAdopcion;

    public PlanificadorRecomendacionAdopciones(EnviarRecomendacionAdopcion enviarRecomendacionAdopcion){
        this.enviarRecomendacionAdopcion = enviarRecomendacionAdopcion;
    }

    @Scheduled(fixedDelayString = "604800000")
    public void ejecutar() {
        enviarRecomendacionAdopcion.ejecutar();
    }

}