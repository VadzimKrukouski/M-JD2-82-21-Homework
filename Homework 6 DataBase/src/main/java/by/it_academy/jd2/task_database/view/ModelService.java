package jd2.task_database.view;

import java.sql.*;

public class ModelService {
    private static final ModelService instance = new ModelService();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("ошибка загрузки файла", e);
        }
    }

    public void addDataBase(String name, Double salary) {

        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/ClassWork",
                        "postgres",
                        "123456789");
                Statement statement = con.createStatement();
        ) {
            String name1 = name;
            Double salary1 = salary;
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employers(\n" +
                    "\tname, salary)\n" +
                    "\tVALUES (?, ?);")
                 ;) {
                preparedStatement.setString(1, name1);
                preparedStatement.setDouble(2, salary1);

                preparedStatement.executeUpdate();

            }
            try (ResultSet resultSet = statement.executeQuery("SELECT id,name,salary FROM application.employers ORDER BY id ASC ");) {
                System.out.printf("id\tName\tSalary\n");
                while ((resultSet.next())) {
                    System.out.printf("%d\t%s\t%,.2f\n",
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3));
                }


            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    public static ModelService getInstance() {
        return instance;
    }

}
