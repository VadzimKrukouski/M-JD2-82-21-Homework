package by.it_academy.jd2.task_database.config;

import by.it_academy.jd2.task_database.storage.DepartmentStorageHibernate;
import by.it_academy.jd2.task_database.storage.EmployeesStorageHibernate;
import by.it_academy.jd2.task_database.storage.PositionStorageHibernate;
import by.it_academy.jd2.task_database.storage.api.IDepartmentStorageHibernate;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorageHibernate;
import by.it_academy.jd2.task_database.storage.api.IPositionStorageHibernate;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DaoConfig {
    @Bean
    public IEmployeeStorageHibernate employeesStorageHibernate(SessionFactory sessionFactory) {
        return new EmployeesStorageHibernate(sessionFactory);
    }

    @Bean
    public IDepartmentStorageHibernate departmentStorageHibernate(SessionFactory sessionFactory) {
        return new DepartmentStorageHibernate(sessionFactory);
    }

    @Bean
    public IPositionStorageHibernate positionStorageHibernate(SessionFactory sessionFactory) {
        return new PositionStorageHibernate(sessionFactory);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.scanPackages("by.it_academy.jd2.task_database");
        builder.setProperty("hibernate.show_sql", "true");
        builder.setProperty("hibernate.default_schema", "application");
        builder.setProperty("hibernate.hbm2ddl.auto", "update");
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        return builder.buildSessionFactory();
    }

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Ошибка загрузки драйвера", e);
        }
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/crm");
        cpds.setUser("postgres");
        cpds.setPassword("123456789");
        return cpds;
    }
}
