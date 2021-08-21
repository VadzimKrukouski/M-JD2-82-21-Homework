package by.it_academy.jd2.task_messenger.storage.api;

import by.it_academy.jd2.task_messenger.model.Message;

import java.util.List;

public interface IChatsStorage {
    List<Message> get(String login);
    void addMessage(String login, Message message);
}
