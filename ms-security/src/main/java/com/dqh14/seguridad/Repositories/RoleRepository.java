package com.dqh14.seguridad.Repositories;

import com.dqh14.seguridad.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
