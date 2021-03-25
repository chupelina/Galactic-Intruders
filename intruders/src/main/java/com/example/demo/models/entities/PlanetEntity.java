package com.example.demo.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "planets")
public class PlanetEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imgUrl;
    @Column(nullable = false)
    private String description;
    @OneToOne
    private UserEntity userEntity;

    public String getName() {
        return name;
    }

    public PlanetEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public PlanetEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlanetEntity setDescription(String description) {
        this.description = description;
        return this;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public PlanetEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
