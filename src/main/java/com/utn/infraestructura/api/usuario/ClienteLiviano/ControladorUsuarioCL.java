package com.utn.infraestructura.api.usuario.ClienteLiviano;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.utn.casodeuso.usuario.IniciarSesion;

import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.excepcion.UsuarioNoEncontradoException;
import com.utn.infraestructura.api.SesionManager;
import com.utn.infraestructura.api.usuario.LoginResponse;
import com.utn.infraestructura.api.usuario.SolicitudIniciarSesion;
import com.utn.infraestructura.persistencia.UsuariosEnMySQL;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ControladorUsuarioCL
{
    private final IniciarSesion iniciarSesion;
    private final Handlebars handlebars;

    public ControladorUsuarioCL() {
        this.handlebars = new Handlebars();
        this.iniciarSesion = new IniciarSesion(new UsuariosEnMySQL());
    }

    @GetMapping(value = "login/ver" ,produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> obtenerVista() throws IOException
    {
        Template template = handlebars.compile("/templates/login");
        Map<String,Object> model = new HashMap<>();
        String html = template.apply(model);
        return ResponseEntity.status(200).body(html);
    }

    @PostMapping("login/autenticar")
    public LoginResponse iniciarSesion(@RequestBody SolicitudIniciarSesion solicitudIniciarSesion,
                                       HttpServletResponse response) {
        try {
            Usuario unUsuario = iniciarSesion.ejecutar(solicitudIniciarSesion.nombreUsuario(), solicitudIniciarSesion.contrasenia());
            SesionManager sesionManager =  SesionManager.getInstance();
            String idSesion = sesionManager.crear("usuario",unUsuario);
            System.out.println(idSesion);
            return new LoginResponse(idSesion);
        }
        catch(UsuarioNoEncontradoException e) {
            return new LoginResponse("-1");
        }
    }

}
