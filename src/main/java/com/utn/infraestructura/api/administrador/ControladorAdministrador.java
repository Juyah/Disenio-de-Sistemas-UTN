package com.utn.infraestructura.api.administrador;

import com.utn.casodeuso.administrador.ActualizarAdministradores;
import com.utn.casodeuso.administrador.ActualizarCaracteristicas;
import com.utn.casodeuso.administrador.ActualizarDetalleFotos;
import com.utn.casodeuso.administrador.IniciarSesionAdmin;
import com.utn.dominio.Administradores;
import com.utn.dominio.excepcion.UsuarioNoEncontradoException;
import com.utn.dominio.organizacion.Administrador;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.infraestructura.api.SesionManager;
import com.utn.infraestructura.api.usuario.LoginResponse;
import com.utn.infraestructura.api.usuario.SolicitudIniciarSesion;
import com.utn.infraestructura.persistencia.AdministradoresEnMySQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class ControladorAdministrador {


    private final IniciarSesionAdmin iniciarSesion;
    private final ActualizarAdministradores actualizarAdministrador;
    private final ActualizarCaracteristicas actualizarCaracteristicas;
    private final ActualizarDetalleFotos actualizarDetallesFotos;


    public ControladorAdministrador() {
        Administradores administradoresEnMySQL = new AdministradoresEnMySQL();
        this.iniciarSesion = new IniciarSesionAdmin(administradoresEnMySQL);
        this.actualizarAdministrador = new ActualizarAdministradores(administradoresEnMySQL);
        this.actualizarCaracteristicas = new ActualizarCaracteristicas(administradoresEnMySQL);
        this.actualizarDetallesFotos = new ActualizarDetalleFotos(administradoresEnMySQL);
    }

    @GetMapping("organizacion/panelAdministracion")
    public ResponseEntity acceder(@RequestHeader("Authorization") String idAdmin) {
        Administrador administrador = this.obtenerAdminSesionManager(idAdmin);
        Organizacion organizacion = administrador.getOrganizacion();

        RespuestaAccesoAdmin unaRespuesta = new RespuestaAccesoAdmin();
        List<String> usuarioAdmins = organizacion.getAdministradores().stream().map(Administrador::getUsuario).collect(Collectors.toList());
        List<String> caracteristicas = organizacion.getCaracteristicas();

        unaRespuesta.setUsuariosAdministradores(usuarioAdmins);
        unaRespuesta.setCalidadFoto(organizacion.getCalidadFoto());
        unaRespuesta.setTamanioFoto(organizacion.getTamañoFoto());
        unaRespuesta.setCaracteristicas(caracteristicas);

        return ResponseEntity.status(200).body(unaRespuesta);
    }

    @PostMapping("organizacion/panelAdministracion/actualizarCaracteristicas")
    public ResponseEntity actualizarCaracteristicas(@RequestHeader("Authorization") String idAdmin, @RequestBody SolicitudActualizarCaracteristicas solicitud) {
        Administrador administradorRealTime = this.obtenerAdminSesionManager(idAdmin);
        actualizarCaracteristicas.ejecutar(administradorRealTime.getUsuario(), solicitud.getNuevaCaracteristica());
        return ResponseEntity.status(200).build();
    }

    @PostMapping("organizacion/panelAdministracion/actualizarDetalleFotos")
    public ResponseEntity actualizarDetalleFotos(@RequestHeader("Authorization") String idAdmin, @RequestBody SolicitudActualizarDetalleFotos solicitud) {
        Administrador administradorRealTime = this.obtenerAdminSesionManager(idAdmin);

        actualizarDetallesFotos.ejecutar(administradorRealTime.getUsuario(),solicitud.getTamanioFoto(),
                solicitud.getCalidadFoto());

        return ResponseEntity.status(200).build();
    }

    @PostMapping("organizacion/panelAdministracion/actualizarAdministradores")
    public ResponseEntity actualizarAdministradores(@RequestHeader("Authorization") String idAdmin, @RequestBody SolicitudActualizarAdministradores solicitud) {
        Administrador administradorRealTime = this.obtenerAdminSesionManager(idAdmin);
        actualizarAdministrador.ejecutar(administradorRealTime.getUsuario(), solicitud.getAdminNuevo(), solicitud.getContrasenia());

        return ResponseEntity.status(200).build();
    }

    @PostMapping("administradores/autenticar")
    public LoginResponse iniciarSesion(@RequestBody SolicitudIniciarSesion solicitud, HttpServletResponse response) {
        try {
            Administrador administrador = iniciarSesion.ejecutar(solicitud.nombreUsuario(), solicitud.contrasenia());
            SesionManager sesionManager =  SesionManager.getInstance();
            String idSesion = sesionManager.crear("administrador",administrador);
            System.out.println(idSesion);
            return new LoginResponse(idSesion);
        }
        catch(UsuarioNoEncontradoException e) {
            return new LoginResponse("-1");
        }
    }

    private Administrador obtenerAdminSesionManager(String idAdmin) {
        SesionManager sesionManager = SesionManager.getInstance();
        Map<String, Object> unosDatos = sesionManager.obtenerAtributos(idAdmin);
        return (Administrador) unosDatos.get("administrador");
    }
}
