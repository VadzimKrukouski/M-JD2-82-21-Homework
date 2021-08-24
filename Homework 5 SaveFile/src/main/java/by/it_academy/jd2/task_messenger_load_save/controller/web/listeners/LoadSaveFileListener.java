package by.it_academy.jd2.task_messenger_load_save.controller.web.listeners;

import by.it_academy.jd2.task_messenger_load_save.storage.AboutStorage;
import by.it_academy.jd2.task_messenger_load_save.view.SaveLoadFileService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class LoadSaveFileListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AboutStorage aboutStorage = AboutStorage.getInstance();
        SaveLoadFileService saveLoadFile = SaveLoadFileService.getInstance();
        Date date = new Date();
        aboutStorage.setAbout(date);

        String storage = sce.getServletContext().getInitParameter("storage");
        String path = sce.getServletContext().getInitParameter("path");
        if (storage.equals("file")){
            aboutStorage.setStorageOption(storage);
            aboutStorage.setPathToFile(path);
            saveLoadFile.loadFromFile();
        } else {
            aboutStorage.setStorageOption("system");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SaveLoadFileService saveLoadFile = SaveLoadFileService.getInstance();
        String storage = sce.getServletContext().getInitParameter("storage");
        if (storage.equals("file")){
            saveLoadFile.saveToFile();
        }
    }
}
