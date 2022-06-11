package com.utn.infraestructura.persistencia;

import com.utn.dominio.Mascotas;
import com.utn.dominio.animal.Mascota;

public class MascotasEnMySQL implements Mascotas
{
    public Mascota obtenerPorId(int idMascota) {

        EntityManagerHelper.beginTransaction();
        Mascota unaMascota = (Mascota) EntityManagerHelper.getEntityManager()
                .createQuery( "from Mascota mas where mas.id = " + idMascota).getSingleResult();
        EntityManagerHelper.commit();

        return unaMascota;
    }

    @Override
    public void guardar(Mascota mascota) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(mascota);

        EntityManagerHelper.commit();
    }

}