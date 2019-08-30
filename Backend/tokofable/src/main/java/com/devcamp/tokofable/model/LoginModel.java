package com.devcamp.tokofable.model;

public class LoginModel {
    private String name ;
    private String id ;
    private String token ;

    public LoginModel() {
    }

    public LoginModel(String name, String id, String token) {
        this.name = name;
        this.id = id;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
