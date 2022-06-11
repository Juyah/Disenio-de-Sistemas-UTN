package com.utn.infraestructura.persistencia;

import com.utn.dominio.Administradores;
import com.utn.dominio.organizacion.Administrador;


public class AdministradoresEnMySQL implements Administradores {
    @Override
    public Administrador obtenerPorNombreUsuario(String nombreUsuario) {
        EntityManagerHelper.beginTransaction();

        Administrador unAdministrador = EntityManagerHelper.getEntityManager()
                .createQuery("select admin from Administrador admin where admin.usuario = ?1", Administrador.class)
                .setParameter(1, nombreUsuario)
                .getSingleResult();

        EntityManagerHelper.commit();
        return unAdministrador;
    }

    @Override
    public void guardar(Administrador unAdministrador) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(unAdministrador);
        EntityManagerHelper.commit();
    }
}