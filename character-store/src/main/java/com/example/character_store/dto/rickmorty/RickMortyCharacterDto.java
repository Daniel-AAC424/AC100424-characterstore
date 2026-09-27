package com.example.character_store.dto.rickmorty;

public class RickMortyCharacterDto {
	
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;

    private RickMortyNamedResourceDto origin;
    private RickMortyNamedResourceDto location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RickMortyNamedResourceDto getOrigin() {
        return origin;
    }

    public void setOrigin(RickMortyNamedResourceDto origin) {
        this.origin = origin;
    }

    public RickMortyNamedResourceDto getLocation() {
        return location;
    }

    public void setLocation(RickMortyNamedResourceDto location) {
        this.location = location;
    }


}
