package com.dqh14.seguridad.Controllers;

import com.dqh14.seguridad.Models.Permission;
import com.dqh14.seguridad.Models.Role;
import com.dqh14.seguridad.Models.RolePermission;
import com.dqh14.seguridad.Repositories.PermissionRepository;
import com.dqh14.seguridad.Repositories.RolePermissionRepository;
import com.dqh14.seguridad.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/role-permission")
public class RolePermissionController {
    @Autowired
    private RolePermissionRepository theRolePermissionRepository;
    @Autowired
    private PermissionRepository thePermissionRepository;
    @Autowired
    private RoleRepository theRoleRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("role/{roleId}/permission/{permissionId}")
    public RolePermission create(@PathVariable String roleId,
                                 @PathVariable String permissionId){
        Role theRole=this.theRoleRepository.findById(roleId)
                                            .orElse(null);
        Permission thePermission=this.thePermissionRepository.findById((permissionId))
                                                                .orElse(null);
        if(theRole!=null && thePermission!=null){
            RolePermission newRolePermission=new RolePermission();
            newRolePermission.setRole(theRole);
            newRolePermission.setPermission(thePermission);
            return this.theRolePermissionRepository.save(newRolePermission);
        }else{
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        RolePermission theRolePermission = this.theRolePermissionRepository
                .findById(id)
                .orElse(null);
        if (theRolePermission != null) {
            this.theRolePermissionRepository.delete(theRolePermission);
        }
    }
    @GetMapping("role/{roleId}")
    public List<RolePermission> findPermissionsByRole(@PathVariable String roleId){
        return this.theRolePermissionRepository.getPermissionsByRole(roleId);
    }

}
