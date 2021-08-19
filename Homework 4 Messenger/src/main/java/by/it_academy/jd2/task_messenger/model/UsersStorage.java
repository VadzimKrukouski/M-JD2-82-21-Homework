package by.it_academy.jd2.task_messenger.model;

import java.util.HashMap;
import java.util.Map;

public class UsersStorage {
    private static final Map<String, User> USERS = new HashMap<>();


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
}
