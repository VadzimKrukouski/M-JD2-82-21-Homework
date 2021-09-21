package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Employee;

import java.util.Collection;

public interface IEmployeeStorage {
    long addEmployee (Employee employee);
    Employee getEmployee (long id);
    Collection<Employee> getAllEmployers();
    Collection<Employee> getEmployersByPosition(long idPosition);
    Collection<Employee> getEmployersByDepartment(long idDepartment);
    Collection<Employee> getALLEmployersLimit(long limit, long offset);
    long getCountAllEntries();
    Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long offset);
    long getCountAllEntriesByPosition(long id);



}
