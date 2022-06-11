package com.utn.infraestructura.persistencia;

import com.utn.dominio.Publicaciones;
import com.utn.dominio.publicacion.Publicacion;

public class PublicacionesEnMySQL implements Publicaciones {

    @Override
    public Publicacion obtenerPorId(int idPublicacion) {
        EntityManagerHelper.beginTransaction();

        Publicacion publicacion = (Publicacion) EntityManagerHelper.getEntityManager()
                .createQuery("FROM Publicacion pub where pub.id =" + idPublicacion).getSingleResult();

        EntityManagerHelper.commit();
        return publicacion;
    }

    @Override
    public void guardar(Publicacion unaPublicacion) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(unaPublicacion);

        EntityManagerHelper.commit();
    }
}
