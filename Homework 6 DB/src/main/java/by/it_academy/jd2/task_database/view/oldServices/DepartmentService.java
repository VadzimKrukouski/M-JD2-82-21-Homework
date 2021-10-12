package by.it_academy.jd2.task_database.view.oldServices;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.storage.oldStorages.DepartmentStorage;
import by.it_academy.jd2.task_database.storage.api.IDepartmentStorage;
import by.it_academy.jd2.task_database.view.api.IDepartmentService;

import java.util.Collection;

public class DepartmentService implements IDepartmentService {
    private static final DepartmentService instance = new DepartmentService();
    private final IDepartmentStorage departmentStorage;

    public DepartmentService() {
        this.departmentStorage = DepartmentStorage.getInstance();
    }

    public static DepartmentService getInstance() {
        return instance;
    }

    @Override
    public long addDepartment(Department department) {
        return departmentStorage.addDepartment(department);
    }

    @Override
    public Department getDepartment(long id) {
        return departmentStorage.getDepartment(id);
    }

    @Override
    public Collection<Department> getAllDepartments() {
        return departmentStorage.getAllDepartments();
    }

    @Override
    public long getCountAllEntries() {
        return departmentStorage.getCountAllEntries();
    }

    @Override
    public Collection<Department> getAllDepartmentsLimit(long limit, long page) {
        long offset = limit*(page-1);
        return departmentStorage.getAllDepartmentsLimit(limit,offset);
    }
}
