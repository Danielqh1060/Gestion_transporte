package com.dqh14.seguridad.Repositories;

import com.dqh14.seguridad.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{'email':  ?0}")
    public User getUserByEmail(String email);

}