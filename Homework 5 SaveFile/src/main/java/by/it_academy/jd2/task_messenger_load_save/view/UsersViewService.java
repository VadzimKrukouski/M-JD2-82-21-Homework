package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.UserStorageFactory;
import by.it_academy.jd2.task_messenger_load_save.view.api.IUsersViewService;

import java.util.Collection;

public class UsersViewService implements IUsersViewService {
    private static final UsersViewService instance = new UsersViewService();

    public static UsersViewService getInstance() {
        return instance;
    }

    @Override
    public Collection<User> getAllUsers() {
        return UserStorageFactory.getInstance().getAll();
    }
}
