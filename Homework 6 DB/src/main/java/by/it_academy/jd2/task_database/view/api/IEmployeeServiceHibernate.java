package by.it_academy.jd2.task_database.view.api;

import by.it_academy.jd2.task_database.model.Employee;

import java.util.Collection;

public interface IEmployeeServiceHibernate {
    long addEmployee(Employee employee);
    Employee getEmployee(long id);
    Collection<Employee> getALLEmployersLimit(long limit, long page);
    long getCountAllEntries();


}