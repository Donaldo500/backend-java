package com.ebac.MongoDB;
import com.ebac.MongoDB.model.TelefonosModel;
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
        MongoDatabase database = mongoClient.getDatabase("modulo37");

        TelefonosModel telefonoModel = new TelefonosModel(database);

        Document document = new Document("numero", "+55 123 456 7894")
                .append("tipo", "Celular");
        telefonoModel.guardar(document);

        telefonoModel.obtener();

        ObjectId objectId = new ObjectId("64a1f8e5c9e77b2f8c8b4567");
        Document documentoABuscar = new Document("_id", objectId);
        Optional<Document> telefonoEncontrado= telefonoModel.obtenerPorId(documentoABuscar);

        telefonoEncontrado.ifPresent(telefonoActual -> {
            Document document1 = new Document("numero", "+55 123 456 7444").append("tipo", "Fijo");
            Document telefonoActualizado = new Document("$set", document1);

            telefonoModel.actualizar(documentoABuscar, telefonoActualizado);
        });
        telefonoModel.obtener();

        telefonoModel.obtener();
        telefonoEncontrado.ifPresent(telefono -> telefonoModel.eliminar(telefono));
        telefonoModel.obtener();
    }
}
