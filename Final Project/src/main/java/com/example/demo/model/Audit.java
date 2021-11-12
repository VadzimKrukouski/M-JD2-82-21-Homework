package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @Column
    private String description;

    @Column
    private String essenceName;

    @Column
    private long essenceId;

    @Column
    private LocalDateTime dateCreate;

    public Audit() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEssenceName() {
        return essenceName;
    }

    public void setEssenceName(String entityType) {
        this.essenceName = entityType;
    }

    public long getEssenceId() {
        return essenceId;
    }

    public void setEssenceId(long entityId) {
        this.essenceId = entityId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }
}
