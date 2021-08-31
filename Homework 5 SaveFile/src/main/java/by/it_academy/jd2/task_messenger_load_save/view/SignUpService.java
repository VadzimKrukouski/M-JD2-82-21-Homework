package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.UserStorageFactory;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IUsersStorage;
import by.it_academy.jd2.task_messenger_load_save.view.api.ISignUpService;

public class SignUpService implements ISignUpService {
    private static final SignUpService instance = new SignUpService();

    public static SignUpService getInstance() {
        return instance;
    }

    @Override
    public boolean registrationUser(User user) {
        IUsersStorage instance = UserStorageFactory.getInstance();
        return instance.addUser(user);
    }
}
