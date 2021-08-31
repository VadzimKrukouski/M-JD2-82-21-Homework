package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.storage.ChatsStorageFactory;
import by.it_academy.jd2.task_messenger_load_save.view.api.IMessageSendService;

public class MessageSendService implements IMessageSendService {
    private static final MessageSendService instance = new MessageSendService();

    public static MessageSendService getInstance() {
        return instance;
    }

    @Override
    public void addMessage(String recipient, Message message) {
        ChatsStorageFactory.getInstance().addMessage(recipient,message);
    }
}
