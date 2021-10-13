package by.it_academy.jd2.task_database.config;

import by.it_academy.jd2.task_database.storage.api.IDepartmentStorageHibernate;
import by.it_academy.jd2.task_database.storage.api.IEmployeeStorageHibernate;
import by.it_academy.jd2.task_database.storage.api.IPositionStorageHibernate;
import by.it_academy.jd2.task_database.view.DepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.EmployeeServiceHibernate;
import by.it_academy.jd2.task_database.view.PositionServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IDepartmentServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IEmployeeServiceHibernate;
import by.it_academy.jd2.task_database.view.api.IPositionServiceHibernate;
import by.it_academy.jd2.task_database.view.util.DataBaseGenerationByData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IEmployeeServiceHibernate employeeServiceHibernate(IEmployeeStorageHibernate employeeStorageHibernate){
        return new EmployeeServiceHibernate(employeeStorageHibernate);
    }

    @Bean
    public IDepartmentServiceHibernate departmentServiceHibernate(IDepartmentStorageHibernate departmentStorageHibernate){
        return new DepartmentServiceHibernate(departmentStorageHibernate);
    }

    @Bean
    public IPositionServiceHibernate positionServiceHibernate (IPositionStorageHibernate positionStorageHibernate){
        return new PositionServiceHibernate(positionStorageHibernate);
    }

    @Bean
    public DataBaseGenerationByData dataBaseGenerationByData(IEmployeeServiceHibernate employeeServiceHibernate, IDepartmentServiceHibernate departmentServiceHibernate, IPositionServiceHibernate positionServiceHibernate){
        return new DataBaseGenerationByData(departmentServiceHibernate,positionServiceHibernate,employeeServiceHibernate);
    }

}
