package com.utn.infraestructura.persistencia;

import com.utn.dominio.Usuarios;
import com.utn.dominio.autenticacion.Usuario;

public class UsuariosEnMySQL implements Usuarios {

    @Override
    public Usuario obtenerPorNombreUsuario(String nombreUsuario) {
        EntityManagerHelper.beginTransaction();
        Usuario unUsuario = EntityManagerHelper
            .getEntityManager()
            .createQuery("select usu from Usuario usu where usu.usuario = ?1",Usuario.class)
            .setParameter(1,nombreUsuario)
            .getSingleResult();
        EntityManagerHelper.commit();
        return unUsuario;
    }

    @Override
    public void guardar(Usuario unUsuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(unUsuario);
        EntityManagerHelper.commit();
    }

}