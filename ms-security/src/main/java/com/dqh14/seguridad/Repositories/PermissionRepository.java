package com.dqh14.seguridad.Repositories;

import com.dqh14.seguridad.Models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PermissionRepository extends MongoRepository<Permission,String> {
    @Query("{'url':?0,'method':?1}")
    Permission getPermission(String url,
                             String method);
}
