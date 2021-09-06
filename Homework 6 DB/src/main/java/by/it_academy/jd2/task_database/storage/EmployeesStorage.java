package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorage;
import by.it_academy.jd2.task_database.view.DataBaseConnection;

import java.sql.*;

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
                "\tname, salary)\n" +
                "\tVALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDouble(2, employee.getSalary());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            ) {
                while (generatedKeys.next()) {
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
        try (Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * " +
                            "FROM application.employers " +
                            "WHERE id=" + id);) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);

                    employee.setName(name);
                    employee.setSalary(salary);
                    return employee;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return null;
    }
}




