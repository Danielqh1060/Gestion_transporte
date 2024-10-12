package com.dqh14.seguridad.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
public class RolePermission {
    private String _id;
    @DBRef
    private Role role;
    @DBRef
    private Permission permission;
    public RolePermission() {
    }
}
