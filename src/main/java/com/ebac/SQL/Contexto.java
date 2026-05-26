package com.ebac.SQL;
import com.ebac.SQL.model.TelefonosModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.ebac.SQL.dto.telefonos;

public class Contexto {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coneccionLocalMySQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TelefonosModel telefonoModel = new TelefonosModel(entityManager);

        telefonos telefonoJuan = new telefonos();
        telefonoJuan.setNumero("+52 55 2349 1234");
        telefonoJuan.setTipo("Oficina");
        telefonoModel.guardar(telefonoJuan);

        telefonos telefonoJuanObtenido = telefonoModel.obtenerPorId(1);
        System.out.println("Telefono obtenido: " + telefonoJuanObtenido.getNumero() + ", Tipo: " + telefonoJuanObtenido.getTipo());

        telefonoJuanObtenido.setTipo("Celular");
        telefonoModel.actualizar(telefonoJuan);

        telefonos telefonoJuanActualizado = telefonoModel.obtenerPorId(1);
        System.out.println("Telefono actualizado: " + telefonoJuanActualizado.getNumero() + ", Tipo: " + telefonoJuanActualizado.getTipo());

        telefonoModel.eliminar(telefonoJuan);
        telefonoJuan = telefonoModel.obtenerPorId(1);
        System.out.println("Telefono después de eliminación: " + (telefonoJuan == null ? "No encontrado" : telefonoJuan.getNumero()));

        entityManager.close();
        entityManagerFactory.close();
    }
}
