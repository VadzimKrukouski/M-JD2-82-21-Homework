package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthDTO {

    @NotEmpty(message = "Login should not be empty")
    @Email(message = "Email should be valid")
    private String login;

    @Size(min = 6)
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
