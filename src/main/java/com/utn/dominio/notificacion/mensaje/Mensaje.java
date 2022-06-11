package com.utn.dominio.notificacion.mensaje;

public class Mensaje {

    private String emailDestino;
    private String telefonoDestino;
    private final String cuerpo;
    private final String asunto;

    public Mensaje(String cuerpo, String asunto) {
        this.cuerpo = cuerpo;
        this.asunto = asunto;
    }

    public void agregarEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    public void agregarTelefonoDestino(String telefonoDestino) {
        this.telefonoDestino = telefonoDestino;
    }

    public String cuerpo() {
        return this.cuerpo;
    }

    public String telefonoDestino(){
        return this.telefonoDestino;
    }

    public String emailDestino() {
        return this.emailDestino;
    }

    public String asunto() {
        return this.asunto;
    }
}