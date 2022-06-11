package com.utn.infraestructura.hogares;

import java.util.List;

public class Hogar {
    public String id;
    public String nombre;
    public Ubicacion ubicacion;
    public String telefono;
    public Admisiones admisiones;
    public Integer capacidad;
    public Integer lugares_disponibles;
    public Boolean patio;
    public List<String> caracteristicas;
}
