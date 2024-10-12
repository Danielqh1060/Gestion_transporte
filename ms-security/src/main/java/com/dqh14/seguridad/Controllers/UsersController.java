package com.dqh14.seguridad.Controllers;

import com.dqh14.seguridad.Models.User;
import com.dqh14.seguridad.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    private UserRepository theUserRepository; //Aqui se esta usando una inyeccion de dependencias en la interfaz. En este caso esta adoptando los metdodos de la libreria que nos vana a ayudar a conectarnos con la bd de mongo

    @GetMapping("")
    public List<User> find(){
        return this.theUserRepository.findAll(); //Este metodo es para que a la hora de que se use un "GET" general de los usuarios, simplemente nos muestre a todos
    }
    @GetMapping("{id}")
    public User findById(@PathVariable String id){ //Aqui es para devolver un unico usuario. El @PathVariable es la ruta, en este caso de donde va a conseguir el identificador
        User theUser=this.theUserRepository.findById(id).orElse(null);
        return theUser;
    }
    @PostMapping
    public User create(@RequestBody User newUser){
        return this.theUserRepository.save(newUser);
    }
    @PutMapping("{id}")
    public User update(@PathVariable String id, @RequestBody User newUser){
        User actualUser=this.theUserRepository.findById(id).orElse(null);
        if(actualUser!=null){
            actualUser.setName(newUser.getName());
            actualUser.setEmail(newUser.getEmail());
            actualUser.setPassword(newUser.getPassword());
            this.theUserRepository.save(actualUser);
            return actualUser;
        }else{
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        User theUser=this.theUserRepository.findById(id).orElse(null);
        if (theUser!=null){
            this.theUserRepository.delete(theUser);
        }
    }

}
