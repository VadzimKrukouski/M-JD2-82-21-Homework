package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Department;

import java.util.Collection;

public interface IDepartmentStorageHibernate {
    long addDepartment (Department department);
    Collection<Department> getAllDepartments();


}
