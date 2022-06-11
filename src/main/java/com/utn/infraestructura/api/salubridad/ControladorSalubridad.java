package com.utn.infraestructura.api.salubridad;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ControladorSalubridad {

    @GetMapping("/")
    public ResponseEntity<RespuestaChequearEstado> chequearEstado() {
        return new ResponseEntity<>(new RespuestaChequearEstado(), HttpStatus.OK);
    }
}
