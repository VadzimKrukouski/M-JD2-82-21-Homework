package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.storage.DepartmentStorage;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;

import java.util.Collection;

public class DepartmentService implements IDepartmentService {
    private static final DepartmentService instance = new DepartmentService();

    public static DepartmentService getInstance() {
        return instance;
    }

    @Override
    public long addDepartment(Department department) {
        return DepartmentStorage.getInstance().addDepartment(department);
    }

    @Override
    public Department getDepartment(long id) {
        return DepartmentStorage.getInstance().getDepartment(id);
    }

    @Override
    public Collection<Department> getAllDepartments() {
        return DepartmentStorage.getInstance().getAllDepartments();
    }
}
