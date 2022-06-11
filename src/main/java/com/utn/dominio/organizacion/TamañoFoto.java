package com.utn.dominio.organizacion;

import com.utn.dominio.excepcion.CalidadFotoNoValidaException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TamañoFoto {

    CHICA("Chica"),
    NORMAL("Normal"),
    GRANDE("Grande");

    TamañoFoto(String valor) {
        this.valor = valor;
    }

    private final String valor;

    public static TamañoFoto buscar(String valor) {
        return Arrays.stream(TamañoFoto.values())
                .filter(tamañoFoto -> tamañoFoto.coincide(valor))
                .findFirst()
                .orElseThrow(CalidadFotoNoValidaException::new);
    }

    public boolean coincide(String valor) {
        return this.valor.equals(valor);
    }

    public Integer ancho(){
        switch (this){
            case CHICA: return 540;
            case NORMAL: return 720;
            case GRANDE: return 1080;
            default: return null;
        }
    }

    public Integer alto(){
        switch (this){
            case CHICA: return 960;
            case NORMAL: return 1280;
            case GRANDE: return 1920;
            default: return null;
        }
    }

    public static List<String> getDescripciones() {
        return Arrays.stream(TamañoFoto.values())
                .map(unTamaño -> unTamaño.valor)
                .collect(Collectors.toList());
    }
}
