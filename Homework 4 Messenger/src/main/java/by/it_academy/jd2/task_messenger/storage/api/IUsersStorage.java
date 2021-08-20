package by.it_academy.jd2.task_messenger.storage.api;

import by.it_academy.jd2.task_messenger.model.User;

public interface IUsersStorage {
    boolean addUser(User user);
    User getUser(String login);
}
