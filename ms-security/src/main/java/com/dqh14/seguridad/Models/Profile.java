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
public class Profile {
    @Id
    private String _id;
    private String photo;
    private String phone;

    @DBRef
    private User user;

    public Profile(String photo, String phone) {
        this.photo = photo;
        this.phone = phone;
    }
}
