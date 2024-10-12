package com.dqh14.seguridad.Controllers;

import com.dqh14.seguridad.Models.Permission;
import com.dqh14.seguridad.Repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permissions")

public class PermissionController {
    @Autowired
    private PermissionRepository thePermissionRepository; //Aqui se esta usando una inyeccion de dependencias en la interfaz. En este caso esta adoptando los metdodos de la libreria que nos vana a ayudar a conectarnos con la bd de mongo

    @GetMapping("")
    public List<Permission> find(){
        return this.thePermissionRepository.findAll(); //Este metodo es para que a la hora de que se use un "GET" general de los usuarios, simplemente nos muestre a todos
    }
    @GetMapping("{id}")
    public Permission findById(@PathVariable String id){ //Aqui es para devolver un unico usuario. El @PathVariable es la ruta, en este caso de donde va a conseguir el identificador
        Permission thePermission=this.thePermissionRepository.findById(id).orElse(null);
        return thePermission;
    }
    @PostMapping
    public Permission create(@RequestBody Permission newPermission){
        return this.thePermissionRepository.save(newPermission);
    }
    @PutMapping("{id}")
    public Permission update(@PathVariable String id, @RequestBody Permission newPermission){
        Permission actualPermission=this.thePermissionRepository.findById(id).orElse(null);
        if(actualPermission!=null){
            actualPermission.setUrl(newPermission.getUrl());
            actualPermission.setMethod(newPermission.getMethod());
            this.thePermissionRepository.save(actualPermission);
            return actualPermission;
        }else{
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Permission thePermission=this.thePermissionRepository.findById(id).orElse(null);
        if (thePermission!=null){
            this.thePermissionRepository.delete(thePermission);
        }
    }

}

