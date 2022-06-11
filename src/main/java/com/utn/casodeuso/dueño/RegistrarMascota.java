package com.utn.casodeuso.dueño;

import com.utn.dominio.Organizaciones;
import com.utn.dominio.Personas;
import com.utn.dominio.animal.Animal;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.animal.Sexo;
import com.utn.dominio.animal.Tamaño;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.persona.TipoDocumento;

import java.util.List;
import java.util.Map;


public class RegistrarMascota {

    private final Personas personas;
    private final Organizaciones organizaciones;

    public RegistrarMascota(Personas personas, Organizaciones organizaciones) {
        this.personas = personas;
        this.organizaciones = organizaciones;
    }

    public void ejecutar(String nombreOrg, int documentoDueño, TipoDocumento tipoDocumento, String nombre, String apodo, int edad, Animal tipoAnimal, Sexo sexo,
                         Tamaño tamaño, String descripcionFisica, List<String> fotos,
                         Map<String, String> caracteristicas) {
        Persona persona = personas.obtenerPorNumeroDocumento(documentoDueño, tipoDocumento);
        Organizacion organizacion = organizaciones.obtenerPorNombre(nombreOrg);

        Mascota mascota = new Mascota(nombre, apodo, edad, tipoAnimal, sexo, tamaño, descripcionFisica);
        fotos.forEach(mascota::añadirFoto);
        caracteristicas.forEach(mascota::añadirCaracteristica);
        persona.añadirMascota(mascota);
        organizacion.añadirMascota(mascota);

        personas.guardar(persona);
    }
}
