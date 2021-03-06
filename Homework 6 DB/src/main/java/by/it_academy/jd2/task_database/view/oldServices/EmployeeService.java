package by.it_academy.jd2.task_database.view.oldServices;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.oldStorages.EmployeesStorage;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorage;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;

import java.util.Collection;

public class EmployeeService implements IEmployeeService {
    private static final EmployeeService instance = new EmployeeService();
    private final IEmployeeStorage employeeStorage;

    public EmployeeService() {
        this.employeeStorage = EmployeesStorage.getInstance();
    }

    public static EmployeeService getInstance() {
        return instance;
    }

    @Override
    public long addEmployee(Employee employee) {
        return employeeStorage.addEmployee(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        return employeeStorage.getEmployee(id);
    }

    @Override
    public Collection<Employee> getALLEmployersLimit(long limit, long page) {
        long offset = limit*(page-1);
        return employeeStorage.getALLEmployersLimit(limit, offset);
    }

    @Override
    public long getCountAllEntries() {
        return employeeStorage.getCountAllEntries();
    }

    @Override
    public long getCountAllEntriesByPosition(long id) {
        return employeeStorage.getCountAllEntriesByPosition(id);
    }

    @Override
    public long getCountAllEntriesByDepartment(long id) {
        return employeeStorage.getCountAllEntriesByDepartment(id);
    }

    @Override
    public Collection<Employee> getEmployersByDepartmentLimit(long idDepartment, long limit, long page) {
        long offset=limit*(page-1);
        return employeeStorage.getEmployersByDepartmentLimit(idDepartment,limit, offset);
    }

    @Override
    public Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long page) {
        long offset = limit*(page-1);
        return employeeStorage.getEmployersByPositionLimit(idPosition,limit,offset);
    }

    @Override
    public Collection<Employee> getEmployeesForSearch(String name, long salary1, long salary2) {
        return employeeStorage.getEmployeesForSearch(name,salary1,salary2);
    }


}
