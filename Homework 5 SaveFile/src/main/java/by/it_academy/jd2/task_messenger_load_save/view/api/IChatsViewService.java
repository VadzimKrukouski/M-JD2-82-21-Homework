package by.it_academy.jd2.task_messenger_load_save.view.api;

import by.it_academy.jd2.task_messenger_load_save.model.Message;

import java.util.List;

public interface IChatsViewService {
    List<Message> getMessages(String login);
}
