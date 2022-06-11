package com.utn.casodeuso.recomendacion;

import java.util.List;
import java.util.stream.Collectors;

import com.utn.dominio.Organizaciones;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.publicacion.Preferencia;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.dominio.notificacion.mensaje.MensajeRecomendacionesAdopcion;
import com.utn.dominio.publicacion.PublicacionMascotaEnAdopcion;
import org.springframework.stereotype.Component;

@Component
public class EnviarRecomendacionAdopcion {

    private final Organizaciones organizaciones;

    public EnviarRecomendacionAdopcion(Organizaciones organizaciones) {
        this.organizaciones = organizaciones;
    }

    public void ejecutar() {
        List<Organizacion> todasOrganizaciones = organizaciones.obtenerTodas();
        todasOrganizaciones.forEach(organizacion -> {
            List<PublicacionMascotaEnAdopcion> publicaciones = organizacion.getPublicacionesMascotaEnAdopcion();
            List<Persona> adoptantesActivos = organizacion.adoptantesActivos();
            adoptantesActivos.forEach(adoptante -> {
                List<String> recomendaciones = this.determinarRecomendaciones(adoptante, publicaciones);
                adoptante.getContactoPersonal().notificar(new MensajeRecomendacionesAdopcion(adoptante, recomendaciones));
            });
        });
    }

    private List<String> determinarRecomendaciones(Persona personaAdoptante, List<PublicacionMascotaEnAdopcion> publicacionesAdopcion) {
        Preferencia preferenciaAdoptante = personaAdoptante.getPreferencia();
        return publicacionesAdopcion.stream()
            .filter(publicacionAdopcion -> this.tienePreferencia(publicacionAdopcion, preferenciaAdoptante))
            .map(Object::toString)
            .collect(Collectors.toList());
    }

    private boolean tienePreferencia(PublicacionMascotaEnAdopcion publicacion, Preferencia preferenciaAdoptante) {
        return preferenciaAdoptante.equals(publicacion.obtenerFisionomia());
    }

}