package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.storage.ChatsStorageFactory;
import by.it_academy.jd2.task_messenger_load_save.view.api.IChatsViewService;

import java.util.List;

public class ChatsViewService implements IChatsViewService {
    private static final ChatsViewService instance = new ChatsViewService();

    public static ChatsViewService getInstance() {
        return instance;
    }

    @Override
    public List<Message> getMessages(String login) {
        return ChatsStorageFactory.getInstance().get(login);
    }
}
