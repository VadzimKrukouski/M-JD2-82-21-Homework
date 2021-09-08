package by.it_academy.jd2.task_database.model;

public class Employee {
    private long id;
    private String name;
    private Double  salary;
    private Department department;
    private Position position;

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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

//    @Override
//    public String toString() {
//        return "Employee{" +
//                "name='" + name + '\'' +
//                ", salary=" + salary +
//                '}';
//    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + this.department.getName() +
                ", position=" + this.position.getName() +
                '}';
    }
}
