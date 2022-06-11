package com.utn.infraestructura.api.inicio.ClienteLiviano;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ControladorInicioCL
{
    private final Handlebars handlebars;

    public ControladorInicioCL() {
        this.handlebars = new Handlebars();
    }

    @GetMapping(value = "/home", produces= MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> obtenerVista() throws IOException
    {
        Template template = handlebars.compile("/templates/index");
        Map<String,Object> model = new HashMap<>();
        String html = template.apply(model);
        return ResponseEntity.status(200).body(html);
    }

}
