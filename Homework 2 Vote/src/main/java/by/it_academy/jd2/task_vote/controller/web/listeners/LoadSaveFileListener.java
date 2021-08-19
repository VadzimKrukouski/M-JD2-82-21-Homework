package by.it_academy.jd2.task_vote.controller.web.listeners;

import by.it_academy.jd2.task_vote.view.LoadSaveService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LoadSaveFileListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("destroy");

        LoadSaveService.getInstance().save();
    }
}
