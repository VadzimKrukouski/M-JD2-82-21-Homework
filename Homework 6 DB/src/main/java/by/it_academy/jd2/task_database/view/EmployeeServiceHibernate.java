package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.EmployeesStorageHibernate;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;

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
}
