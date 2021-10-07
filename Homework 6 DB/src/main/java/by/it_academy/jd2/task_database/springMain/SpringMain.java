package by.it_academy.jd2.task_database.springMain;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.EmployeeServiceHibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("service.xml", "storage.xml");

        EmployeeServiceHibernate bean = context.getBean(EmployeeServiceHibernate.class);
        Employee employee = bean.getEmployee(1);
        System.out.println(employee.toString());


    }
}
