package com.utn.infraestructura.persistencia;

import com.utn.dominio.Personas;
import com.utn.dominio.animal.Mascota;
import com.utn.dominio.persona.Persona;
import com.utn.dominio.persona.TipoDocumento;

import java.util.List;


public class PersonasEnMySQL implements Personas {

    @Override
    public Persona obtenerPorNombreDeUsuario(String nombreUsuario) {
        EntityManagerHelper.beginTransaction();

        Persona unaPersona = (Persona) EntityManagerHelper.getEntityManager()
                .createQuery("FROM Persona per WHERE EXISTS (FROM Usuario usr WHERE usr.usuario = '"
                        + nombreUsuario + "' AND per.usuario = usr)").getSingleResult();

        EntityManagerHelper.commit();
        return unaPersona;
    }

    @Override
    public Persona obtenerPorNumeroDocumento(int numeroDocumento, TipoDocumento tipoDocumento) {
        EntityManagerHelper.beginTransaction();

        Persona persona = (Persona) EntityManagerHelper.getEntityManager()
                .createQuery("FROM Persona per WHERE EXISTS (FROM Documento doc WHERE doc.numero = ?1 AND doc.tipo = ?2 AND per.documento = doc.id)")
                .setParameter(1, numeroDocumento)
                .setParameter(2, tipoDocumento)
                .getSingleResult();

        EntityManagerHelper.commit();
        return persona;
    }

    @Override
    public void guardar(Persona persona) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(persona);

        EntityManagerHelper.commit();
    }

    @Override
    public Persona obtenerPorIdMascota(int idMascota) {
        EntityManagerHelper.beginTransaction();

        Persona persona = (Persona) EntityManagerHelper.getEntityManager()
                .createQuery("SELECT m.duenio from Mascota m where m.id = ?1")
                .setParameter(1, idMascota)
                .getSingleResult();

        EntityManagerHelper.commit();
        return persona;
    }
}
