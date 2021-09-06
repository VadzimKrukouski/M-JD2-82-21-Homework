package by.it_academy.jd2.task_database.view.api;

import by.it_academy.jd2.task_database.model.Department;

public interface IDepartmentService {
    long addDepartment(Department department);
    Department getDepartment(long id);
}
