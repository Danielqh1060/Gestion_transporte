package com.dqh14.seguridad.Repositories;

import com.dqh14.seguridad.Models.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {
    @Query("{'user.$id': ObjectId(?0)}")
    public List<UserRole> getRolesByUser(String userId);

    @Query("{'role.$id': ObjectId(?0)}")
    public List<UserRole> getUsersByRole(String roleId);
}
