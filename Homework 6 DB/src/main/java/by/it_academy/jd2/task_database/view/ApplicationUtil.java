package by.it_academy.jd2.task_database.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationUtil {

    private static final ClassPathXmlApplicationContext context;

    static {
        context= new ClassPathXmlApplicationContext("service.xml");
    }

    public static ApplicationContext getContext (){
        return  context;
    }

    public static void close(){
        context.close();
    }
}
