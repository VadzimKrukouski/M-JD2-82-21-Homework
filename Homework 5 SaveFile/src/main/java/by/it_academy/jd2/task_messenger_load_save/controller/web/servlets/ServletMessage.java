package by.it_academy.jd2.task_messenger_load_save.controller.web.servlets;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.view.MessageService;
import by.it_academy.jd2.task_messenger_load_save.view.api.IMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletMessage", urlPatterns = "/message")
public class ServletMessage extends HttpServlet {
    private final IMessageService messageService;

    public ServletMessage() {
        this.messageService = MessageService.getInstance();
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

        Message message = new Message();
        message.setFrom(from);
        message.setDate(new Date().toString());
        message.setText(text);

        try {
            this.messageService.addMessage(recipient,message);
            req.setAttribute("infoOk", "Сообщение отправлено");
            req.getRequestDispatcher("views/message.jsp").forward(req, resp);

        } catch (IllegalArgumentException e) {
            req.setAttribute("infoErr", "Получатель не найден");
            req.getRequestDispatcher("views/message.jsp").forward(req, resp);
        }


    }
}
