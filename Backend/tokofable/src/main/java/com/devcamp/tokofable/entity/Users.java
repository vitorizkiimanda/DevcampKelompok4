package com.devcamp.tokofable.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    private String id ;
    private String name ;
    private String address ;
    private String photoUrl ;
    private String password ;
    private String email ;
    private Double ovo ;
    private Double goldWeight ;

    public Users() {
    }

    public Users(String id) {
        this.id = id;
    }

    public Users(String id, String name, String address, String photoUrl, String password, String email, Double ovo, Double goldWeight) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.photoUrl = photoUrl;
        this.password = password;
        this.email = email;
        this.ovo = ovo;
        this.goldWeight = goldWeight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getOvo() {
        return ovo;
    }

    public void setOvo(Double ovo) {
        this.ovo = ovo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Double getGoldWeight() {
        return goldWeight;
    }

    public void setGoldWeight(Double goldWeight) {
        this.goldWeight = goldWeight;
    }
}
