package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorage;
import by.it_academy.jd2.task_database.view.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeesStorage implements IEmployeeStorage {
    private static final EmployeesStorage instance = new EmployeesStorage();
    private final Connection con;

    private EmployeesStorage() {
        this.con = DataBaseConnection.getConnection();
    }

    public static EmployeesStorage getInstance() {
        return instance;
    }

    @Override
    public long addEmployee(Employee employee) {

//            try (
//                 Statement statement = con.createStatement();
//            ) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO application.employers(\n" +
                        "\tname, salary, position, department)\n" +
                        "\tVALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDouble(2, employee.getSalary());
            preparedStatement.setLong(3, employee.getPosition().getId());
            preparedStatement.setLong(4, employee.getDepartment().getId());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            ) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
            return -1;
        }
//                try (ResultSet resultSet = statement.executeQuery("SELECT id,name,salary FROM application.employers ORDER BY id ASC ");){
//                    System.out.printf("id\tName\tSalary\n");
//                    while ((resultSet.next())) {
//                        System.out.printf("%d\t%s\t%,.2f\n",
//                                resultSet.getLong(1),
//                                resultSet.getString(2),
//                                resultSet.getDouble(3));
//                    }
//
//
//                }
        catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    @Override
    public Employee getEmployee(long id) {
        String sql = "SELECT employers.id, employers.name, employers.salary, positions.name, departments.name \n" +
                "FROM application.employers \n" +
                "\n" +
                "JOIN application.positions\n" +
                "ON employers.position=positions.id\n" +
                "JOIN application.departments\n" +
                "ON employers.department=departments.id\n" +
                "WHERE employers.id=";
        try (Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql + id);) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    Position position = (Position) resultSet.getObject(4);
                    Department department = (Department) resultSet.getObject(5);

//                    long positionId = resultSet.getLong(4);
//                    long departmentId = resultSet.getLong(5);

//                    Position position = PositionStorage.getInstance().getPosition(positionId);
//                    Department department = DepartmentStorage.getInstance().getDepartment(departmentId);


                    employee.setId(currentId);
                    employee.setName(name);
                    employee.setSalary(salary);
                    employee.setPosition(position);
                    employee.setDepartment(department);
                    return employee;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return null;
    }

    @Override
    public Collection<Employee> getAllEmployers() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT employers.id, employers.name, employers.salary, positions.name, departments.name\n" +
                "                    FROM application.employers\n" +
                "                    JOIN application.positions\n" +
                "                    ON employers.position=positions.id\n" +
                "                    JOIN application.departments\n" +
                "                    ON employers.department=departments.id";
        try (Statement statement = con.createStatement()){
            try (ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()){
                    Employee employee = new Employee();
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    Position position = (Position) resultSet.getObject(4);
                    Department department = (Department) resultSet.getObject(5);

                    employee.setId(currentId);
                    employee.setName(name);
                    employee.setSalary(salary);
                    employee.setPosition(position);
                    employee.setDepartment(department);

                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return employeeList;
    }
}




