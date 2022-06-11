package com.utn.infraestructura.api.usuario.CientePesado;

import com.utn.casodeuso.rescatista.BuscarHogarTransito;
import com.utn.casodeuso.usuario.IniciarSesion;
import com.utn.casodeuso.usuario.Registrar;
import com.utn.dominio.*;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.excepcion.ContraseñaDebilException;
import com.utn.dominio.excepcion.UsuarioNoEncontradoException;
import com.utn.dominio.excepcion.UsuarioYaRegistradoException;
import com.utn.dominio.hogar.ValidacionHogar;
import com.utn.dominio.hogar.criterios.*;
import com.utn.infraestructura.api.SesionManager;
import com.utn.infraestructura.api.usuario.LoginResponse;
import com.utn.infraestructura.api.usuario.SolicitudBuscarHogarTransito;
import com.utn.infraestructura.api.usuario.SolicitudIniciarSesion;
import com.utn.infraestructura.api.usuario.SolicitudRegistroUsuario;
import com.utn.infraestructura.hogares.Hogar;
import com.utn.infraestructura.hogares.ServicioHogares;
import com.utn.infraestructura.persistencia.MascotasEnMySQL;
import com.utn.infraestructura.persistencia.OrganizacionesEnMySQL;
import com.utn.infraestructura.persistencia.PersonasEnMySQL;
import com.utn.infraestructura.persistencia.UsuariosEnMySQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ControladorUsuarioCP {

    private final IniciarSesion iniciarSesion;
    private final Registrar registrar;
    private final BuscarHogarTransito buscarHogarTransito;

    public ControladorUsuarioCP() {
        Usuarios usuariosEnMySQL = new UsuariosEnMySQL();
        Personas personasEnMySQL = new PersonasEnMySQL();
        Mascotas mascotasEnMySQL = new MascotasEnMySQL();
        Hogares hogares = new ServicioHogares();

        List<ValidacionHogar> validaciones = new ArrayList<ValidacionHogar>() {{
            add(new CapacidadDisponible());
            add(new Cercania());
            add(new CumpleCaracteristicas());
            add(new TamañoMascota());
            add(new TipoAnimal());
        }};

        this.iniciarSesion = new IniciarSesion(usuariosEnMySQL);
        this.registrar = new Registrar(usuariosEnMySQL);
        this.buscarHogarTransito = new BuscarHogarTransito(personasEnMySQL, mascotasEnMySQL, hogares, validaciones);
    }

    @PostMapping("usuarios/autenticar")
    public LoginResponse iniciarSesion(@RequestBody SolicitudIniciarSesion solicitud, HttpServletResponse response) {
        try {
            Usuario unUsuario = iniciarSesion.ejecutar(solicitud.nombreUsuario(), solicitud.contrasenia());
            SesionManager sesionManager = SesionManager.getInstance();
            String idSesion = sesionManager.crear("usuario", unUsuario);
            System.out.println(idSesion);
            return new LoginResponse(idSesion);
        } catch (UsuarioNoEncontradoException e) {
            return new LoginResponse("-1");
        }
    }

    @GetMapping("usuarios/desconectar")
    public ResponseEntity cerrarSesion(@RequestHeader("Authorization") String idSesion) {

        SesionManager sesionManager = SesionManager.getInstance();
        sesionManager.eliminar(idSesion);

        return ResponseEntity.status(200).build();
    }


    @PostMapping("registrar/usuario")
    public ResponseEntity registrarUsuario(@RequestBody SolicitudRegistroUsuario solicitud) {
        try {
            registrar.ejecutar(solicitud.getNombreUsuario(), solicitud.getContrasenia());

        } catch (ContraseñaDebilException e) {
            return ResponseEntity.status(400).build();
        } catch (UsuarioYaRegistradoException e) {
            return ResponseEntity.status(404).body("Usuario ya registrado");
        }
        return ResponseEntity.status(200).build();
    }
}
