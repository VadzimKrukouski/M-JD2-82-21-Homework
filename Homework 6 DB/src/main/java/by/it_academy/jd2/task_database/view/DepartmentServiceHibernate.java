package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.storage.DepartmentStorageHibernate;
import by.it_academy.jd2.task_database.storage.api.IDepartmentStorageHibernate;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;

import java.util.Collection;

public class DepartmentServiceHibernate implements IDepartmentServiceHibernate {
    private final IDepartmentStorageHibernate departmentStorageHibernate;

    public DepartmentServiceHibernate(IDepartmentStorageHibernate departmentStorageHibernate) {
        this.departmentStorageHibernate = departmentStorageHibernate;
    }

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
