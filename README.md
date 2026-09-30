# backend-java

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-JPA-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-Driver_5.1-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

Comparación práctica entre **persistencia relacional con JPA** y **persistencia NoSQL con MongoDB** en Java. Ambas implementaciones gestionan el mismo concepto (un usuario con nombre y edad) para contrastar cómo se modela, consulta y actualiza la información en cada tecnología.

## Descripción

| Paquete | Enfoque | Qué hace |
| --- | --- | --- |
| `com.ebac.SQL` | JPA sobre MySQL | Entidad `Usuario` anotada con `@Entity`, `@Table`, `@Id` y `@GeneratedValue`. `UsuarioModel` usa `EntityManager` y transacciones para guardar, actualizar, buscar y eliminar, además de listar con la **Criteria API**. |
| `com.ebac.MongoDB` | Driver síncrono de MongoDB | `UsuarioModel` trabaja sobre la colección `usuarios` con `Document` de BSON: `insertOne`, `find`, `updateOne` con `$set` y `deleteOne`, devolviendo `Optional<Document>` en las búsquedas. |

### Funcionalidades

- CRUD completo de usuarios en MySQL mediante JPA con manejo de transacciones y `rollback` ante errores.
- Listado de usuarios con `CriteriaBuilder` y `TypedQuery`.
- CRUD completo de usuarios en MongoDB, con búsqueda por `ObjectId`.
- Mensajes en consola que indican si la actualización o eliminación afectó algún documento.

## Tecnologías utilizadas

| Tecnología | Versión |
| --- | --- |
| Java | 21 |
| Spring Boot Starter Data JPA (Hibernate) | 3.3.5 |
| javax.persistence API | 2.2 |
| MySQL Connector/J | 8.0.33 |
| MongoDB Driver Sync | 5.1.0 |
| Maven + exec-maven-plugin | 3.1.0 |

## Estructura del proyecto

```text
src/main/
├── java/com/ebac/
│   ├── SQL/
│   │   ├── Contexto.java            # Flujo CRUD con JPA
│   │   ├── dto/Usuario.java         # Entidad JPA
│   │   └── model/UsuarioModel.java
│   └── MongoDB/
│       ├── Contexto.java            # Flujo CRUD con MongoDB
│       └── model/UsuarioModel.java
└── resources/META-INF/persistence.xml   # Unidad de persistencia "coneccionLocalMySQL"
```

## Instalación y uso

### 1. Levantar las bases de datos

```bash
docker run --rm --name mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:8
docker run --rm --name mongo -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=toor -d -p 27017:27017 mongo
```

### 2. Preparar MySQL

```sql
CREATE DATABASE modulo36;
USE modulo36;

CREATE TABLE usuarios (
    idUsuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100),
    edad      INT
);
```

MongoDB crea la base `modulo36` y la colección `usuarios` automáticamente al insertar el primer documento.

### 3. Ejecutar

```bash
git clone https://github.com/Donaldo500/backend-java.git
cd backend-java

# Versión JPA / MySQL
mvn compile exec:java -Dexec.mainClass="com.ebac.SQL.Contexto"

# Versión MongoDB
mvn compile exec:java -Dexec.mainClass="com.ebac.MongoDB.Contexto"
```

La conexión de MySQL se configura en `src/main/resources/META-INF/persistence.xml` y la de MongoDB en `MongoDB/Contexto.java` (`mongodb://root:toor@localhost:27017`).

## Ejemplos de uso

JPA:

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("coneccionLocalMySQL");
UsuarioModel usuarioModel = new UsuarioModel(emf.createEntityManager());

Usuario juan = new Usuario();
juan.setNombre("Juan");
juan.setEdad(20);
usuarioModel.guardar(juan);

List<Usuario> todos = usuarioModel.obtenerUsuarios();
```

MongoDB:

```java
UsuarioModel usuarioModel = new UsuarioModel(database);

usuarioModel.guardar(new Document("nombre", "John Doe")
        .append("edad", 30)
        .append("profesion", "Programador java"));

usuarioModel.actualizar(
        new Document("_id", objectId),
        new Document("$set", new Document("nombre", "PedroActualizado").append("edad", 20)));
```

## Contribuciones

Proyecto individual con fines de aprendizaje. Las sugerencias son bienvenidas mediante issues o pull requests.

## Autor

**Donaldo Ibarra** - [@Donaldo500](https://github.com/Donaldo500)
