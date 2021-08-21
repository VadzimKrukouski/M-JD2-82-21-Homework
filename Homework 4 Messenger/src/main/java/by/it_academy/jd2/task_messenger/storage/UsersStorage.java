package by.it_academy.jd2.task_messenger.storage;

import by.it_academy.jd2.task_messenger.model.User;
import by.it_academy.jd2.task_messenger.storage.api.IUsersStorage;

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

    public Map<String, User> getUSERSMap() {
        return USERS;
    }

    public void addUser(User user) {
        String login = user.getLogin();
            USERS.put(login, user);
    }

    public User getUser(String login){
        return USERS.get(login);
    }
}
