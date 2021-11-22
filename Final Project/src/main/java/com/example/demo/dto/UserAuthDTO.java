package com.example.demo.dto;

import com.example.demo.model.api.EGender;
import com.example.demo.model.api.ELifestyle;
import com.example.demo.model.api.ETarget;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserAuthDTO {

    @NotEmpty(message = "Login should not be empty")
    @Email(message = "Email should be valid")
    private String login;

    @Size(min = 6)
    private String password;

    @NotEmpty (message = "Name should not be empty" )
    private String name;

    @Min(value = 0, message = "height should be greater than 0 ")
    private double height;

    @Min(value = 0, message = "weightTarget should be greater than 0 ")
    private double weightTarget;

    @Min(value = 0, message = "weightFromWeightMeasurement should be greater than 0 ")
    private double weightFromWeightMeasurement;
    private long dateOfBirthday;
    private EGender gender;
    private ELifestyle lifestyle;
    private ETarget target;

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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeightTarget() {
        return weightTarget;
    }

    public void setWeightTarget(double weightTarget) {
        this.weightTarget = weightTarget;
    }

    public long getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(long dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public ELifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(ELifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public ETarget getTarget() {
        return target;
    }

    public void setTarget(ETarget target) {
        this.target = target;
    }

    public double getWeightFromWeightMeasurement() {
        return weightFromWeightMeasurement;
    }

    public void setWeightFromWeightMeasurement(double weightFromWeightMeasurement) {
        this.weightFromWeightMeasurement = weightFromWeightMeasurement;
    }
}
