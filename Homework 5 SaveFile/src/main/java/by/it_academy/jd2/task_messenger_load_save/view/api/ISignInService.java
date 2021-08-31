package by.it_academy.jd2.task_messenger_load_save.view.api;

import by.it_academy.jd2.task_messenger_load_save.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ISignInService {
    User userVerification(String login);
}
