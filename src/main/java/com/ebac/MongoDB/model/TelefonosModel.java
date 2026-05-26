package com.ebac.MongoDB.model;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.Optional;
import java.util.Objects;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;


public class TelefonosModel {
    private final MongoCollection<Document> collection;

    public TelefonosModel (MongoDatabase database){
        collection = database.getCollection("telefonos");
    }

    public void guardar(Document document){
        collection.insertOne(document);
    }

    public void obtener(){
        FindIterable<Document> telefonos = collection.find();

        for(Document telefono : telefonos){
            ObjectId id = telefono.getObjectId("_id");
            String numero = telefono.getString("numero");
            String tipo = telefono.getString("tipo");
            System.out.println("ID: " + id.toHexString() + ", Número: " + numero + ", Tipo: " + tipo);
        }
    }

    public Optional<Document> obtenerPorId(Document document){
        Document telefono = collection.find(document).first();

        if(!Objects.isNull(telefono)){
            ObjectId id = telefono.getObjectId("_id");
            String numero = telefono.getString("numero");
            String tipo = telefono.getString("tipo");
            System.out.println("ID: " + id.toHexString() + ", Número: " + numero + ", Tipo: " + tipo);
            return Optional.of(telefono);
        }
        return Optional.empty();
    }

    public void actualizar(Document documentoActual, Document documentoNuevo){
        UpdateResult updateResult = collection.updateOne(documentoActual, documentoNuevo);

        if(updateResult.getModifiedCount() > 0){
            System.out.println("Documento actualizado correctamente.");
        } else {
            System.out.println("No se encontró el documento para actualizar.");
        }
    }

    public void eliminar(Document document){
        DeleteResult deleteResult = collection.deleteOne(document);

        if(deleteResult.getDeletedCount() > 0){
            System.out.println("Documento eliminado correctamente.");
        } else {
            System.out.println("No se encontró el documento para eliminar.");
        }
    }
}
