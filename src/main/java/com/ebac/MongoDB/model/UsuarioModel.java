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


public class UsuarioModel {
    private final MongoCollection<Document> collection;

    public UsuarioModel (MongoDatabase database){
        collection = database.getCollection("usuarios");
    }

    public void guardar(Document document){
        collection.insertOne(document);
    }

    public void obtener(){
        FindIterable<Document> usuarios = collection.find();

        for(Document usuario : usuarios){
            ObjectId id = usuario.getObjectId("_id");
            String nombre = usuario.getString("nombre");
            int edad = usuario.getInteger("edad");
            System.out.println("ID: " + id.toHexString() + ", Nombre: " + nombre + ", Edad: " + edad);
        }
    }

    public Optional<Document> obtenerPorId(Document document){
        Document usuario = collection.find(document).first();

        if(!Objects.isNull(usuario)){
            ObjectId id = usuario.getObjectId("_id");
            String nombre = usuario.getString("nombre");
            int edad = usuario.getInteger("edad");
            System.out.println("ID: " + id.toHexString() + ", Nombre: " + nombre + ", Edad: " + edad);
            return Optional.of(usuario);
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
