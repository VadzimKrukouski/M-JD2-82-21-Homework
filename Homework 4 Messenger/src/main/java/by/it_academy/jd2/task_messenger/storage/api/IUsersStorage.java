package by.it_academy.jd2.task_messenger.storage.api;

import by.it_academy.jd2.task_messenger.model.User;

import java.util.Map;

public interface IUsersStorage {
    void addUser(User user);
    User getUser(String login);
    Map<String, User> getUSERSMap();
}
