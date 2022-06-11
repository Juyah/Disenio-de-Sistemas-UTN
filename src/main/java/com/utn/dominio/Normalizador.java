package com.utn.dominio;

import com.utn.dominio.organizacion.TamañoFoto;
import com.utn.dominio.organizacion.CalidadFoto;

public interface Normalizador {
    String ejecutar(String fotoOriginal, CalidadFoto calidadFoto, TamañoFoto tamaño);
}