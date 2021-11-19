package com.example.demo.dto;

import com.example.demo.model.api.EGender;
import com.example.demo.model.api.ELifestyle;
import com.example.demo.model.api.ETarget;

import java.time.LocalDate;

public class UserAuthDTO {
    private String login;
    private String password;
    private String name;
    private double height;
    private double weightTarget;
    private double weightFromWeightMeasurement;
    private LocalDate dateOfBirthday;
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

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
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
