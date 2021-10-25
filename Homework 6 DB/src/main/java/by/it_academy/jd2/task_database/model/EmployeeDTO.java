package by.it_academy.jd2.task_database.model;

public class EmployeeDTO {
    private String name;
    private long salaryFrom;
    private long salaryTo;
    private int page;
    private Department department;
    private Position position;

    public EmployeeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(long salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public double getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(long salaryTo) {
        this.salaryTo = salaryTo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public static class Builder{
        private EmployeeDTO employeeDTO;

        public Builder() {
            employeeDTO=new EmployeeDTO();
        }

        public Builder name(String name){
            employeeDTO.name=name;
            return this;
        }

        public Builder salaryFrom(long salaryFrom){
            employeeDTO.salaryFrom=salaryFrom;
            return this;
        }

        public Builder salaryTo(long salaryTo){
            employeeDTO.salaryTo=salaryTo;
            return this;
        }

        public Builder page(int page){
            employeeDTO.page=page;
            return this;
        }

        public Builder position(Position position){
            employeeDTO.position=position;
            return this;
        }

        public Builder department(Department department){
            employeeDTO.department=department;
            return this;
        }

        public EmployeeDTO build(){
            return employeeDTO;
        }
    }
}
