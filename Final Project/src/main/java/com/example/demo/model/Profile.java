package com.example.demo.model;

import com.example.demo.model.api.EGender;
import com.example.demo.model.api.ELifestyle;
import com.example.demo.model.api.ETarget;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @Column
    private double height;

    @Column
    private double weightTarget;

    @Column
    private double weightFromWeightMeasurement;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirthday;

    @Column
    private EGender gender;

    @Column
    private ELifestyle lifestyle;

    @Column
    private ETarget target;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreate;

    @Column
    @Version
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateUpdate;

    public Profile() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setWeightTarget(double weight) {
        this.weightTarget = weight;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateBirthday) {
        this.dateOfBirthday = dateBirthday;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public double getWeightFromWeightMeasurement() {
        return weightFromWeightMeasurement;
    }

    public void setWeightFromWeightMeasurement(double weightFromWeightMeasurement) {
        this.weightFromWeightMeasurement = weightFromWeightMeasurement;
    }
}
