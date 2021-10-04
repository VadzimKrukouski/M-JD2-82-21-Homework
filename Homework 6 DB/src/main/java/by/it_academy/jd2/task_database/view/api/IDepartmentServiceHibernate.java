package by.it_academy.jd2.task_database.view.api;

import by.it_academy.jd2.task_database.model.Department;

import java.util.Collection;

public interface IDepartmentServiceHibernate {
    long addDepartment(Department department);
    Collection<Department> getAllDepartments();


}
