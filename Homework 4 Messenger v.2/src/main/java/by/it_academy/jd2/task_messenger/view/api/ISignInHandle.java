package by.it_academy.jd2.task_messenger.view.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ISignInHandle {
    void userVerification(HttpServletRequest req, HttpServletResponse resp, String login, String passwordSite) throws ServletException, IOException;
}
