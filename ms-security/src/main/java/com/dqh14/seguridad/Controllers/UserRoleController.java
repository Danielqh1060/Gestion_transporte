package com.dqh14.seguridad.Controllers;

import com.dqh14.seguridad.Models.Role;
import com.dqh14.seguridad.Models.User;
import com.dqh14.seguridad.Models.UserRole;
import com.dqh14.seguridad.Repositories.RoleRepository;
import com.dqh14.seguridad.Repositories.UserRepository;
import com.dqh14.seguridad.Repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user_role")

public class UserRoleController {
    @Autowired
    private UserRoleRepository theUserRoleRepository; //Aqui se esta usando una inyeccion de dependencias en la interfaz. En este caso esta adoptando los metdodos de la libreria que nos vana a ayudar a conectarnos con la bd de mongo

    @Autowired
    private RoleRepository theRoleRepository;

    @Autowired
    private UserRepository theUserRepository;

    @PostMapping("user/{userId}/role/{roleId}")
    public UserRole create(@PathVariable String userId,
                           @PathVariable String roleId){
        User theUser = this.theUserRepository.findById(userId).orElse(null);
        Role theRole = this.theRoleRepository.findById(roleId).orElse(null);

        if(theRole!=null && theUser!=null){
            UserRole theNewUserRole = new UserRole();

            theNewUserRole.setUser(theUser);
            theNewUserRole.setRole(theRole);
            return this.theUserRoleRepository.save(theNewUserRole);
        }else {
            return null;
        }

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        UserRole theUserRole=this.theUserRoleRepository.findById(id).orElse(null);
        if (theUserRole!=null){
            this.theUserRoleRepository.delete(theUserRole);
        }
    }
    //Creacion de la API

    @GetMapping("user/{userId}")
    public List<UserRole> getRolesByUser(@PathVariable String userId){
        return this.theUserRoleRepository.getRolesByUser(userId);
    }

    @GetMapping("role/{roleId}")
    public List<UserRole> getUsersByRole(@PathVariable String roleId){
        return this.theUserRoleRepository.getUsersByRole(roleId);
    }
}




