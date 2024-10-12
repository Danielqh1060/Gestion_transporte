package com.dqh14.seguridad.Controllers;

import com.dqh14.seguridad.Models.Session;
import com.dqh14.seguridad.Models.User;
import com.dqh14.seguridad.Repositories.SessionsRepository;
import com.dqh14.seguridad.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sessions")
public class SessionsController {
    @Autowired //Esto inyecta, crea la clase
    private SessionsRepository theSessionsRepository; //Aqui se esta usando una inyeccion de dependencias en la interfaz. En este caso esta adoptando los metdodos de la libreria que nos vana a ayudar a conectarnos con la bd de mongo

    @Autowired
    private UserRepository theUserRepository;

    @GetMapping("")
    public List<Session> find(){
        return this.theSessionsRepository.findAll(); //Este metodo es para que a la hora de que se use un "GET" general de los usuarios, simplemente nos muestre a todos
    }
    @GetMapping("{id}")
    public Session findById(@PathVariable String id){ //Aqui es para devolver un unico usuario. El @PathVariable es la ruta, en este caso de donde va a conseguir el identificador
        Session theSession=this.theSessionsRepository.findById(id).orElse(null);
        return theSession;
    }
    @PostMapping
    public Session create(@RequestBody Session newSession){
        return this.theSessionsRepository.save(newSession);
    }
    @PutMapping("{id}")
    public Session update(@PathVariable String id, @RequestBody Session newSession){
        Session actualSession=this.theSessionsRepository.findById(id).orElse(null);
        if(actualSession!=null){
            actualSession.set_id(newSession.get_id());
            actualSession.setToken(newSession.getToken());
            this.theSessionsRepository.save(actualSession);
            return actualSession;
        }else{
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Session theSession=this.theSessionsRepository.findById(id).orElse(null);
        if (theSession!=null){
            this.theSessionsRepository.delete(theSession);
        }
    }
    @PostMapping("{session_id}/user/{user_id}")
    public Session matchUser(@PathVariable String session_id,
                             @PathVariable String user_id){
        Session theSession = this.theSessionsRepository.findById(session_id).orElse(null);
        User theUser = this.theUserRepository.findById(user_id).orElse(null);
        if(theSession != null && theUser != null){
            theSession.setUser((theUser));
            this.theSessionsRepository.save(theSession);
            return theSession;

        } else{
            return null;
        }

    }

    @GetMapping("user/{userId}")
    public List<Session> getSessionsByUser(@PathVariable String userId){
        return this.theSessionsRepository.getSessionsByUser(userId);
    }
}
