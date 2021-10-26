package by.it_academy.jd2.task_database.view.api;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.EmployeeDTO;

import java.util.Collection;

public interface IEmployeeServiceHibernate {
    long addEmployee(Employee employee);

    Employee getEmployee(long id);

    Collection<Employee> getALLEmployersLimit(long limit, long page);

    long getCountAllEntries();

    long getCountAllEntriesLastVersion(EmployeeDTO employeeDTO);

    Collection<Employee> getAllEmployersLastVersion(EmployeeDTO employeeDTO, long limit, long offset);

//    Collection<Employee> getEmployeesForSearch(String name, long salary1, long salary2, long limit, long page);
//    Collection<Employee> getEmployersByDepartmentLimit(long idDepartment, long limit, long page);
//    Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long page);
//    long getCountAllEntriesForSearch(EmployeeDTO employeeDTO);
//    long getCountAllEntriesByPosition(long id);
//    long getCountAllEntriesByDepartment(long id);

}
