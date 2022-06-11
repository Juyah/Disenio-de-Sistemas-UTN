package com.utn.dominio;

import com.utn.dominio.excepcion.CredencialesInvalidasException;

import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.excepcion.ContraseñaCortaException;
import com.utn.dominio.excepcion.ContraseñaDebilException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static com.utn.dominio.FixtureUsuario.*;

public class PruebaUsuario {

    @Test
    public void un_usuario_con_contraseña_segura_se_genera_correctamente() {
        Usuario usuario = new Usuario(nombreUsuario, contraseñaSegura);
        Assertions.assertEquals(usuario.getUsuario(), nombreUsuario);
    }

    @Test
    public void un_usuario_con_contraseña_debil_lanza_una_excepcion() {
        Assertions.assertThrows(ContraseñaDebilException.class,
            () -> new Usuario(nombreUsuario, contraseñaDebil));
    }

    @Test
    public void un_usuario_con_contraseña_corta_lanza_una_excepcion() {
        Assertions.assertThrows(ContraseñaCortaException.class,
            () -> new Usuario(nombreUsuario, contraseñaCorta));
    }

    @Test
    public void un_usuario_inicia_sesion_correctamente() {
        //Dado un usuario generado previamente en el sistema
        Usuario usuario = new Usuario(nombreUsuario, contraseñaSegura);
        //Cuando intento iniciar sesion con credenciales validas
        String nombreUsuarioValido = "ccalvoromero";
        String contraseñaValida = "Cont321Ho@13";
        usuario.iniciarSesion(nombreUsuarioValido, contraseñaValida);
        //Entonces el usuario se loguea correctamente
    }

    @Test
    public void un_usuario_intenta_iniciar_sesion_con_contraseña_invalida() {
    //Dado un usuario generado previamente en el sistema
        Usuario usuario = new Usuario(nombreUsuario, contraseñaSegura);
        //Cuando intento iniciar sesion con credenciales validas
        String nombreUsuarioValido = "ccalvoromero";
        String contraseñaInvalida = "cualquiercosa";
        //Entonces el usuario se loguea correctamente
        Assertions.assertThrows(CredencialesInvalidasException.class,
            () -> usuario.iniciarSesion(nombreUsuarioValido, contraseñaInvalida));
    }

}
