package com.utn.infraestructura.api.rescatista;

import com.utn.casodeuso.rescatista.BuscarHogarTransito;
import com.utn.casodeuso.rescatista.BuscarHogarTransitoSinID;
import com.utn.casodeuso.rescatista.GenerarPublicacionMascotaEncontrada;
import com.utn.casodeuso.rescatista.NotificarMascotaEncontrada;
import com.utn.dominio.Personas;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.hogar.criterios.Criterios;
import com.utn.dominio.persona.Direccion;
import com.utn.infraestructura.hogares.Hogar;
import com.utn.infraestructura.hogares.ServicioHogares;
import com.utn.infraestructura.persistencia.MascotasEnMySQL;
import com.utn.infraestructura.persistencia.OrganizacionesEnMySQL;
import com.utn.infraestructura.persistencia.PersonasEnMySQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ControladorRescatista {

    private final NotificarMascotaEncontrada notificarMascotaEncontrada;
    private final GenerarPublicacionMascotaEncontrada generarPublicacionMascotaEncontrada;
    private final BuscarHogarTransito buscarHogarTransito;
    private final BuscarHogarTransitoSinID buscarHogarTransitoSinID;

    public ControladorRescatista() {
        Personas personasEnMySQL = new PersonasEnMySQL();
        this.notificarMascotaEncontrada = new NotificarMascotaEncontrada(personasEnMySQL);
        this.generarPublicacionMascotaEncontrada = new GenerarPublicacionMascotaEncontrada(new OrganizacionesEnMySQL(), personasEnMySQL);
        this.buscarHogarTransito = new BuscarHogarTransito(personasEnMySQL, new MascotasEnMySQL(), ServicioHogares.getInstance(), Criterios.getInstance().getCriterios());
        this.buscarHogarTransitoSinID = new BuscarHogarTransitoSinID(personasEnMySQL, new MascotasEnMySQL(), ServicioHogares.getInstance(), Criterios.getInstance().getCriterios());
    }

    //int numeroDocumentoRescatista, TipoDocumento tipoDocumentoRescatista, int idMascota, String estado, Direccion direccionEncontrada, String foto
    @PostMapping("mascotaEncontradaConChapita")
    public ResponseEntity mascotaEncontradaConChapita(@RequestBody SolicitudMascotaEncontradaChapita solicitud) {
        try {
            this.notificarMascotaEncontrada.ejecutar(solicitud.getNumeroDocumentoRescatista(),
                    solicitud.getTipoDocumentoRescatista(), solicitud.getIdMascota(), solicitud.getEstado(),
                    new Direccion(solicitud.getLatitud(), solicitud.getLongitud()), solicitud.getFotos());
        } catch (Exception ignored) {

        }
        return ResponseEntity.status(200).build();
    }


    @PostMapping("rescatista/PublicarMascotaEncontrada")
    public ResponseEntity mascotaEncontrada(@RequestBody SolicitudMascotaEncontradaPublicacion solicitud) {
        this.generarPublicacionMascotaEncontrada.ejecutar(solicitud.getNumeroDocumentoRescatista(), solicitud.getTipoDocumentoRescatista(),
                solicitud.getNombreOrganizacion(), new Direccion(solicitud.getLatitud(),
                        solicitud.getLongitud()), solicitud.getEstado(), solicitud.getFotos());

        return ResponseEntity.status(200).build();
    }

    @PostMapping("rescatista/HogarTransitoSinChapita")
    public ResponseEntity buscarHogarTransitoSinChapita(@RequestBody SolicitudHogarSinChapita solicitud) {
        Mascota mascotaSinChapa = new Mascota();
        mascotaSinChapa.setTamaño(solicitud.getTamanio());
        mascotaSinChapa.setAnimal(solicitud.getAnimal());
        List<Hogar> hogares = buscarHogarTransitoSinID.ejecutar(solicitud.getNumDocRescatista(), solicitud.getTipoDocRescatista(),
                mascotaSinChapa);

        List<RespuestaHogares> hogaresRespuesta = hogares.stream().map(unHogar -> new RespuestaHogares(unHogar.nombre,
                unHogar.ubicacion.direccion, unHogar.ubicacion.latitud, unHogar.ubicacion.longitud,
                unHogar.telefono, unHogar.caracteristicas)).collect(Collectors.toList());
        return ResponseEntity.status(200).body(hogaresRespuesta);
    }


    @PostMapping("rescatista/HogarTransitoChapita")
    public ResponseEntity buscarHogarTransitoChapita(@RequestBody SolicitudHogarConChapita solictud) {
        List<Hogar> hogares = buscarHogarTransito.ejecutar(solictud.getNumDocRescatista(), solictud.getTipoDocRescatista(), solictud.getIdMascota());
        List<RespuestaHogares> hogaresRespuesta = hogares.stream().map(unHogar -> new RespuestaHogares(unHogar.nombre,
                unHogar.ubicacion.direccion, unHogar.ubicacion.latitud, unHogar.ubicacion.longitud,
                unHogar.telefono, unHogar.caracteristicas)).collect(Collectors.toList());

        return ResponseEntity.status(200).body(hogaresRespuesta);
    }
}
