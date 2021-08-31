package by.it_academy.jd2.task_messenger_load_save.view.api;

import by.it_academy.jd2.task_messenger_load_save.model.Message;

public interface IMessageSendService {
    void addMessage(String recipient, Message message);
}
