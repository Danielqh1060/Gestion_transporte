package com.dqh14.seguridad.Repositories;

import com.dqh14.seguridad.Models.RolePermission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RolePermissionRepository extends MongoRepository<RolePermission,String> {
    @Query("{'role.$id': ObjectId(?0)}")
    List<RolePermission> getPermissionsByRole(String roleId);
    @Query("{'role.$id': ObjectId(?0),'permission.$id': ObjectId(?1)}")
    public RolePermission getRolePermission(String roleId,String permissionId);
}
