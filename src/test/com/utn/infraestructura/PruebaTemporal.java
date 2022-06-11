package com.utn.infraestructura;

import com.utn.dominio.*;
import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.animal.Sexo;
import com.utn.dominio.animal.Tamaño;
import com.utn.dominio.autenticacion.Usuario;
import com.utn.dominio.notificacion.estrategia.Email;
import com.utn.dominio.notificacion.estrategia.SMS;
import com.utn.dominio.notificacion.estrategia.WhatsApp;
import com.utn.dominio.organizacion.*;
import com.utn.dominio.persona.*;
import com.utn.dominio.publicacion.Preferencia;
import com.utn.dominio.publicacion.PublicacionBusquedaAdopcion;
import com.utn.dominio.publicacion.PublicacionMascotaEnAdopcion;
import com.utn.dominio.publicacion.PublicacionMascotaEncontrada;
import com.utn.infraestructura.persistencia.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class PruebaTemporal {

    private final Voluntarios voluntarios = new VoluntariosEnMySQL();
    private final Personas personas = new PersonasEnMySQL();
    private final Organizaciones organizaciones = new OrganizacionesEnMySQL();

    //@BeforeEach
    //void inicializar() { voluntarios = }

    @Test
    public void se_rescata_voluntario_con_sus_usuarios() {
        //Voluntario voluntario = voluntarios.obtenerPorNumeroDNI(5488759);
        Voluntario voluntario = voluntarios.obtenerPorNombreUsuario("pepe");
        System.out.println(" Mi usuario es: " + voluntario.getUsuario() + " y contraseña: " + voluntario.getContrasenia());
    }

    @Test
    public void se_persiste_voluntario_en_db() {
        Organizacion organizacion = new Organizacion("nombre", new Direccion(3, 3), TamañoFoto.GRANDE, CalidadFoto.BAJA);
        Voluntario voluntario = new Voluntario("pepe", "hola1234", organizacion);

        organizacion.añadirVoluntario(voluntario);

        voluntarios.guardar(voluntario);
    }

    @Test
    public void se_rescata_persona_de_db() {
        Persona persona = personas.obtenerPorNumeroDocumento(386514987, TipoDocumento.DNI);

        System.out.println("Hola" + persona);

    }

    @Test
    public void se_rescata_usuario_de_db() {
        Usuarios usuarios = new UsuariosEnMySQL();
        Usuario usuario = usuarios.obtenerPorNombreUsuario("pepebavutti");
        System.out.println("Hola" + usuario);
    }

    @Test
    public void se_rescata_admin_de_db() {
        Administradores administradores = new AdministradoresEnMySQL();
        Administrador administrador = administradores.obtenerPorNombreUsuario("pepeOwner");
        System.out.println("Hola " + administrador.getUsuario());
    }

    @Test
    public void se_persiste_persona_en_db() {
        OrganizacionesEnMySQL per = new OrganizacionesEnMySQL();
        Contacto contactoTest = new Contacto("Celes", "", "+549118755-7845", "ebavutti@gmail.com");
        LocalDate nacimientoTest = LocalDate.of(1990, 4, 27);
        Documento documentoTest = new Documento(TipoDocumento.DNI, 38554127);
        Direccion domicilioTest = new Direccion(1742.38, 2394.2);
        Contacto otroContactoTest = new Contacto("Isabela", "Ferriera", "+549117855-4121", "iferriera@gmail.com");
        Usuario usuarioTest = new Usuario("pepebavutti", "noSeQuePoner...");
        Preferencia preferenciaTest = new Preferencia(Sexo.MACHO, Animal.PERRO, Tamaño.MEDIANO);

        Direccion domicilioOrgTest = new Direccion(5447.358, 5648.74);

        Organizacion organizacionTest = new Organizacion("Patitas Juguetonas", domicilioOrgTest, TamañoFoto.GRANDE, CalidadFoto.BAJA);
        Voluntario voluntarioTest = new Voluntario("volOrg", "324ae41gg", organizacionTest);
        Administrador administradorTest = new Administrador("adminOrg", "32ad965min", organizacionTest);

        organizacionTest.añadirPreguntaAdopcion("Tu animal tiene vacunas?");
        organizacionTest.añadirPreguntaAdopcion("Tu animal es jugeton?");
        organizacionTest.añadirVoluntario(voluntarioTest);
        Mascota mascotaTest = new Mascota("Solange", "Sol", 5, Animal.PERRO, Sexo.HEMBRA, Tamaño.GRANDE,
                "Soy muy linda y tierna");

        mascotaTest.añadirFoto("foto1.png");
        mascotaTest.añadirFoto("foto2.png");
        mascotaTest.añadirCaracteristica("Vacuna", "Si");
        mascotaTest.añadirCaracteristica("Parasitos", "No");

        Mascota otraMascotaTest = new Mascota("Pepa", "Pepita", 1, Animal.GATO, Sexo.HEMBRA, Tamaño.CHICO,
                "Me gusta jugar con lana");

        otraMascotaTest.añadirFoto("foto1.jpg");
        otraMascotaTest.añadirFoto("foto2.jpg");
        otraMascotaTest.añadirCaracteristica("Parasitos", "No");
        otraMascotaTest.añadirCaracteristica("Garras", "Si");
        otraMascotaTest.añadirCaracteristica("Perdida de Pelo", "No");

        Persona personaTest = new Persona(usuarioTest, contactoTest, nacimientoTest, documentoTest, domicilioTest, preferenciaTest, new ArrayList<Contacto>() {{
            add(otroContactoTest);
        }}, 32);
        organizacionTest.añadirPersona(personaTest);
        personaTest.añadirMascota(mascotaTest);
        personaTest.añadirMascota(otraMascotaTest);

        organizacionTest.añadirPublicacionBusquedaAdopcion(new PublicacionBusquedaAdopcion(personaTest, new ArrayList<String>() {{
            add("Hola");
            add("Adios");
        }}));
        organizacionTest.añadirPublicacionMascotaEnAdopcion(new PublicacionMascotaEnAdopcion(personaTest, mascotaTest, new HashMap<String, String>() {{
            put("Es fuerte?", "Si");
            put("Dureme mucho?", "No");
        }}));
        organizacionTest.añadirPublicacionMascotaEncontrada(new PublicacionMascotaEncontrada(personaTest, new Direccion(20, 23), "Todo okey"));

        organizacionTest.añadirCaracteristica("Perrito gordito");
        organizacionTest.añadirPreguntaAdopcion("Cuanto pesa??");
        organizacionTest.añadirPreguntaQuieroAdoptar("Tiene patio??");
        personaTest.setPreferencia(new Preferencia(Sexo.HEMBRA, Animal.PERRO, Tamaño.CHICO));

        organizacionTest.añadirAdministrador(administradorTest);

        personaTest.setEsAdoptante(false);

        contactoTest.añadirMedioDeComunicacion(new SMS(false));
        contactoTest.añadirMedioDeComunicacion(new Email(true));
        contactoTest.añadirMedioDeComunicacion(new WhatsApp(true));
        otroContactoTest.añadirMedioDeComunicacion(new WhatsApp(true));
        otroContactoTest.añadirMedioDeComunicacion(new Email(false));

        per.guardar(organizacionTest);
    }

    @Test
    public void se_persiste_organizacion_en_db() {
        OrganizacionesEnMySQL per = new OrganizacionesEnMySQL();

        Direccion domicilioOrgTest = new Direccion(5447.358, 5648.74);
        Organizacion organizacionTest = new Organizacion("Peluditos al rescate", domicilioOrgTest, TamañoFoto.GRANDE, CalidadFoto.BAJA);
        Organizacion organizacionTest2 = new Organizacion("Animalitos en espera", domicilioOrgTest, TamañoFoto.GRANDE, CalidadFoto.BAJA);


        per.guardar(organizacionTest);
        per.guardar(organizacionTest2);
    }

    @Test
    public void se_actualiza_persona() {
        Persona persona = personas.obtenerPorNumeroDocumento(38554127, TipoDocumento.DNI);
        persona.getUsuario().setUsuario("InserteUsuarioGenerico");
        persona.setPreferencia(new Preferencia(Sexo.MACHO, Animal.GATO, Tamaño.GRANDE));
        persona.setEsAdoptante(true);
        persona.añadirMascota(new Mascota("nombre", "String apodo", 25, Animal.PERRO, Sexo.HEMBRA, Tamaño.GRANDE, " String descripcionFisica"));

        personas.guardar(persona);
    }

    @Test
    public void llenar_base_de_datos_con_datos_de_prueba() {
        String descripcion = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur mollis ipsum sem, non imperdiet diam commodo non. Cras dui eros, facilisis in luctus ut, cursus a dui. Curabitur sagittis tincidunt augue, in aliquam diam dictum in.";
        Contacto con1 = new Contacto("Juan", "Perez", "+5491175848544", "juanperez@gmail.com");
        Usuario usr1 = new Usuario("juanperez", "per@31Juan");
        Preferencia pref1 = new Preferencia(Sexo.HEMBRA, Animal.PERRO, Tamaño.CHICO);
        Persona per1 = new Persona(usr1, con1, LocalDate.of(1990, 4, 27), new Documento(TipoDocumento.DNI, 38456457), new Direccion(-34.60342185145848, -58.41012996306558), pref1, new ArrayList<Contacto>() {{
            add(new Contacto("Sofia", "Perez", "+5491174148879", "sofiaperez@gmail.com"));
        }}, 10);
        Mascota masc1 = new Mascota("Piru", "Obi", 5, Animal.PERRO, Sexo.HEMBRA, Tamaño.GRANDE, descripcion);
        Mascota masc2 = new Mascota("Chispita", "Chispi", 12, Animal.PERRO, Sexo.MACHO, Tamaño.CHICO, descripcion);
        per1.añadirMascota(masc1);
        per1.añadirMascota(masc2);

        Contacto con2 = new Contacto("Esteban", "Sanchez", "+549111243321", "estebansanchez@gmail.com");
        Usuario usr2 = new Usuario("estebanSanchez", "est.ez231");
        Preferencia pref2 = new Preferencia(Sexo.MACHO, Animal.PERRO, Tamaño.GRANDE);
        Persona per2 = new Persona(usr2, con2, LocalDate.of(1967, 11, 21), new Documento(TipoDocumento.LICENCIA, 12454234), new Direccion(-34.62247272569366, -58.44569802091699), pref2, new ArrayList<Contacto>() {{
            add(new Contacto("Ricardo", "Sanchez", "+5491178554123", "ricsan@gmail.com"));
        }}, 1);
        Mascota masc8 = new Mascota("Rocco", "Roki", 21, Animal.PERRO, Sexo.HEMBRA, Tamaño.GRANDE, descripcion);
        per2.añadirMascota(masc8);

        Contacto con3 = new Contacto("Jose", "Diaz", "+54911000000", "josediaz@gmail.com");
        Usuario usr3 = new Usuario("josaz", "josesito31-42");
        Preferencia pref3 = new Preferencia(Sexo.HEMBRA, Animal.GATO, Tamaño.MEDIANO);
        Persona per3 = new Persona(usr3, con3, LocalDate.of(1987, 6, 12), new Documento(TipoDocumento.DNI, 24577458), new Direccion(-34.58582050299011, -58.479337073041734), pref3, new ArrayList<Contacto>() {{
            add(new Contacto("Marta", "diaz", "+5491178445689", "martitadiaz@gmail.com"));
        }}, 3);
        Mascota masc4 = new Mascota("Wanda", "Wandi", 5, Animal.PERRO, Sexo.HEMBRA, Tamaño.GRANDE, descripcion);
        Mascota masc5 = new Mascota("Brillito", "Brilli", 99, Animal.GATO, Sexo.HEMBRA, Tamaño.MEDIANO, descripcion);
        Mascota masc6 = new Mascota("Hercules", "Hercu", 1, Animal.PERRO, Sexo.MACHO, Tamaño.CHICO, descripcion);
        Mascota masc7 = new Mascota("Thor", "Thori", 3, Animal.GATO, Sexo.MACHO, Tamaño.GRANDE, descripcion);
        per3.añadirMascota(masc4);
        per3.añadirMascota(masc5);
        per3.añadirMascota(masc6);
        per3.añadirMascota(masc7);

        Contacto con4 = new Contacto("Karen", "Lopez", "+5491121564566", "karenlopez@gmail.com");
        Usuario usr4 = new Usuario("karenlopez", "karendelbarrio@42");
        Preferencia pref4 = new Preferencia(Sexo.MACHO, Animal.GATO, Tamaño.GRANDE);
        Persona per4 = new Persona(usr4, con4, LocalDate.of(1970, 2, 1), new Documento(TipoDocumento.DNI, 42544587), new Direccion(-34.613379284716316, -58.368000742465895), pref4, new ArrayList<Contacto>() {{
            add(new Contacto("Lucas", "Perez", "+5491174148879", "lucasperez@gmail.com"));
        }}, 7);
        Mascota masc3 = new Mascota("Luna", "Luni", 7, Animal.GATO, Sexo.HEMBRA, Tamaño.MEDIANO, descripcion);
        per4.añadirMascota(masc3);

        Contacto con5 = new Contacto("Marta", "Bavutti", "+5491145122457", "martitabavu@gmail.com");
        Usuario usr5 = new Usuario("martab", "Bavutti21%12");
        Preferencia pref5 = new Preferencia(Sexo.MACHO, Animal.PERRO, Tamaño.CHICO);
        Persona per5 = new Persona(usr5, con5, LocalDate.of(2002, 4, 27), new Documento(TipoDocumento.PASAPORTE, 134451245), new Direccion(-34.585521050311804, -58.406342268765385), pref5, new ArrayList<Contacto>() {{
            add(new Contacto("Peppe", "Pig", "+5491113547891", "peppeelcerdo@gmail.com"));
        }}, 28);

        Contacto con6 = new Contacto("Lionel", "Messi", "+5491178874569", "traemelacopa@gmail.com");
        Usuario usr6 = new Usuario("lioeluno", "messi-10-barca");
        Preferencia pref6 = new Preferencia(Sexo.HEMBRA, Animal.GATO, Tamaño.CHICO);
        Persona per6 = new Persona(usr6, con6, LocalDate.of(1921, 1, 1), new Documento(TipoDocumento.DNI, 23545471), new Direccion(-34.63022913639562, -58.47919855613238), pref6, new ArrayList<Contacto>() {{
            add(new Contacto("Diego", "Maradona", "+5491147887542", "eldiegote10@gmail.com"));
        }}, 100);

        Contacto con7 = new Contacto("Kun", "Aguero", "+5491148678654", "elkun@gmail.com");
        Usuario usr7 = new Usuario("kunaguero", "457elkun@42");
        Preferencia pref7 = new Preferencia(Sexo.MACHO, Animal.PERRO, Tamaño.GRANDE);
        Persona per7 = new Persona(usr7, con7, LocalDate.of(1945, 2, 11), new Documento(TipoDocumento.LICENCIA, 35454748), new Direccion(-34.65607282734608, -58.623932684980254), pref7, new ArrayList<Contacto>() {{
            add(new Contacto("Lionel", "Messi", "+5491178874569", "traemelacopa@gmail.com"));
        }}, 165);

        Contacto con8 = new Contacto("Celeste", "Challen", "+5491178774112", "celestecn@gmail.com");
        Usuario usr8 = new Usuario("celesrubi", "rubi99celes@");
        Preferencia pref8 = new Preferencia(Sexo.MACHO, Animal.PERRO, Tamaño.MEDIANO);
        Persona per8 = new Persona(usr8, con8, LocalDate.of(1998, 1, 13), new Documento(TipoDocumento.PASAPORTE, 147421243), new Direccion(-34.75305095133177, -58.392638979689174), pref8, new ArrayList<Contacto>() {{
            add(new Contacto("Pepe", "Rubino", "+5491178745521", "peperubino@gmail.com"));
        }}, 79);


        con1.añadirMedioDeComunicacion(new SMS(false));
        con1.añadirMedioDeComunicacion(new WhatsApp(true));
        con2.añadirMedioDeComunicacion(new Email(true));
        con3.añadirMedioDeComunicacion(new WhatsApp(true));
        con4.añadirMedioDeComunicacion(new WhatsApp(true));
        con5.añadirMedioDeComunicacion(new Email(true));
        con5.añadirMedioDeComunicacion(new WhatsApp(false));
        con6.añadirMedioDeComunicacion(new SMS(true));
        con7.añadirMedioDeComunicacion(new Email(true));
        con8.añadirMedioDeComunicacion(new WhatsApp(true));
        con8.añadirMedioDeComunicacion(new Email(false));

        Organizacion org1 = new Organizacion("Patitas locas", new Direccion(-34.61297133588416, -58.42231495564473), TamañoFoto.GRANDE, CalidadFoto.BAJA);
        Organizacion org2 = new Organizacion("Mascotas en casa", new Direccion(-34.59623438899126, -58.43776828091517), TamañoFoto.GRANDE, CalidadFoto.ALTA);
        Organizacion org3 = new Organizacion("Dulce hogar", new Direccion(-34.620760937206086, -58.50204053938509), TamañoFoto.NORMAL, CalidadFoto.MEDIA);
        Organizacion org4 = new Organizacion("Peludones", new Direccion(-34.61350551320277, -58.370166508249945), TamañoFoto.NORMAL, CalidadFoto.ALTA);
        Organizacion org5 = new Organizacion("Tu compañerito", new Direccion(-34.65991349419335, -58.468461409429835), TamañoFoto.CHICA, CalidadFoto.BAJA);

        org1.añadirCaracteristica("Car1Org1");
        org1.añadirCaracteristica("Car2Org1");
        org1.añadirCaracteristica("Car1Org1");
        org2.añadirCaracteristica("Car1Org2");
        org2.añadirCaracteristica("Car2Org2");
        org2.añadirCaracteristica("Car3Org2");
        org3.añadirCaracteristica("Car1Org3");
        org3.añadirCaracteristica("Car2Org3");
        org4.añadirCaracteristica("Car1Org4");
        org5.añadirCaracteristica("Car1Org5");
        org5.añadirCaracteristica("Car2Org5");
        org5.añadirCaracteristica("Car3Org5");

        org1.añadirPreguntaAdopcion("PregAdop1Org1");
        org1.añadirPreguntaAdopcion("PregAdop2Org1");
        org1.añadirPreguntaAdopcion("PregAdop3Org1");
        org2.añadirPreguntaAdopcion("PregAdop1Org2");
        org2.añadirPreguntaAdopcion("PregAdop2Org2");
        org3.añadirPreguntaAdopcion("PregAdop1Org3");
        org3.añadirPreguntaAdopcion("PregAdop2Org3");
        org4.añadirPreguntaAdopcion("PregAdop1Org4");
        org5.añadirPreguntaAdopcion("PregAdop1Org5");
        org5.añadirPreguntaAdopcion("PregAdop2Org5");

        org1.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar1Org1");
        org1.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar2Org1");
        org1.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar3Org1");
        org2.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar1Org2");
        org2.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar2Org2");
        org3.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar1Org3");
        org3.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar2Org3");
        org4.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar1Org4");
        org5.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar1Org5");
        org5.añadirPreguntaQuieroAdoptar("PregQuieroAdoptar2Org5");

        org1.añadirMascota(masc1);
        org3.añadirMascota(masc2);
        org1.añadirMascota(masc3);
        org2.añadirMascota(masc8);
        org4.añadirMascota(masc4);
        org4.añadirMascota(masc5);
        org5.añadirMascota(masc6);
        org3.añadirMascota(masc7);

        PublicacionMascotaEncontrada pub1 = new PublicacionMascotaEncontrada(per8, new Direccion(-34.79269093516929, -58.480033806673624), "Todo ok");
        pub1.agregarFoto("foto1.png");
        pub1.agregarFoto("foto2.png");
        pub1.agregarFoto("foto3.png");
        PublicacionMascotaEncontrada pub2 = new PublicacionMascotaEncontrada(per8, new Direccion(-34.57408835867723, -58.72414063855492), "Patita lastimada");
        pub2.agregarFoto("foto1.png");
        pub2.agregarFoto("foto2.png");
        PublicacionMascotaEncontrada pub3 = new PublicacionMascotaEncontrada(per7, new Direccion(-34.73605837002798, -58.29174260923606), "Tiene parasitos");
        pub3.agregarFoto("foto1.png");
        pub3.agregarFoto("foto2.png");
        PublicacionMascotaEncontrada pub4 = new PublicacionMascotaEncontrada(per6, new Direccion(-34.750943705113215, -58.29095499898228), "Hambriento");
        pub4.agregarFoto("foto1.png");
        PublicacionMascotaEncontrada pub5 = new PublicacionMascotaEncontrada(per7, new Direccion(-34.47675454326059, -58.54614072120325), "Perfecto");
        PublicacionMascotaEncontrada pub6 = new PublicacionMascotaEncontrada(per2, new Direccion(-34.70275860011573, -58.40645683721891), "Asustado");
        org1.añadirPublicacionMascotaEncontrada(pub1);
        org1.añadirPublicacionMascotaEncontrada(pub2);
        org2.añadirPublicacionMascotaEncontrada(pub3);
        org4.añadirPublicacionMascotaEncontrada(pub4);
        org5.añadirPublicacionMascotaEncontrada(pub5);
        org5.añadirPublicacionMascotaEncontrada(pub6);

        org1.añadirPublicacionMascotaEnAdopcion(new PublicacionMascotaEnAdopcion(per1, per1.getMascotas().get(1), new HashMap<String, String>() {{
            put("Es bueno?", "Si");
            put("Es malo?", "Si");
        }}));
        org1.añadirPublicacionMascotaEnAdopcion(new PublicacionMascotaEnAdopcion(per1, per1.getMascotas().get(0), new HashMap<String, String>() {{
            put("Es alto?", "No");
            put("Es bueno?", "Si");
        }}));
        org2.añadirPublicacionMascotaEnAdopcion(new PublicacionMascotaEnAdopcion(per2, per2.getMascotas().get(0), new HashMap<String, String>() {{
            put("Come mucho", "No");
        }}));
        org5.añadirPublicacionMascotaEnAdopcion(new PublicacionMascotaEnAdopcion(per3, per3.getMascotas().get(0), new HashMap<String, String>() {{
            put("Es hiperactivo", "Si");
        }}));

        org1.añadirPublicacionBusquedaAdopcion(new PublicacionBusquedaAdopcion(per5, new ArrayList<String>() {{
            add("Patio");
            add("Pasto");
        }}));
        org2.añadirPublicacionBusquedaAdopcion(new PublicacionBusquedaAdopcion(per6, new ArrayList<String>() {{
            add("Pileta");
        }}));
        org4.añadirPublicacionBusquedaAdopcion(new PublicacionBusquedaAdopcion(per8, new ArrayList<String>() {{
            add("Balcon");
            add("Plantas");
        }}));

        Administrador admin1 = new Administrador("pepeOwner", "pepeo#123", org1);
        Administrador admin2 = new Administrador("juanOwner", "juan_cito(?123", org2);
        Administrador admin3 = new Administrador("mariaOwner", "maria_unica123", org3);
        Administrador admin4 = new Administrador("pedroOwner", "pedro_elduenio@123", org4);
        Administrador admin5 = new Administrador("joseOwner", "jose_nosequeponer%123", org5);

        org1.añadirAdministrador(admin1);
        admin1.darAltaNuevoAdministrador("martaAdmin", "martita&123");
        org2.añadirAdministrador(admin2);
        org3.añadirAdministrador(admin3);
        admin3.darAltaNuevoAdministrador("celesteAdmin", "celeste_f123");
        org4.añadirAdministrador(admin4);
        org5.añadirAdministrador(admin5);

        Voluntario vol1 = new Voluntario("pepeVol", "pepe1rsd23", org1);
        Voluntario vol6 = new Voluntario("martaVol", "martita123", org1);
        Voluntario vol2 = new Voluntario("juanVol", "juan_cito(?123", org2);
        Voluntario vol3 = new Voluntario("mariaVol", "maria_unica123", org3);
        Voluntario vol4 = new Voluntario("pedroVol", "pedro_elduenio123", org4);
        Voluntario vol5 = new Voluntario("joseVol", "jose_nosequeponer123", org5);

        org1.añadirVoluntario(vol1);
        org1.añadirVoluntario(vol6);
        org2.añadirVoluntario(vol2);
        org3.añadirVoluntario(vol3);
        org4.añadirVoluntario(vol4);
        org5.añadirVoluntario(vol5);

        masc1.añadirFoto("1.jpg");
        masc1.añadirFoto("2.jpg");
        masc2.añadirFoto("3.jpg");
        masc3.añadirFoto("4.jpg");
        masc4.añadirFoto("5.jpg");
        masc4.añadirFoto("6.jpg");
        masc5.añadirFoto("7.jpg");
        masc6.añadirFoto("8.jpg");
        masc6.añadirFoto("9.jpg");
        masc7.añadirFoto("10.jpg");
        masc8.añadirFoto("11.jpg");


        Contacto contactoProfesor = new Contacto("Luciano", "Straccia", "+5491158757641", "lstraccia1@frba.utn.edu.ar");
        Usuario usuarioProfesor = new Usuario("lucianostraccia", "acare@1443$str");
        Administrador administradorProfesor = new Administrador("lucianostraccia", "acare@1443$str", org1);
        Voluntario voluntarioProfesor = new Voluntario("lucianostraccia", "acare@1443$str", org1);
        Preferencia preferenciasProfesor = new Preferencia(Sexo.MACHO, Animal.PERRO, Tamaño.MEDIANO);
        Persona personaProfesor = new Persona(usuarioProfesor, contactoProfesor, LocalDate.of(1982, 11, 23),
                new Documento(TipoDocumento.DNI, 30787456), new Direccion(-34.59831733430477, -58.4201443483576),
                preferenciasProfesor, new ArrayList<Contacto>() {{
            add(con8);
            add(con1);
        }}, 10);
        Mascota mascota1Profesor = new Mascota("Pepe", "Pancho", 11, Animal.GATO, Sexo.HEMBRA, Tamaño.MEDIANO, descripcion);
        mascota1Profesor.añadirFoto("1.jpg");
        mascota1Profesor.añadirFoto("2.jpg");
        Mascota mascota2Profesor = new Mascota("Rex", "Dino", 3, Animal.PERRO, Sexo.MACHO, Tamaño.GRANDE, descripcion);
        mascota2Profesor.añadirFoto("1.jpg");
        Mascota mascota3Profesor = new Mascota("Pluto", "Plu", 5, Animal.PERRO, Sexo.MACHO, Tamaño.CHICO, descripcion);
        mascota3Profesor.añadirFoto("1.jpg");
        mascota3Profesor.añadirFoto("2.jpg");

        personas.guardar(per1);
        personas.guardar(per2);
        personas.guardar(per3);
        personas.guardar(per4);
        personas.guardar(per5);
        personas.guardar(per6);
        personas.guardar(per7);
        personas.guardar(per8);
        personas.guardar(personaProfesor);

        organizaciones.guardar(org1);
        organizaciones.guardar(org2);
        organizaciones.guardar(org3);
        organizaciones.guardar(org4);
        organizaciones.guardar(org5);

    }
}


