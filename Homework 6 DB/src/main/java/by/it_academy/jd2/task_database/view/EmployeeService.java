package by.it_academy.jd2.task_database.view;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.EmployeesStorage;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;

public class EmployeeService implements IEmployeeService {
    private static final EmployeeService instance = new EmployeeService();

    public static EmployeeService getInstance() {
        return instance;
    }

    @Override
    public long addEmployee(Employee employee) {
        return EmployeesStorage.getInstance().addEmployee(employee);
    }
}
