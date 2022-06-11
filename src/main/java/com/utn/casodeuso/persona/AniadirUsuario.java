package com.utn.casodeuso.persona;

import com.utn.dominio.Personas;
import com.utn.dominio.Usuarios;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.persona.TipoDocumento;

public class AniadirUsuario {

    private final Personas personas;
    private final Usuarios usuarios;

    public AniadirUsuario(Personas personas, Usuarios usuarios) {
        this.personas = personas;
        this.usuarios = usuarios;
    }

    public void ejectuar(String nombreUsuario, int numeroDocumento, TipoDocumento tipoDocumento) {
        Persona persona = personas.obtenerPorNumeroDocumento(numeroDocumento, tipoDocumento);
        Usuario usuario = usuarios.obtenerPorNombreUsuario(nombreUsuario);
        persona.setUsuario(usuario);

        personas.guardar(persona);
    }
}
