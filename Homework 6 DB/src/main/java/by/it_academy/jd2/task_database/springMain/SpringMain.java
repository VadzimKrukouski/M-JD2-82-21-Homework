package by.it_academy.jd2.task_database.springMain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("service.xml", "storage.xml");



    }
}
