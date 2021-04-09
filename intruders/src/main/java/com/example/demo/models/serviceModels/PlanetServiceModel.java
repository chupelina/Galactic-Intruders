package com.example.demo.models.serviceModels;


import com.example.demo.models.entities.PlanetResourceEntity;

public class PlanetServiceModel {

    private long id;
    private String name;
    private String imgUrl;
    private String description;
    private PlanetResourceEntity planetResourceEntity;

    public long getId() {
        return id;
    }

    public PlanetServiceModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlanetServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public PlanetServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlanetServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public PlanetResourceEntity getPlanetResourceEntity() {
        return planetResourceEntity;
    }

    public PlanetServiceModel setPlanetResourceEntity(PlanetResourceEntity planetResourceEntity) {
        this.planetResourceEntity = planetResourceEntity;
        return this;
    }
}
