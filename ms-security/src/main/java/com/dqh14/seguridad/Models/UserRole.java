package com.dqh14.seguridad.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Data
@Document
public class UserRole {
    @Id
    private String _id;

    public UserRole(){

    }

    @DBRef //Aqui estamos guardando la referencia, si no colocaramos esto,
           //lo dejariamos como si fuese un objeto relacionado
    private User user;

    @DBRef
    private Role role;

}
