package com.utn.infraestructura;

import com.utn.dominio.organizacion.CalidadFoto;
import com.utn.dominio.organizacion.TamañoFoto;
import com.utn.dominio.organizacion.Administrador;
import com.utn.dominio.persona.Direccion;

import java.util.ArrayList;
import java.util.List;

public class FixtureCargaDeDatos {
    private static final String nombreOrganizacion1 = "Organizacion1";
    private static final Direccion direccionOrganizacion1 = new Direccion(1329.42, 1233.22);
    private static final TamañoFoto tamañoFotoOrganizacion1 = TamañoFoto.NORMAL;
    private static final CalidadFoto calidadFotoOrganizacion1 = CalidadFoto.MEDIA;
    private static final Administrador administradorDueñoOrganizacion1 = new Administrador("Organizacion1", "Org1#2021^Admin", null);
    private static final List<String> caracteristicasOrganizacion1 = new ArrayList<String>(){{add("Percentile"); add("Alimentacion"); add("Vacunas"); add("Raza");}};
    private static final List<String> preguntasAdopcionOrganizacion1 = new ArrayList<String>(){{add("Por que lo da en adopcion?"); add("Tiene algun trauma?");}};
    private static final List<String> preguntasQuieroAdoptarOrganizacion1 = new ArrayList<String>(){{add("Cuenta con ingresos suficientes?"); add("Cuenta con espacio suficiente?"); add("Existe un tercero que pueda asistirle?"); add("Tuvo mascotas anteriormente?");}};

    private static final String nombreOrganizacion2 = "Organizacion2";
    private static final Direccion direccionOrganizacion2 = new Direccion(1559.82, 1122.11);
    private static final TamañoFoto tamañoFotoOrganizacion2 = TamañoFoto.GRANDE;
    private static final CalidadFoto calidadFotoOrganizacion2 = CalidadFoto.ALTA;
    private static final Administrador administradorDueñoOrganizacion2 = new Administrador("Organizacion2", "Org2#2021^Admin", null);
    private static final List<String> caracteristicasOrganizacion2 = new ArrayList<String>(){{add("Percentile"); add("Alimentacion"); add("Vacunas"); add("Raza");}};
    private static final List<String> preguntasAdopcionOrganizacion2 = new ArrayList<String>(){{add("Por que lo da en adopcion?"); add("Tiene algun trauma?");}};
    private static final List<String> preguntasQuieroAdoptarOrganizacion2 = new ArrayList<String>(){{add("Tiene niños pequeños?"); add("Se encuentra mucho tiempo fuera de casa?"); add("Tuvo mascotas anteriormente?");}};


}
