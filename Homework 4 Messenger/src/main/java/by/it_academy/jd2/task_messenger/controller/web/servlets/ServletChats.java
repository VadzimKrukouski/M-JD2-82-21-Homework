package by.it_academy.jd2.task_messenger.controller.web.servlets;

import by.it_academy.jd2.task_messenger.view.MessageHandle;
import by.it_academy.jd2.task_messenger.view.api.IMessageHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletChats", urlPatterns = "/chats")
public class ServletChats extends HttpServlet {
    private final IMessageHandle messageHandle;

    public ServletChats() {
        this.messageHandle = MessageHandle.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //получаем юзера из аттрибута сессии
        String login = (String) req.getSession().getAttribute("login");

        messageHandle.showMessage(req, resp, login);
    }
}
