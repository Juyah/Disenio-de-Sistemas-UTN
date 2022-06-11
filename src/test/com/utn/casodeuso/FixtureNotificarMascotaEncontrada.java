package com.utn.casodeuso;

import com.utn.dominio.animal.Sexo;
import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Mascota;

import com.utn.dominio.animal.Tamaño;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.persona.*;
import com.utn.dominio.notificacion.estrategia.SMS;
import com.utn.dominio.notificacion.estrategia.Email;
import com.utn.dominio.notificacion.estrategia.WhatsApp;

import com.utn.dominio.Notificador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixtureNotificarMascotaEncontrada {

    protected static final String nombreMascotaEncontradaExistente = "Ayudante de Santa";
    protected static final String nombreMascotaEncontradaNoExistente = "Beneto";
    private static final String nombreMascota = "Ayudante de Santa";
    private static final Animal animal = Animal.PERRO;
    private static final String apodo = "Huesos";
    private static final int edad = 10;
    private static final Sexo sexo = Sexo.MACHO;
    private static final Tamaño tamaño = Tamaño.CHICO;
    private static final String descripcionFisica = "Chiquito y dormilon";
    private static final List<String> fotos = new ArrayList<>();
    private static final Map<String, String> caracteristicasMascota = new HashMap<String, String>() {{
        put("castrado", "si");
        put("vegano", "si");
    }};

    protected static final int numeroDocumentoDueño = 11111;
    protected static final TipoDocumento tipoDocumentoDueño = TipoDocumento.DNI;
    private static final String nombreDueño = "Camila";
    private static final String apellidoDueño = "Berro";
    private static final LocalDate fechaNacimientoDueño = LocalDate.now();
    private static final Direccion direccionDueño = new Direccion(-34.58805543938273, -58.39709555890073);
    private static final String emailDueño = "ccalvoromero@gmail.com";
    protected static final String numeroDueño = "+5491134561101";
    private static final Usuario usuarioDueño = new Usuario("ccalvoromero", "estaEsUnaContraseña21");

    private static final String nombreContactoDueño = "Ayax";
    private static final String apellidoContactoDueño = "Santos";
    private static final String emailContactoDueño = "ccalvoromero@gmail.com";
    private static final String numeroContactoDueño = "+5491123961279";

    private static final boolean esPreferido = true;
    private static final boolean noEsPreferido = false;

    protected static final int numeroDocumentoRescatista = 22222;
    protected static final TipoDocumento tipoDocumentoRescatista = TipoDocumento.DNI;
    private static final String nombreRescatista = "Celeste";
    private static final String apellidoRescatista = "Fernandez";
    private static final LocalDate fechaNacimientoRescatista = LocalDate.now();
    private static final Direccion direccionRescatista = new Direccion(-34.58805543938273, -58.39709555890073);
    protected static final String numeroSMSRescatista = "+12252247262";
    protected static final String numeroWhatsappRescatista = "+14155238886";
    private static final String emailRescatista = "celestefernandez@gmail.com";
    private static final Usuario usuarioRescatista = new Usuario("celeslvp", "otraContraseñaValida21");

    private static final String nombreContactoRescatista = "Juan";
    private static final String apellidoContactoRescatista = "Yarbuh";
    private static final String emailContactoRescatista = "juani@gmail.com";
    private static final String numeroContactoRescatista = "+5491173619611";

    private static final TipoDocumento tipoDocumento = TipoDocumento.DNI;
    public static final int radioHogares = 10;

    protected static String mensajeMascotaEncontrada = "";
    protected static final String asuntoMascotaEncontrada = "[Importante] Rescate de Patitas";

    public static Mascota mascota;
    protected static Persona personaDueño;
    public static Persona personaRescatista;

    private static Contacto contactoDueño;
    private static Contacto contactoRescatista;
    private static Contacto otroContactoDueño;
    private static Contacto otroContactoRescatista;

    private static Documento documentoDueño;
    private static Documento documentoRescatista;

    private static Notificador sms;
    private static Notificador email;
    private static Notificador whatsApp;

    protected static void inicializarEstrategiasDeNotificacion(Notificador notificador) {
        email = notificador;
        sms = notificador;
        whatsApp = notificador;
    }

    public static void inicializarMascota() {
        mascota = crearMascota(nombreMascota, animal, apodo, edad, sexo, tamaño, descripcionFisica, fotos, caracteristicasMascota);
    }

    protected static void inicializarDueño() {
        otroContactoDueño = crearContacto(nombreContactoDueño, apellidoContactoDueño, numeroContactoDueño, emailContactoDueño);
        otroContactoDueño.añadirMedioDeComunicacion(new SMS( noEsPreferido));
        otroContactoDueño.añadirMedioDeComunicacion(new Email(esPreferido));
        otroContactoDueño.añadirMedioDeComunicacion(new WhatsApp(noEsPreferido));
        documentoDueño = crearDocumento(tipoDocumento, numeroDocumentoDueño);
        contactoDueño = crearContacto(nombreDueño, apellidoDueño, numeroDueño, emailDueño);
        contactoDueño.añadirMedioDeComunicacion(new SMS(esPreferido));
        personaDueño = crearPersona(contactoDueño, fechaNacimientoDueño, documentoDueño,
                direccionDueño, otroContactoDueño, usuarioDueño, radioHogares);
        personaDueño.añadirMascota(mascota);
    }

    public static void inicializarRescatista() {
        otroContactoRescatista = crearContacto(nombreContactoRescatista, apellidoContactoRescatista,
                numeroContactoRescatista, emailContactoRescatista);
        documentoRescatista = crearDocumento(tipoDocumento, numeroDocumentoRescatista);
        contactoRescatista = crearContacto(nombreRescatista, apellidoRescatista, numeroSMSRescatista, emailRescatista);
        personaRescatista = crearPersona(contactoRescatista, fechaNacimientoRescatista, documentoRescatista,
                direccionRescatista, otroContactoRescatista, usuarioRescatista, radioHogares);
        mensajeMascotaEncontrada = mensajeMascotaEncontrada(personaRescatista, mascota);
    }

    private static Mascota crearMascota(
            String nombre, Animal animal, String apodo, int edad, Sexo sexo, Tamaño tamaño,
            String descripcionFisica, List<String> fotos, Map<String, String> caracteristicas) {
        Mascota mascota = new Mascota(nombre, apodo, edad, animal, sexo, tamaño, descripcionFisica);
        fotos.forEach(unaFoto -> mascota.añadirFoto(unaFoto));
        caracteristicas.forEach((unaCaracteristica, unaRespuesta) -> mascota.añadirCaracteristica(unaCaracteristica, unaRespuesta));

        return mascota;
    }

    private static Persona crearPersona(
            Contacto contacto, LocalDate fechaNacimiento, Documento documento,
            Direccion direccion, Contacto otroContacto, Usuario usuario, int radioHogares) {
        return new Persona(usuario, contacto, fechaNacimiento, documento, direccion, null, new ArrayList<Contacto>(){{add(otroContacto);}}, radioHogares);
    }

    private static Documento crearDocumento(TipoDocumento tipo, Integer numero) {
        return new Documento(tipo, numero);
    }

    private static Contacto crearContacto(String nombre, String apellido, String telefono, String email) {
        return new Contacto(nombre, apellido, telefono, email);
    }

    private static String mensajeMascotaEncontrada(Persona personaRescatista, Mascota mascota) {
        return "Hola, soy " + personaRescatista.getNombre() + " y encontré a " + mascota.getNombre() + ". " +
                "Mi número es " + personaRescatista.getTelefono() + " y mi email es " + personaRescatista.getEmail();
    }

}