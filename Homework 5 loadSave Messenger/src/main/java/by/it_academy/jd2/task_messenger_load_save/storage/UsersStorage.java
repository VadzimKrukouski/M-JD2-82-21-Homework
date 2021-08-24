package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IUsersStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UsersStorage implements IUsersStorage {
    private static final UsersStorage instance = new UsersStorage();

    private UsersStorage() {
    }

    public static UsersStorage getInstance() {
        return instance;
    }

    private final Map<String, User> USERS = new HashMap<>();


    public boolean addUser(User user) {
        String login = user.getLogin();
        if (USERS.containsKey(login)) {
            return false;
        } else {
            USERS.put(login, user);
            return true;
        }
    }

    public User getUser(String login){
        return USERS.get(login);
    }

    @Override
    public Collection<User> getAll() {
        return this.USERS.values();
    }
}