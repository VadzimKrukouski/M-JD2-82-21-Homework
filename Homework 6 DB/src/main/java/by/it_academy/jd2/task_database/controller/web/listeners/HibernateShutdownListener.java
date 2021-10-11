package by.it_academy.jd2.task_database.controller.web.listeners;

import by.it_academy.jd2.task_database.view.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateShutdownListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.shutdown();
    }
}
