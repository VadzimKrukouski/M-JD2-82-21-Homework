package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.UserStorageFactory;
import by.it_academy.jd2.task_messenger_load_save.view.api.ISignInService;

public class SignInService implements ISignInService {
    private static final SignInService instance = new SignInService();

    private SignInService() {
    }

    public static SignInService getInstance() {
        return instance;
    }


    public User userVerification(String login) {

        //по полученному логину получаем юзера из хранилища юзеров
        User user = UserStorageFactory.getInstance().getUser(login);

        //если юзер существует, сравниваем пароли
        if (user != null) {
           return user;
        } else {
            return null;
        }
    }
}
