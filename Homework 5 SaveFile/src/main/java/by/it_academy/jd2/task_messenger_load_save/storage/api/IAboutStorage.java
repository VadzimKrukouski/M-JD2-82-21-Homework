package by.it_academy.jd2.task_messenger_load_save.storage.api;

import java.util.Date;

public interface IAboutStorage {
    void setAbout(Date date);
    Date getAbout();
    void setStorageOption(String string);
    String getSrorageOption();
}
