package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.EmployeesStorageHibernate;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;

import java.util.Collection;

public class EmployeeServiceHibernate implements IEmployeeServiceHibernate {
    private static final EmployeeServiceHibernate instance = new EmployeeServiceHibernate();
    private final EmployeesStorageHibernate employeesStorageHibernate;

    private EmployeeServiceHibernate() {
        this.employeesStorageHibernate = EmployeesStorageHibernate.getInstance();
    }

    public static EmployeeServiceHibernate getInstance() {
        return instance;
    }

    @Override
    public long addEmployee(Employee employee) {
        return employeesStorageHibernate.addEmployee(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        return employeesStorageHibernate.getEmployee(id);
    }

    @Override
    public Collection<Employee> getALLEmployersLimit(long limit, long page) {
        long offset = limit*(page-1);
        return employeesStorageHibernate.getALLEmployersLimit(limit,offset);
    }

    @Override
    public long getCountAllEntries() {
        return employeesStorageHibernate.getCountAllEntries();
    }

    @Override
    public long getCountAllEntriesByDepartment(long id) {
        return employeesStorageHibernate.getCountAllEntriesByDepartment(id);
    }

    @Override
    public Collection<Employee> getEmployersByDepartmentLimit(long idDepartment, long limit, long page) {
        long offset=limit*(page-1);
        return employeesStorageHibernate.getEmployersByDepartmentLimit(idDepartment,limit,offset);
    }

    @Override
    public long getCountAllEntriesByPosition(long id) {
        return employeesStorageHibernate.getCountAllEntriesByPosition(id);
    }

    @Override
    public Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long page) {
        long offset=limit*(page-1);
        return employeesStorageHibernate.getEmployersByPositionLimit(idPosition,limit,offset);
    }
}
