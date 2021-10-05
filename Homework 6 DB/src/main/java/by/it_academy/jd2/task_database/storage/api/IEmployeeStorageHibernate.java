package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Employee;

import java.util.Collection;

public interface IEmployeeStorageHibernate {
    long addEmployee (Employee employee);
    Employee getEmployee (long id);
    Collection<Employee> getALLEmployersLimit(long limit, long offset);
    long getCountAllEntries();


}
