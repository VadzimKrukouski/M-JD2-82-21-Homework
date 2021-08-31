package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;

import java.util.List;

public class FileChatsStorage implements IChatsStorage {
    private static final FileChatsStorage instance = new FileChatsStorage();

    private FileChatsStorage() {
    }

    public static FileChatsStorage getInstance() {
        return instance;
    }

    @Override
    public List<Message> get(String login) {
        return null;
    }

    @Override
    public void addMessage(String login, Message message) {

    }
}
