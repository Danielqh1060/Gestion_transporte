package com.dqh14.seguridad.Controllers;

import com.dqh14.seguridad.Models.Role;
import com.dqh14.seguridad.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")

public class RoleController {
    @Autowired
    private RoleRepository theRoleRepository; //Aqui se esta usando una inyeccion de dependencias en la interfaz. En este caso esta adoptando los metdodos de la libreria que nos vana a ayudar a conectarnos con la bd de mongo

    @GetMapping("")
    public List<Role> find(){
        return this.theRoleRepository.findAll(); //Este metodo es para que a la hora de que se use un "GET" general de los usuarios, simplemente nos muestre a todos
    }
    @GetMapping("{id}")
    public Role findById(@PathVariable String id){ //Aqui es para devolver un unico usuario. El @PathVariable es la ruta, en este caso de donde va a conseguir el identificador
        Role theRole=this.theRoleRepository.findById(id).orElse(null);
        return theRole;
    }
    @PostMapping
    public Role create(@RequestBody Role newRole){
        return this.theRoleRepository.save(newRole);
    }
    @PutMapping("{id}")
    public Role update(@PathVariable String id, @RequestBody Role newRole){
        Role actualRole=this.theRoleRepository.findById(id).orElse(null);
        if(actualRole!=null){
            actualRole.setName(newRole.getName());
            actualRole.setDescription(newRole.getDescription());
            this.theRoleRepository.save(actualRole);
            return actualRole;
        }else{
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Role theUser=this.theRoleRepository.findById(id).orElse(null);
        if (theUser!=null){
            this.theRoleRepository.delete(theUser);
        }
    }

}

