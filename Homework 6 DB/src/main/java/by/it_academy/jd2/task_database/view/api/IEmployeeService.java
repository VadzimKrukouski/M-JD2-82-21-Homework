package by.it_academy.jd2.task_database.view.api;

import by.it_academy.jd2.task_database.model.Employee;

public interface IEmployeeService {
    long addEmployee(Employee employee);
    Employee getEmployee(long id);
}