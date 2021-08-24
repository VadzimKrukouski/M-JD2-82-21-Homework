package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.storage.api.IAboutStorage;

import java.util.Date;

public class AboutStorage implements IAboutStorage {
    private static final AboutStorage instance = new AboutStorage();

    public static AboutStorage getInstance() {
        return instance;
    }

    private Date date;
    private String string;

    @Override
    public void setAbout(Date date) {
        this.date=date;
    }

    @Override
    public void setStorageOption(String string) {
        this.string=string;
    }

    @Override
    public String getSrorageOption() {
        return this.string;
    }

    @Override
    public Date getAbout() {
        return this.date;
    }
}
