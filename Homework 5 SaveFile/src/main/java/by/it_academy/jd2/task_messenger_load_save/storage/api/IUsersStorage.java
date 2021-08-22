package by.it_academy.jd2.task_messenger_load_save.storage.api;

import by.it_academy.jd2.task_messenger_load_save.model.User;

import java.util.Collection;

public interface IUsersStorage {
    boolean addUser(User user);
    User getUser(String login);
    Collection<User> getAll();
}
