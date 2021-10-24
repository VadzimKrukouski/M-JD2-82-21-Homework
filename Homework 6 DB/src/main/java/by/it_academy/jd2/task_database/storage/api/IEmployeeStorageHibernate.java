package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.EmployeeDTO;

import java.util.Collection;

public interface IEmployeeStorageHibernate {
    long addEmployee (Employee employee);
    Employee getEmployee (long id);
    Collection<Employee> getALLEmployersLimit(long limit, long offset);
    long getCountAllEntries();
    long getCountAllEntriesByDepartment(long id);
    Collection<Employee> getEmployersByDepartmentLimit(long idDepartment, long limit, long offset);
    Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long offset);
    long getCountAllEntriesByPosition(long id);
    Collection<Employee> getEmployeesForSearch(String name, long salary1, long salary2, long limit, long offset);
    long getCountAllEntriesForSearch(EmployeeDTO employeeDTO);

}
