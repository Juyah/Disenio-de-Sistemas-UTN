package com.utn.dominio.autenticacion;

import com.utn.dominio.archivo.Archivo;
import com.utn.dominio.excepcion.ContraseñaDebilException;

import java.util.List;

public class ContraseñaDebil implements Criterios {

    private final static String rutaArchivo = "src/main/resources/contraseñasInseguras.txt";

    public void validar(String contraseña) {
        List<String> contraseñasInseguras = Archivo.leer(rutaArchivo);
        boolean esContraseñaDebil = contraseñasInseguras.stream()
            .anyMatch(contraseñaInsegura -> contraseñaInsegura.equals(contraseña));
        if (esContraseñaDebil)
            throw new ContraseñaDebilException();
    }

}