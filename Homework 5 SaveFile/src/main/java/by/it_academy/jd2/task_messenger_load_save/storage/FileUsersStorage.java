package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IUsersStorage;

import java.util.Collection;

public class FileUsersStorage implements IUsersStorage {
    private static final FileUsersStorage instance = new FileUsersStorage();

    private FileUsersStorage() {
    }

    public static FileUsersStorage getInstance() {
        return instance;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User getUser(String login) {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }
}
