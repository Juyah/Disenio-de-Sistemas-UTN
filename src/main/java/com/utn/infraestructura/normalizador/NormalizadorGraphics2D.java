package com.utn.infraestructura.normalizador;

import com.utn.dominio.Normalizador;
import com.utn.dominio.organizacion.CalidadFoto;
import com.utn.dominio.organizacion.TamañoFoto;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class NormalizadorGraphics2D implements Normalizador {

    @Override
    public String ejecutar(String fotoOriginal, CalidadFoto calidadFoto, TamañoFoto tamaño) {
        File archivoEntrada = new File(fotoOriginal);
        BufferedImage imagenEntrante;
        try {
            imagenEntrante = ImageIO.read(archivoEntrada);
            BufferedImage imagenSaliente = new BufferedImage(tamaño.ancho(), tamaño.alto(), imagenEntrante.getType());
            Graphics2D graphics2d = imagenSaliente.createGraphics();
            switch(calidadFoto){
                case ALTA:
                    graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                case MEDIA:
                    graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
                case BAJA:
                    graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            }
            graphics2d.drawImage(imagenEntrante, 0, 0, tamaño.ancho(), tamaño.alto(), null);
            graphics2d.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}