package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.MemoryChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.view.api.IMessageService;

import java.util.List;

public class MessageServiceOld implements IMessageService {
    private static final MessageServiceOld instance = new MessageServiceOld();

    public static MessageServiceOld getInstance() {
        return instance;
    }

    private final IChatsStorage chatsStorage;

    public MessageServiceOld() {
        this.chatsStorage = MemoryChatsStorage.getInstance();
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
