package com.utn.infraestructura.api.organizacion;

import com.utn.casodeuso.organizacion.ObtenerOrganizacion;
import com.utn.casodeuso.organizacion.ObtenerTodasLasOrganizaciones;
import com.utn.dominio.Organizaciones;
import com.utn.dominio.organizacion.Organizacion;
import com.utn.infraestructura.persistencia.OrganizacionesEnMySQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ControladorOrganizacion {

    private final ObtenerTodasLasOrganizaciones obtenerTodasLasOrganizaciones;
    private final ObtenerOrganizacion obtenerOrganizacion;

    public ControladorOrganizacion() {
        Organizaciones organizacionesEnMySQL = new OrganizacionesEnMySQL();
        this.obtenerTodasLasOrganizaciones = new ObtenerTodasLasOrganizaciones(organizacionesEnMySQL);
        this.obtenerOrganizacion = new ObtenerOrganizacion(organizacionesEnMySQL);
    }

    @GetMapping("organizaciones/nombres")
    public ResponseEntity obtenerNombreDeTodasLasOrganizaciones() {
        List<Organizacion> organizaciones = this.obtenerTodasLasOrganizaciones.ejecutar();
        List<String> nombresOrganizaciones = organizaciones.stream().map(Organizacion::getNombre).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.status(200).body(nombresOrganizaciones);
    }

    @GetMapping("organizacion/{nombre}/caracteristicas")
    public ResponseEntity obtenerCaracteristicas(@PathVariable("nombre") String nombreOrg) {
        Organizacion organizacion = this.obtenerOrganizacion.ejecutar(nombreOrg);
        return ResponseEntity.status(200).body(organizacion.getCaracteristicas());
    }

    @GetMapping("organizacion/{nombre}/preguntasDarEnAdopcion")
    public ResponseEntity obtenerPreguntasDarEnAdopcion(@PathVariable("nombre") String nombreOrg) {
        Organizacion organizacion = this.obtenerOrganizacion.ejecutar(nombreOrg);
        return ResponseEntity.status(200).body(organizacion.getPreguntasDarEnAdopcion());
    }

    @GetMapping("organizacion/preguntasGenerales/darEnAdopcion")
    public ResponseEntity obtenerPreguntasGeneralesEnAdopcion() {
        List<String> preguntas = new ArrayList<String>(){{
            add("¿PregGeneral1?");
            add("¿PregGeneral2?");
            add("¿PregGeneral3?");
        }};
        return ResponseEntity.status(200).body(preguntas);
    }

}
