package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "Weight_measurements")
public class WeightMeasurements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Profile profile;

    @Column
    private double value;

    public WeightMeasurements() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
