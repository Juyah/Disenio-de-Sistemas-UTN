package com.utn.dominio;

import com.utn.dominio.animal.Mascota;

public interface Mascotas {
    Mascota obtenerPorId(int idMascota);
    void guardar(Mascota mascota);
}