package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.storage.DepartmentStorageHibernate;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;

import java.util.Collection;

public class DepartmentServiceHibernate implements IDepartmentServiceHibernate {
//    private static final DepartmentServiceHibernate instance = new DepartmentServiceHibernate();
    private final DepartmentStorageHibernate departmentStorageHibernate;

    private DepartmentServiceHibernate(DepartmentStorageHibernate departmentStorageHibernate) {
        this.departmentStorageHibernate = departmentStorageHibernate;
    }


//    public static DepartmentServiceHibernate getInstance() {
//        return instance;
//    }

    @Override
    public long addDepartment(Department department) {
        return departmentStorageHibernate.addDepartment(department);
    }

    @Override
    public Department getDepartment(long id) {
        return departmentStorageHibernate.getDepartment(id);
    }

    @Override
    public Collection<Department> getAllDepartments() {
        return departmentStorageHibernate.getAllDepartments();
    }

    @Override
    public long getCountAllEntries() {
        return departmentStorageHibernate.getCountAllEntries();
    }

    @Override
    public Collection<Department> getAllDepartmentsLimit(long limit, long page) {
        long offset = limit*(page-1);
        return departmentStorageHibernate.getAllDepartmentsLimit(limit,offset);
    }
}
