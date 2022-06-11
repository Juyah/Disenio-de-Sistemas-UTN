package com.utn.infraestructura.persistencia;

import com.utn.dominio.Voluntarios;
import com.utn.dominio.organizacion.Voluntario;


public class VoluntariosEnMySQL implements Voluntarios {

    public Voluntario obtenerPorNombreUsuario(String nombreUsuario) {
        EntityManagerHelper.beginTransaction();

        Voluntario voluntario = EntityManagerHelper.getEntityManager()
                .createQuery("select vol from Voluntario vol where vol.usuario = ?1", Voluntario.class)
                .setParameter(1, nombreUsuario)
                .getSingleResult();

        EntityManagerHelper.commit();
        return voluntario;
    }

    @Override
    public void guardar(Voluntario voluntario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(voluntario);
        EntityManagerHelper.commit();
    }
}
