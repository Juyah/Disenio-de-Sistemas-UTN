package com.utn.infraestructura.api.persona;

import com.utn.casodeuso.adoptante.QuererAdoptarMascota;
import com.utn.casodeuso.persona.AniadirUsuario;
import com.utn.casodeuso.persona.RegistrarPersona;
import com.utn.dominio.Personas;
import com.utn.dominio.Usuarios;
import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Sexo;
import com.utn.dominio.animal.Tamaño;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.persona.Contacto;
import com.utn.dominio.persona.Direccion;
import com.utn.dominio.persona.Documento;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.publicacion.Preferencia;
import com.utn.infraestructura.api.SesionManager;
import com.utn.infraestructura.persistencia.PersonasEnMySQL;
import com.utn.infraestructura.persistencia.UsuariosEnMySQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ControladorPersona {
    private final RegistrarPersona registrarPersona;
    private final QuererAdoptarMascota quererAdoptarMascota;
    private final AniadirUsuario aniadirUsuario;

    public ControladorPersona() {
        Personas personasEnMySQL = new PersonasEnMySQL();
        Usuarios usuariosEnMySQL = new UsuariosEnMySQL();
        this.quererAdoptarMascota = new QuererAdoptarMascota(personasEnMySQL);
        this.registrarPersona = new RegistrarPersona(personasEnMySQL, usuariosEnMySQL);
        this.aniadirUsuario = new AniadirUsuario(personasEnMySQL, usuariosEnMySQL);
    }


    @PostMapping("usuario/registroPersona")
    public ResponseEntity registroPersona(@RequestBody SolicitudRegistrarPersona solicitud) {


        Preferencia preferencia = new Preferencia(Sexo.buscar(solicitud.getPreferencia().getSexoAnimal()),
                Animal.buscar(solicitud.getPreferencia().getTipoAnimal()),
                Tamaño.buscar(solicitud.getPreferencia().getTamanioAnimal()));

        registrarPersona.ejecutar(solicitud.getNombreUsuario(), solicitud.getContactoPersonal(), solicitud.getFechaNacimiento(),
                solicitud.getDocumento(), new Direccion(solicitud.getLatitud(), solicitud.getLongitud()), preferencia,
                solicitud.getUnosContactos(), solicitud.getRadioHogares());

        return ResponseEntity.status(200).build();
    }

    @PostMapping("persona/registrarleUsuario")
    public ResponseEntity registrarleUsuario(@RequestBody SolicitudRegistrarleUsuario solicitud) {
        aniadirUsuario.ejectuar(solicitud.getNombreUsuario(),
                solicitud.getNumeroDocumento(), solicitud.getTipoDocumento());

        return ResponseEntity.status(200).build();
    }

    @GetMapping("persona/documento")
    public ResponseEntity obtenerDocumento(@RequestHeader("Authorization") String idSesion) {
        Usuario usuario = this.obtenerUsuarioSesionManager(idSesion);
        Persona persona = new PersonasEnMySQL().obtenerPorNombreDeUsuario(usuario.getUsuario());
        RespuestaDocumento documento = new RespuestaDocumento(persona.getDocumento().getNumero(), persona.getDocumento().getTipo());
        return ResponseEntity.status(200).body(documento);
    }

    private Usuario obtenerUsuarioSesionManager(String idSesion) {
        SesionManager sesionManager = SesionManager.getInstance();

        Map<String, Object> unosDatos = sesionManager.obtenerAtributos(idSesion);
        return (Usuario) unosDatos.get("usuario");
    }
}
