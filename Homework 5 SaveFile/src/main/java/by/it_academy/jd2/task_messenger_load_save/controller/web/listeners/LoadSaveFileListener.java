package by.it_academy.jd2.task_messenger_load_save.controller.web.listeners;

import by.it_academy.jd2.task_messenger_load_save.storage.AboutStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.ChatsStorageFactory;
import by.it_academy.jd2.task_messenger_load_save.storage.StorageType;
import by.it_academy.jd2.task_messenger_load_save.storage.UserStorageFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class LoadSaveFileListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String storage = sce.getServletContext().getInitParameter("storage");
        String path = sce.getServletContext().getInitParameter("path");
        StorageType storageType = StorageType.valueOf(storage);

        ChatsStorageFactory.setType(storageType);
        UserStorageFactory.setType(storageType);

        AboutStorage aboutStorage = AboutStorage.getInstance();
        Date date = new Date();
        aboutStorage.setAbout(date);
        aboutStorage.setStorageOption(storage);
        aboutStorage.setPathToFile(path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
