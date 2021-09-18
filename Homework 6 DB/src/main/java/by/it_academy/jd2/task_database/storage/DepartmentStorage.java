package by.it_academy.jd2.task_database.storage;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.storage.api.IDepartmentStorage;
import by.it_academy.jd2.task_database.view.DataBaseConnectionCPDS;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DepartmentStorage implements IDepartmentStorage {
    private static final DepartmentStorage instance = new DepartmentStorage();
//    private final ComboPooledDataSource cpds;

    public DepartmentStorage() {
//        this.cpds = DataBaseConnectionCPDS.getInstance().getConnection();
    }

    public static DepartmentStorage getInstance() {
        return instance;
    }

    @Override
    public long addDepartment(Department department) {
        if (department.getParentDepartment() == null) {
            try (Connection con = DataBaseConnectionCPDS.getConnection();
                    PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO application.departments(\n" +
                            "\tname)\n" +
                            "\tVALUES (?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, department.getName());

                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
                ) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            } catch (SQLException e) {
                throw new IllegalStateException("Ошибка работы с базой данных", e);
            }
        } else {
            try (Connection con = DataBaseConnectionCPDS.getConnection();
                    PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO application.departments(\n" +
                            "\tname, parent_department)\n" +
                            "\tVALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)
            ) {
                preparedStatement.setString(1, department.getName());
                preparedStatement.setLong(2, department.getParentDepartment().getId());

                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
                ) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Ошибка работы с базой данных", e);
            }
        }
        return -1;
    }

    @Override
    public Department getDepartment(long id) {
        try (Connection con = DataBaseConnectionCPDS.getConnection();
                Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM application.departments WHERE id=" + id)) {
                if (resultSet.next()) {
                    Department department = new Department();

                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    long parentDepartmentId = resultSet.getLong(3);
                    Department parentDepartment = getDepartment(parentDepartmentId);

                    department.setId(currentId);
                    department.setName(name);
                    department.setParentDepartment(parentDepartment);

                    return department;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return null;
    }

    @Override
    public Collection<Department> getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();
        try (Connection con = DataBaseConnectionCPDS.getConnection();
                Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM application.departments")) {
                while (resultSet.next()) {
                    Department department = new Department();

                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    long parentDepartmentId = resultSet.getLong(3);
                    Department parentDepartment = getDepartment(parentDepartmentId);

                    department.setId(currentId);
                    department.setName(name);
                    department.setParentDepartment(parentDepartment);

                    departmentList.add(department);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return departmentList;
    }
}
