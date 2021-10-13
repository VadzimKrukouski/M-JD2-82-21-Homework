package by.it_academy.jd2.task_database.view.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationUtil {

    private static final AnnotationConfigApplicationContext context;

    static {
        context= new AnnotationConfigApplicationContext("by.it_academy.jd2.task_database.config");
    }

    public static ApplicationContext getContext (){
        return  context;
    }

    public static void close(){
        context.close();
    }
}
