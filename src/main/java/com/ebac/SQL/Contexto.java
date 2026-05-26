package com.ebac.SQL;
import com.ebac.SQL.model.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.ebac.SQL.dto.Usuario;

public class Contexto {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coneccionLocalMySQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UsuarioModel usuarioModel = new UsuarioModel(entityManager);

        Usuario usuarioJuan = new Usuario();
        usuarioJuan.setNombre("Juan");
        usuarioJuan.setEdad(20);
        usuarioModel.guardar(usuarioJuan);

        Usuario usuario = usuarioModel.obtenerPorId(1);
        System.out.println("Usuario obtenido: " + usuario.getNombre() + ", Edad: " + usuario.getEdad());

        usuario.setEdad(50);
        usuarioModel.actualizar(usuarioJuan);

        Usuario usuarioJuanActualizado = usuarioModel.obtenerPorId(1);
        System.out.println("Usuario actualizado: " + usuarioJuanActualizado.getNombre() + ", Edad: " + usuarioJuanActualizado.getEdad());

        usuarioModel.eliminar(usuario);
        usuario = usuarioModel.obtenerPorId(1);
        System.out.println("Usuario después de eliminación: " + (usuario == null ? "No encontrado" : usuario.getNombre()));

        entityManager.close();
        entityManagerFactory.close();
    }
}
