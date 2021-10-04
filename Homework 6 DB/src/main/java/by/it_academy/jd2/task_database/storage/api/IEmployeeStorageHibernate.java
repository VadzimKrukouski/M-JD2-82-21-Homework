package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Employee;

public interface IEmployeeStorageHibernate {
    long addEmployee (Employee employee);

}
