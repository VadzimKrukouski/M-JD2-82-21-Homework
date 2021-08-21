package by.it_academy.jd2.task_messenger.view.api;

import by.it_academy.jd2.task_messenger.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ISignUpHandle {
    void registrationUser(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

}
