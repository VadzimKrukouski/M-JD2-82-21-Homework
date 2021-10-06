package by.it_academy.jd2.task_database.view.api;

import by.it_academy.jd2.task_database.model.Employee;

import java.util.Collection;

public interface IEmployeeServiceHibernate {
    long addEmployee(Employee employee);
    Employee getEmployee(long id);
    Collection<Employee> getALLEmployersLimit(long limit, long page);
    long getCountAllEntries();
    long getCountAllEntriesByDepartment(long id);
    Collection<Employee> getEmployersByDepartmentLimit(long idDepartment, long limit, long page);
    Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long page);
    long getCountAllEntriesByPosition(long id);
    Collection<Employee> getEmployeesForSearch(String name, long salary1, long salary2, long limit, long page);
    long getCountAllEntriesForSearch(String name, long salary1, long salary2);

}
