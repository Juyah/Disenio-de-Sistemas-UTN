package com.utn.infraestructura.api.publicacion;

import com.utn.casodeuso.adoptante.GenerarPublicacionBusquedaAdopcion;
import com.utn.casodeuso.adoptante.QuererAdoptarMascota;
import com.utn.casodeuso.dueño.GenerarPublicacionMascotaEnAdopcion;
import com.utn.casodeuso.organizacion.ObtenerPublicacionesMascotaEnAdopcion;
import com.utn.dominio.Organizaciones;
import com.utn.dominio.Personas;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.publicacion.Publicacion;
import com.utn.dominio.publicacion.PublicacionMascotaEnAdopcion;
import com.utn.infraestructura.api.SesionManager;
import com.utn.infraestructura.persistencia.OrganizacionesEnMySQL;
import com.utn.infraestructura.persistencia.PersonasEnMySQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ControladorPublicacion {

    private final ObtenerPublicacionesMascotaEnAdopcion obtenerPublicacionesMascotaEnAdopcion;
    private final GenerarPublicacionBusquedaAdopcion generarPublicacionBusquedaAdopcion;
    private final GenerarPublicacionMascotaEnAdopcion generarPublicacionMascotaEnAdopcion;
    private final QuererAdoptarMascota quererAdoptarMascota;

    public ControladorPublicacion() {
        Personas personasEnMySQL = new PersonasEnMySQL();
        Organizaciones organizacionesEnMySQL = new OrganizacionesEnMySQL();
        this.generarPublicacionBusquedaAdopcion = new GenerarPublicacionBusquedaAdopcion(personasEnMySQL, organizacionesEnMySQL);
        this.obtenerPublicacionesMascotaEnAdopcion = new ObtenerPublicacionesMascotaEnAdopcion(new OrganizacionesEnMySQL());
        this.generarPublicacionMascotaEnAdopcion = new GenerarPublicacionMascotaEnAdopcion(personasEnMySQL);
        this.quererAdoptarMascota = new QuererAdoptarMascota(personasEnMySQL);
    }


    @PostMapping("adoptante/generarPublicacionBusquedaAdopcion")
    public ResponseEntity generarPublicacionBusquedaAdopcion(@RequestBody SolicitudGenerarPublicacionBusquedaAdopcion solicitud) {
        System.out.println(solicitud);
        generarPublicacionBusquedaAdopcion.ejecutar(solicitud.getNumeroDocumentoAdoptante(), solicitud.getTipoDocumentoAdoptante(), solicitud.getNombreOrganizacion(), solicitud.getComodidades());
        return ResponseEntity.status(200).build();
    }

    @PostMapping("adoptante/adoptarMascotaPublicacion")
    public ResponseEntity adoptarMascotaPublicacion(@RequestBody SolicitudAdoptarMascotaPublicacion solicitud) {
        try {
            quererAdoptarMascota.ejecutar(solicitud.getNumeroDocDuenio(), solicitud.getTipoDocumentoDuenio(),
                    solicitud.getNombreMascota(), solicitud.getNumeroDocAdoptante(), solicitud.getTipoDocumentoAdoptante());
        } catch (Exception ignored) {

        }
        return ResponseEntity.status(200).build();
    }

    @GetMapping("organizacion/{nombreOrganizacion}/publicacionesMascotaAdopcion")
    public ResponseEntity publicacionesMascotaEnAdopcion(@PathVariable("nombreOrganizacion") String nombreOrganizacion) {
        List<PublicacionMascotaEnAdopcion> publicaciones = obtenerPublicacionesMascotaEnAdopcion.ejecutar(nombreOrganizacion)
                .stream().filter(Publicacion::isEstaVisible).collect(Collectors.toList());
        List<RespuestaPublicacionMascotaEnAdopcion> publicacionesJson = publicaciones.stream()
                .map(unaPublicacion -> new RespuestaPublicacionMascotaEnAdopcion(unaPublicacion.getMascota().getNombre(),
                        unaPublicacion.getMascota().getDescripcionFisica(), unaPublicacion.getMascota().getFotos(),
                        unaPublicacion.getPersona().getDocumento().getNumero(), unaPublicacion.getPersona().getDocumento().getTipo())).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.status(200).body(publicacionesJson);
    }

    @PostMapping("persona/publicarMascotaEnAdopcion")
    public ResponseEntity generarPublicacionMascotaEnAdopcion(@RequestBody SolicitudPublicarMascotaEnAdopcion solicitud) {
        this.generarPublicacionMascotaEnAdopcion.ejecutar(solicitud.getNumeroDocumento(), solicitud.getTipoDocumento(),
                solicitud.getNombreMascota(), solicitud.getPreguntasRespuestas());

        return ResponseEntity.status(201).build();
    }


    private Usuario obtenerUsuarioSesionManager(String idSesion) {
        SesionManager sesionManager = SesionManager.getInstance();

        Map<String, Object> unosDatos = sesionManager.obtenerAtributos(idSesion);
        return (Usuario) unosDatos.get("usuario");
    }
}
