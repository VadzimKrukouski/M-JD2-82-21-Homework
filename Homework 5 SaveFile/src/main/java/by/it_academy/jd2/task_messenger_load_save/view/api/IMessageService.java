package by.it_academy.jd2.task_messenger_load_save.view.api;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;

import java.util.List;

public interface IMessageService {
    void addMessage(String recipient, Message textMessage);
    List<Message> get(User currentUser);
}
