package com.utn.infraestructura.api.voluntario;

import com.utn.casodeuso.organizacion.ObtenerPublicacionesMascotaEnAdopcion;
import com.utn.casodeuso.voluntario.AprobarPublicacion;
import com.utn.casodeuso.voluntario.IniciarSesionVoluntario;
import com.utn.dominio.excepcion.UsuarioNoEncontradoException;
import com.utn.dominio.organizacion.Voluntario;
import com.utn.infraestructura.api.SesionManager;
import com.utn.infraestructura.api.usuario.LoginResponse;
import com.utn.infraestructura.api.usuario.SolicitudIniciarSesion;
import com.utn.infraestructura.persistencia.OrganizacionesEnMySQL;
import com.utn.infraestructura.persistencia.PublicacionesEnMySQL;
import com.utn.infraestructura.persistencia.VoluntariosEnMySQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ControladorVoluntario {
    private final IniciarSesionVoluntario iniciarSesion;
    private final ObtenerPublicacionesMascotaEnAdopcion obtenerPublicacionesMascotaEnAdopcion;
    private final AprobarPublicacion aprobarPublicacion;

    public ControladorVoluntario() {
        this.iniciarSesion = new IniciarSesionVoluntario(new VoluntariosEnMySQL());
        this.obtenerPublicacionesMascotaEnAdopcion = new ObtenerPublicacionesMascotaEnAdopcion(new OrganizacionesEnMySQL());
        this.aprobarPublicacion = new AprobarPublicacion(new PublicacionesEnMySQL(), new VoluntariosEnMySQL());
    }

    @PostMapping("voluntarios/autenticar")
    public LoginResponse iniciarSesion(@RequestBody SolicitudIniciarSesion solicitud, HttpServletResponse response) {
        try {
            Voluntario voluntario = iniciarSesion.ejecutar(solicitud.nombreUsuario(), solicitud.contrasenia());
            ;
            SesionManager sesionManager = SesionManager.getInstance();
            String idSesion = sesionManager.crear("voluntario", voluntario);
            System.out.println(idSesion);
            return new LoginResponse(idSesion);
        } catch (UsuarioNoEncontradoException e) {
            return new LoginResponse("-1");
        }
    }

    @GetMapping("organizacion/panelVoluntario")
    public ResponseEntity acceder(@RequestHeader("Authorization") String idVoluntario) {
        Voluntario voluntario = this.obtenerVolSesionManager(idVoluntario);

        List<RespuestaPublicacionMascotaEncontradaID> publicacionMascotaEncontradas = voluntario.getPublicacionesMascotaEncontradaPendientes().stream()
                .map(unaPublicacion -> new RespuestaPublicacionMascotaEncontradaID(unaPublicacion.getId(), unaPublicacion.getUbicacionMascota().getLatitud(),
                        unaPublicacion.getUbicacionMascota().getLongitud(), unaPublicacion.getEstadoMascota(), unaPublicacion.getFotosMascota()))
                .collect(Collectors.toList());

        List<RespuestaPublicacionMascotaEnAdopcionID> publicacionMascotaEnAdopciones = voluntario.getPublicacionesMascotaEnAdopcionPendientes()
                .stream().map(unaPublicacion -> new RespuestaPublicacionMascotaEnAdopcionID(unaPublicacion.getMascota().getNombre(),
                        unaPublicacion.getMascota().getDescripcionFisica(), unaPublicacion.getMascota().getFotos(), unaPublicacion.getId()))
                .collect(java.util.stream.Collectors.toList());


        RespuestaPublicaciones respuestaPublicaciones = new RespuestaPublicaciones(publicacionMascotaEncontradas, publicacionMascotaEnAdopciones);
        return ResponseEntity.status(200).body(respuestaPublicaciones);
    }

    @PostMapping("organizacion/publicaciones/aceptar")
    public ResponseEntity aprobarPublicacion(@RequestHeader("Authorization") String idVoluntario,
                                             @RequestBody SolicitudAceptarPublicacion solicitud)
    {
        Voluntario unVoluntario = this.obtenerVolSesionManager(idVoluntario);

        aprobarPublicacion.ejecutar(solicitud.getId(),unVoluntario.getUsuario());
        return ResponseEntity.status(200).build();
    }

    private Voluntario obtenerVolSesionManager(String idVoluntario) {
        SesionManager sesionManager = SesionManager.getInstance();
        Map<String, Object> unosDatos = sesionManager.obtenerAtributos(idVoluntario);
        return (Voluntario) unosDatos.get("voluntario");
    }

}
