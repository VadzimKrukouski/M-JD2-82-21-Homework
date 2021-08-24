package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.ChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.view.api.IMessageService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class MessageService implements IMessageService {
    private static final MessageService instance = new MessageService();

    public static MessageService getInstance() {
        return instance;
    }

    private final IChatsStorage chatsStorage;

    public MessageService() {
        this.chatsStorage = ChatsStorage.getInstance();
    }

    @Override
    public void addMessage(String recipient, Message message) {
        this.chatsStorage.addMessage(recipient,message);
    }

    @Override
    public List<Message> get(User currentUser) {
        return this.chatsStorage.get(currentUser.getLogin());
    }


}
