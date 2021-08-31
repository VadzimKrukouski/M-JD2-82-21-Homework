package by.it_academy.jd2.task_messenger_load_save.controller.web.servlets;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.view.ChatsViewService;
import by.it_academy.jd2.task_messenger_load_save.view.api.IChatsViewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletChats", urlPatterns = "/chats")
public class ServletChats extends HttpServlet {
    private final IChatsViewService chatsViewService;

    public ServletChats() {
        this.chatsViewService = ChatsViewService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //получаем юзера из аттрибута сессии
        User user = (User) req.getSession().getAttribute("user");

        //по логину юзера получаем список его сообщений
        List<Message> messages = this.chatsViewService.getMessages(user.getLogin());

        if (messages==null){
            req.setAttribute("infoErr", "У вас ещё нет сообщений");
        }else {
            req.setAttribute("messages", messages);
        }
        req.getRequestDispatcher("views/chats.jsp").forward(req, resp);
    }
}
