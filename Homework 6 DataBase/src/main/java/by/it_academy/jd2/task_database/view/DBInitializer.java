package jd2.task_database.view;

import java.sql.*;

public class DBInitializer {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("ошибка загрузки файла", e);
        }
    }

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/ClassWork",
                "postgres",
                "123456789");
             Statement statement = con.createStatement();
        ) {
            String name = "Sasha";
            Double salary = 5000.5;
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employers(\n" +
                    "\tname, salary)\n" +
                    "\tVALUES (?, ?);")
                 ; ){
                preparedStatement.setString(1,name);
                preparedStatement.setDouble(2,salary);

                preparedStatement.executeUpdate();

            }
            try (ResultSet resultSet = statement.executeQuery("SELECT id,name,salary FROM application.employers ORDER BY id ASC ");){
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

    public static void mainOld(String[] args) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/ClassWork",
                "postgres",
                "123456789");
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id,name,salary FROM application.employers ORDER BY id ASC ");
        ) {
            System.out.printf("id\tName\tSalary\n");
            while ((resultSet.next())) {
                System.out.printf("%d\t%s\t%,.2f",
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3));
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }
}
