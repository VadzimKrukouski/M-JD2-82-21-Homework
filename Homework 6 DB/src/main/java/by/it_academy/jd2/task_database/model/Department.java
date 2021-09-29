package by.it_academy.jd2.task_database.model;

import javax.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    private Department parentDepartment;

    public Department() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

}
