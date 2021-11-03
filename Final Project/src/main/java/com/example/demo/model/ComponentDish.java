package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "component_dish")
public class ComponentDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Dish dish;

    @OneToOne
    private Product product;

    @Column
    private double weightProduct;

    public ComponentDish() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
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
}
