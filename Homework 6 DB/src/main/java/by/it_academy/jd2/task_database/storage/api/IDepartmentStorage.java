package by.it_academy.jd2.task_database.storage.api;

import by.it_academy.jd2.task_database.model.Department;

import java.util.Collection;

public interface IDepartmentStorage {
    long addDepartment (Department department);
    Department getDepartment(long id);
    Collection<Department> getAllDepartments();
}
