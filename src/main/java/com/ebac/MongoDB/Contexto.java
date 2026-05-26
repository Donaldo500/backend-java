package com.ebac.MongoDB;
import com.ebac.MongoDB.model.UsuarioModel;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.Optional;

public class Contexto {
    public static void main(String[] args) {
        String connectionString = "mongodb://root:toor@localhost:27017";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("modulo36");

        UsuarioModel usuarioModel = new UsuarioModel(database);

        //Crear usuario
        Document document = new Document("nombre", "John Doe")
                .append("edad", 30)
                .append("profesion", "Programdor java");
        usuarioModel.guardar(document);

        //Listar Usuarios
        usuarioModel.obtener();

        //Listar usuarios por id
        ObjectId objectId = new ObjectId("64a1f8e5c9e77b2f8c8b4567");
        Document documentoABuscar = new Document("_id", objectId);
        Optional<Document> usuarioEncontrado= usuarioModel.obtenerPorId(documentoABuscar);

        //Actualiza usuario
        usuarioEncontrado.ifPresent(usuarioActual -> {
            Document document1 = new Document("nombre", "PedroActualizado").append("edad", 20);
            Document usuarioActualizado = new Document("$set", document1);

            usuarioModel.actualizar(documentoABuscar, usuarioActualizado);
        });
        usuarioModel.obtener();

        //Eliminar usuario
        usuarioModel.obtener();
        usuarioEncontrado.ifPresent(usuario -> usuarioModel.eliminar(usuario));
        usuarioModel.obtener();
        
    }
}
