package by.it_academy.jd2.task_messenger.view.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IMessageHandle {
    void sendMessage(HttpServletRequest req, HttpServletResponse resp, String from, String recipient, String text) throws ServletException, IOException;
    void showMessage(HttpServletRequest req, HttpServletResponse resp, String login) throws ServletException, IOException;
}
