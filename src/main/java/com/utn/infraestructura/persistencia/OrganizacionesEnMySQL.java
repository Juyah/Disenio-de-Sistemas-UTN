package com.utn.infraestructura.persistencia;

import com.utn.dominio.Organizaciones;
import com.utn.dominio.organizacion.Organizacion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizacionesEnMySQL implements Organizaciones {


    @Override
    public List<Organizacion> obtenerTodas(){
        EntityManagerHelper.beginTransaction();

        List<Organizacion> organizaciones = (List<Organizacion>) EntityManagerHelper.getEntityManager()
                .createQuery("from Organizacion").getResultList();

        EntityManagerHelper.commit();
        return organizaciones;
    }

    @Override
    public Organizacion obtenerPorNombre(String nombreOrganizacion)
    {
        EntityManagerHelper.beginTransaction();

        Organizacion unaOrganizacion = (Organizacion) EntityManagerHelper.getEntityManager()
        .createQuery("from Organizacion org where org.nombre = '" + nombreOrganizacion + "'").getSingleResult();

        EntityManagerHelper.commit();
        return unaOrganizacion;
    }

    @Override
    public void guardar(Organizacion organizacion)
    {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(organizacion);

        EntityManagerHelper.commit();
    }
}
