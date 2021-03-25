package com.example.demo.models.entities;

import com.example.demo.models.entities.enums.RoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private RoleEnum role;


    public RoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(RoleEnum role) {
        this.role = role;
        return this;
    }


}
