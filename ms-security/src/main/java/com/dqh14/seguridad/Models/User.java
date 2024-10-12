package com.dqh14.seguridad.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Setter
@Getter
@Data
@Document


public class User  {
    @Id
    private String _id;
    private String name;
    private String email;
    private String password;


    @DBRef
    private Profile profile;


    public User(String password, String email, String name) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //Constructor por defecto

    public User() {
    }

}
