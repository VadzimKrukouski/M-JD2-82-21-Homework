package by.it_academy.jd2.task_messenger_load_save.view.api;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public interface IMessageService {
    void addMessage(String recipient, Message textMessage) throws ServletException, IOException;
    List<Message> get(User currentUser);
}
