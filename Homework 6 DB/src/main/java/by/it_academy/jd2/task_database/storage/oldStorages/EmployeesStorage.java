package by.it_academy.jd2.task_database.storage.oldStorages;

import by.it_academy.jd2.task_database.model.Department;
import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.model.Position;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorage;
import by.it_academy.jd2.task_database.view.oldDBS.DataBaseConnectionCPDS;
import by.it_academy.jd2.task_database.view.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeesStorage implements IEmployeeStorage {
    private static final EmployeesStorage instance = new EmployeesStorage();

    private EmployeesStorage() {
    }

    public static EmployeesStorage getInstance() {
        return instance;
    }

    @Override
    public long addEmployee(Employee employee) {
        try (Connection con = DataBaseConnectionCPDS.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO application.employers(\n" +
                             "\tname, salary, position, department)\n" +
                             "\tVALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDouble(2, employee.getSalary());
            preparedStatement.setLong(3, employee.getPosition().getId());
            preparedStatement.setLong(4, employee.getDepartment().getId());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
            ) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
            return -1;
        } catch (SQLException e) {
            throw new IllegalStateException("???????????? ???????????? ?? ?????????? ????????????", e);
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
        try (Connection con = DataBaseConnectionCPDS.getConnection();
             Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql + id)) {
                if (resultSet.next()) {
                    Employee employee = new Employee();

                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);

                    Position position = new Position();
                    String namePosition = resultSet.getString(4);
                    position.setName(namePosition);

                    Department department = new Department();
                    String nameDepartment = resultSet.getString(5);
                    department.setName(nameDepartment);

                    employee.setId(currentId);
                    employee.setName(name);
                    employee.setSalary(salary);
                    employee.setPosition(position);
                    employee.setDepartment(department);
                    return employee;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("???????????? ???????????? ?? ?????????? ????????????", e);
        }
        return null;
    }

    @Override
    public Collection<Employee> getALLEmployersLimit(long limit, long offset) {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT employers.id, employers.name, employers.salary, positions.name, departments.name\n" +
                "FROM application.employers\n" +
                "JOIN application.positions\n" +
                "ON employers.position=positions.id\n" +
                "JOIN application.departments\n" +
                "ON employers.department=departments.id\n" +
                "ORDER BY id ASC \n" +
                "LIMIT ? OFFSET ?";
        try (Connection connection = DataBaseConnectionCPDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, limit);
            preparedStatement.setLong(2, offset);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();

                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);

                    Position position = new Position();
                    String namePosition = resultSet.getString(4);
                    position.setName(namePosition);

                    Department department = new Department();
                    String nameDepartment = resultSet.getString(5);
                    department.setName(nameDepartment);

                    employee.setId(currentId);
                    employee.setName(name);
                    employee.setSalary(salary);
                    employee.setPosition(position);
                    employee.setDepartment(department);

                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("???????????? ???????????? ?? ?????????? ????????????", e);
        }
        return employeeList;
    }

    @Override
    public long getCountAllEntries() {
        long count = 0;
        String sql = "SELECT count(id) \n" +
                "FROM application.employers";
        try (Connection connection = DataBaseConnectionCPDS.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    count = resultSet.getLong(1);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("???????????? ???????????? ?? ?????????? ????????????", e);
        }
        return count;
    }

    @Override
    public long getCountAllEntriesByPosition(long id) {
        long count = 0;
        String sql = "SELECT count(id)\n" +
                "FROM application.employers\n" +
                "WHERE \"position\"=";
        try (Connection connection = DataBaseConnectionCPDS.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql + id)) {
                while (resultSet.next()) {
                    count = resultSet.getLong(1);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("???????????? ???????????? ?? ?????????? ????????????", e);
        }
        return count;
    }

    @Override
    public long getCountAllEntriesByDepartment(long id) {
        long count = 0;
        String sql = "SELECT count(id)\n" +
                "FROM application.employers\n" +
                "WHERE \"department\"=";
        try (Connection connection = DataBaseConnectionCPDS.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql + id)) {
                while (resultSet.next()) {
                    count = resultSet.getLong(1);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("???????????? ???????????? ?? ?????????? ????????????", e);
        }
        return count;
    }

    @Override
    public Collection<Employee> getEmployersByDepartmentLimit(long idDepartment, long limit, long offset) {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT employers.id,employers.name,employers.salary,positions.name,departments.name\n" +
                "FROM application.employers\n" +
                "JOIN application.positions\n" +
                "ON employers.position=positions.id\n" +
                "JOIN application.departments\n" +
                "ON employers.department=departments.id\n" +
                "WHERE \"department\"=" + idDepartment + "\n" +
                "ORDER BY id ASC\n" +
                "LIMIT ? OFFSET ?";
        try (Connection con = DataBaseConnectionCPDS.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setLong(1, limit);
            preparedStatement.setLong(2, offset);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();

                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);

                    Position position = new Position();
                    String namePosition = resultSet.getString(4);
                    position.setName(namePosition);

                    Department department = new Department();
                    String nameDepartment = resultSet.getString(5);
                    department.setName(nameDepartment);

                    employee.setId(currentId);
                    employee.setName(name);
                    employee.setSalary(salary);
                    employee.setPosition(position);
                    employee.setDepartment(department);

                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("???????????? ???????????? ?? ?????????? ????????????", e);
        }
        return employeeList;
    }

    @Override
    public Collection<Employee> getEmployersByPositionLimit(long idPosition, long limit, long offset) {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT employers.id,employers.name,employers.salary,positions.name,departments.name\n" +
                "FROM application.employers\n" +
                "JOIN application.positions\n" +
                "ON employers.position=positions.id\n" +
                "JOIN application.departments\n" +
                "ON employers.department=departments.id\n" +
                "WHERE \"position\"=" + idPosition + "\n" +
                "ORDER BY id ASC\n" +
                "LIMIT ? OFFSET ?";
        try (Connection con = DataBaseConnectionCPDS.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setLong(1, limit);
            preparedStatement.setLong(2, offset);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();

                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);

                    Position position = new Position();
                    String namePosition = resultSet.getString(4);
                    position.setName(namePosition);

                    Department department = new Department();
                    String nameDepartment = resultSet.getString(5);
                    department.setName(nameDepartment);

                    employee.setId(currentId);
                    employee.setName(name);
                    employee.setSalary(salary);
                    employee.setPosition(position);
                    employee.setDepartment(department);

                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("???????????? ???????????? ?? ?????????? ????????????", e);
        }
        return employeeList;
    }

    public Collection<Employee> getEmployeesForSearch(String name, long salary1, long salary2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(itemRoot.get("name"), name),
                        criteriaBuilder.between(itemRoot.get("salary"), salary1,salary2)
                )
        );
        List<Employee> resultList = session.createQuery(criteriaQuery).getResultList();
        return resultList;
    }
}




