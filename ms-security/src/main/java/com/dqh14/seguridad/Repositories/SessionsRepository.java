package com.dqh14.seguridad.Repositories;

import com.dqh14.seguridad.Models.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SessionsRepository extends MongoRepository<Session, String> {

    @Query("{'user.$id': ObjectId(?0)}") //Esta es la consulta de como se va a tirar en mongo. En otras palabras dice: "Busca la el id con el que concuerde el id que estoy consultando
    public List<Session> getSessionsByUser(String userId);
}
