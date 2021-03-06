package by.it_academy.jd2.task_messenger_load_save.storage;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryChatsStorage implements IChatsStorage {
    private static final MemoryChatsStorage instance = new MemoryChatsStorage();

    public static MemoryChatsStorage getInstance() {
        return instance;
    }

    private final Map<String, List<Message>> chats = new HashMap<>();

    @Override
    public List<Message> get(String login) {
        return this.chats.get(login);
    }

    @Override
    public void addMessage(String login, Message message) {
        List<Message> chat;
        if (chats.containsKey(login)) {
            chat = this.chats.get(login);
        } else {
            chat = new ArrayList<>();
            this.chats.put(login,chat);
        }
        chat.add(message);
    }
}
