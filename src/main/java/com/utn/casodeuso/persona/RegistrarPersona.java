package com.utn.casodeuso.persona;

import com.utn.dominio.Personas;
import com.utn.dominio.Usuarios;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.notificacion.MedioDeComunicacion;
import com.utn.dominio.notificacion.estrategia.Email;
import com.utn.dominio.notificacion.estrategia.SMS;
import com.utn.dominio.notificacion.estrategia.WhatsApp;
import com.utn.dominio.persona.Contacto;
import com.utn.dominio.persona.Direccion;
import com.utn.dominio.persona.Documento;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.publicacion.Preferencia;
import com.utn.infraestructura.api.persona.DatosContacto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrarPersona {
    private final Personas personas;
    private final Usuarios usuarios;

    public RegistrarPersona(Personas personas, Usuarios usuarios) {
        this.personas = personas;
        this.usuarios = usuarios;
    }

    public void ejecutar(String nombreUsuario, DatosContacto contactoPersonal, LocalDate fechaNacimiento,
                         Documento documento, Direccion domicilio, Preferencia preferencia,
                         List<DatosContacto> contactos, int radioHogares) {
        Usuario usuario;
        if (nombreUsuario.isEmpty())
            usuario = null;
        else
            usuario = usuarios.obtenerPorNombreUsuario(nombreUsuario);

        Contacto contactoPer = this.datosAContacto(contactoPersonal);
        List<Contacto> otrosContactos = contactos.stream().map(this::datosAContacto).collect(Collectors.toList());
        Persona persona = new Persona(usuario, contactoPer, fechaNacimiento, documento, domicilio, preferencia, otrosContactos, radioHogares);

        personas.guardar(persona);
    }

    private Contacto datosAContacto(DatosContacto datos) {
        Contacto unContacto = new Contacto(datos.getNombre(), datos.getApellido(),
                datos.getTelefono(), datos.getEmail());
        List<MedioDeComunicacion> mediosComunicacion = this.obtenerMediosDeComunicacion(datos.getMediosComunicacion());
        unContacto.setMediosDeComunicacion(mediosComunicacion);
        return unContacto;
    }

    private List<MedioDeComunicacion> obtenerMediosDeComunicacion(List<String> mediosPreferidos) {
        return mediosPreferidos.stream().map(this::resolverMedioComunicacion)
                .collect(Collectors.toList());
    }

    private MedioDeComunicacion resolverMedioComunicacion(String unMedio) {
        switch (unMedio) {
            case "WhatsApp":
                return new WhatsApp(true);
            case "Email":
                return new Email(true);
            case "SMS":
                return new SMS(true);
            default:
                return null;
        }
    }
}
