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
public class Session {
    @Id
    private String _id;
    private String token;
    private String expiration;

    @DBRef //Este decorador es para decirle a mongo que esto es una relacion con direccionalidad, si no la quisiese no relacional, lo quitaria
    private User user;

    public Session(String token, String expiration) {
        this.token = token;
        this.expiration = expiration;
    }

}
