package by.it_academy.jd2.task_database.view;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnectionCPDS {
    private static final ComboPooledDataSource cpds;

    static {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Ошибка загрузки драйвера", e);
        }
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/crm");
        cpds.setUser("postgres");
        cpds.setPassword("123456789");
    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }

}
