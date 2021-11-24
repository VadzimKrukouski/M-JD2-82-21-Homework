package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class AuthDTO {

    private String login;

    private String password;

    @NotEmpty (message = "Name should not be empty" )
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
