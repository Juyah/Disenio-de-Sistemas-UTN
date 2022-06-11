package com.utn.dominio.archivo;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

public class Archivo {

    public static List<String> leer(String ruta) {
        List<String> lineas = new ArrayList<>();
        try {
            Scanner escaner = new Scanner(new File(ruta));
            while(escaner.hasNext())
                lineas.add(escaner.next());
            escaner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lineas;
    }

}