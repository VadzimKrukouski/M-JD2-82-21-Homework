package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.EmployeesStorage;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;

import java.util.Collection;

public class EmployeeService implements IEmployeeService {
    private static final EmployeeService instance = new EmployeeService();

    public static EmployeeService getInstance() {
        return instance;
    }

    @Override
    public long addEmployee(Employee employee) {
        return EmployeesStorage.getInstance().addEmployee(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        return EmployeesStorage.getInstance().getEmployee(id);
    }

    @Override
    public Collection<Employee> getAllEmployers() {
        return EmployeesStorage.getInstance().getAllEmployers();
    }

    @Override
    public Collection<Employee> getEmployersByPosition(long idPosition) {
        return EmployeesStorage.getInstance().getEmployersByPosition(idPosition);
    }

    @Override
    public Collection<Employee> getEmployersByDepartment(long idDepartment) {
        return EmployeesStorage.getInstance().getEmployersByDepartment(idDepartment);
    }

    @Override
    public Collection<Employee> getALLEmployersLimit(long limit, long page) {
        long offset = 0;
        if (page != 1) {
            offset = limit * page;
        }
        return EmployeesStorage.getInstance().getALLEmployersLimit(limit, offset);
    }

    @Override
    public long getCountAllEntries() {
        return EmployeesStorage.getInstance().getCountAllEntries();
    }
}
