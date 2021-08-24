package by.it_academy.jd2.task_messenger_load_save.controller.web.listeners;

import by.it_academy.jd2.task_messenger_load_save.storage.AboutStorage;
import by.it_academy.jd2.task_messenger_load_save.view.SaveLoadFileService;
import by.it_academy.jd2.task_messenger_load_save.view.api.ISaveLoadFileService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class LoadSaveFileListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AboutStorage aboutStorage = AboutStorage.getInstance();
        Date date = new Date();
        aboutStorage.setAbout(date);

        String storage = sce.getServletContext().getInitParameter("storage");
        String path = sce.getServletContext().getInitParameter("path");
        if (storage.equals("file")){
            aboutStorage.setStorageOption(storage);
            aboutStorage.setPathToFile(path);
        } else {
            aboutStorage.setStorageOption("system");
        }
        saveOrLoad(storage, "load");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String storage = sce.getServletContext().getInitParameter("storage");
        saveOrLoad(storage, "save");
    }

    private void saveOrLoad(String storage, String string){
        ISaveLoadFileService saveLoadFile = SaveLoadFileService.getInstance();
        if (storage.equals("file")){
            if (string.equals("save")){
                saveLoadFile.saveToFile();
            }
            if (string.equals("load")){
                saveLoadFile.loadFromFile();
            }
        }

    }
}
