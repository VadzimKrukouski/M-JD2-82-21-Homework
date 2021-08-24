package by.it_academy.jd2.task_messenger_load_save.controller.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LoadSaveFileListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext().getInitParameter("storage");
        sce.getServletContext().getInitParameter("path");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
