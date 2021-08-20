package by.it_academy.jd2.task_messenger.controller.web.servlets;

import by.it_academy.jd2.task_messenger.view.MessageHandle;
import by.it_academy.jd2.task_messenger.view.api.IMessageHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletMessage", urlPatterns = "/message")
public class ServletMessage extends HttpServlet {
    private final IMessageHandle messageHandle;

    public ServletMessage() {
        this.messageHandle = MessageHandle.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //получаем данные от кого, кому и что отправляем
        String from = (String) req.getSession().getAttribute("login");
        String recipient = req.getParameter("recipient");
        String text = req.getParameter("text");

        messageHandle.sendMessage(req,resp,from,recipient,text);
    }
}
