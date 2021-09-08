package by.it_academy.jd2.task_database.view;

import java.sql.*;

public class DataBaseConnection {

    public static Connection getConnection() {
        Connection con;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/crm",
                    "postgres",
                    "123456789");

        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return con;

    }


}
