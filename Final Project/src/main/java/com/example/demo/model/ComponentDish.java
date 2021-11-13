package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "component_dish")
public class ComponentDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    @Column
    private double weightProduct;

    @Column
    private LocalDateTime dateCreate;

    @Column
    private LocalDateTime dateUpdate;

    public ComponentDish() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getWeightProduct() {
        return weightProduct;
    }

    public void setWeightProduct(double weight) {
        this.weightProduct = weight;
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
}
