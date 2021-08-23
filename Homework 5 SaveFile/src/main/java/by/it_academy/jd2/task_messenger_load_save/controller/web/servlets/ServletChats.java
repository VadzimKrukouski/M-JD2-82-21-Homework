package by.it_academy.jd2.task_messenger_load_save.controller.web.servlets;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.ChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletChats", urlPatterns = "/chats")
public class ServletChats extends HttpServlet {
    private final IChatsStorage chatsStorage;

    public ServletChats() {
        this.chatsStorage = ChatsStorage.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //получаем юзера из аттрибута сессии
        User user = (User) req.getSession().getAttribute("user");

        List<Message> messages = this.chatsStorage.get(user.getLogin());
        for (Message message : messages) {
            
        }
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("views/chats.jsp").forward(req, resp);

    }
}
