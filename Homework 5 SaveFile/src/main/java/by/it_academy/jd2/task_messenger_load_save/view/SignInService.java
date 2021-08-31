package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.UserStorageFactory;
import by.it_academy.jd2.task_messenger_load_save.view.api.ISignInService;
import by.it_academy.jd2.task_messenger_load_save.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInService implements ISignInService {
    private static final SignInService instance = new SignInService();

    private SignInService() {
    }

    public static SignInService getInstance() {
        return instance;
    }


    public User userVerification(String login, String passwordSite) {

        //по полученному логину получаем юзера из хранилища юзеров
        User user = UserStorageFactory.getInstance().getUser(login);

        //если юзер существует, сравниваем пароли
        if (user != null) {
            String passwordServer = user.getPassword();
            if (passwordServer.equals(passwordSite)) {
                return user;
            } else {
                throw new IllegalArgumentException("Неправильный пароль");
            }
        } else {
            return null;
        }
    }
}
