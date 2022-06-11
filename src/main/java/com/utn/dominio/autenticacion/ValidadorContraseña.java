package com.utn.dominio.autenticacion;

import java.util.List;
import java.util.ArrayList;

public class ValidadorContraseña {

    private static final List<Criterios> criterios = new ArrayList<Criterios>() {{
        add(new ContraseñaDebil());
        add(new ContraseñaCorta());
    }};

    public static void ejecutar(String contraseña) {
        criterios.forEach(criterio  -> criterio.validar(contraseña));
    }

}