package com.utn.infraestructura.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SesionManager
{
    private static SesionManager instance;
    private Map<String,Map<String,Object>> sesiones;

    public SesionManager() {
        this.sesiones = new HashMap<>();
    }

    public static SesionManager getInstance()
    {
        if(instance == null)
        {
            instance = new SesionManager();
        }
        return instance;
    }

    public String crear() {
        return this.crear(new HashMap<>());
    }

    public String crear(String clave, Object valor) {
        HashMap<String, Object> atributo = new HashMap<>();
        atributo.put(clave, valor);
        return this.crear(atributo);
    }

    public String crear(Map<String, Object> atributos) {
        String id = UUID.randomUUID().toString();
        this.sesiones.put(id, atributos);
        return id;
    }

    public Map<String, Object> obtenerAtributos(String id) {
        return this.sesiones.get(id);
    }

    public void agregarAtributo(String id, String clave, Object valor) {
        Map<String, Object> atributos = this.sesiones.get(id);
        atributos.put(clave, valor);
    }

    public void agregarAtributos(String id, Map<String, Object> nuevosAtributos) {
        Map<String, Object> atributos = this.sesiones.get(id);
        atributos.putAll(nuevosAtributos);
    }

    public Map<String, Object> eliminar(String id) {
        return this.sesiones.remove(id);
    }
}
