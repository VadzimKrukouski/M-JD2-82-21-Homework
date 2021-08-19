package by.it_academy.jd2.task_messenger.view;

import by.it_academy.jd2.task_messenger.model.User;
import by.it_academy.jd2.task_messenger.model.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MessageHandle {


    public void sendMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //получаем данные от кого, кому и что отправляем
        String from = (String) req.getSession().getAttribute("login");
        String recipient = req.getParameter("recipient");
        String text = req.getParameter("text");

        //в хранилище юзеров находим получателя сообщения
        UsersStorage usersStorage = new UsersStorage();
        Date date = new Date();
        User addresses = usersStorage.getUser(recipient);

        //если адрессат существует, успешно отправляем сообщение и добавляем сообщение к адрессату
        if (addresses != null) {
            String message = "От кого: " + from + " " + "Сообщение: " + text + " " + "Время отправления: " + date.toString();
            addresses.addMessage(message);
            req.setAttribute("infoOk", "Сообщение отправлено");
            req.getRequestDispatcher("views/message.jsp").forward(req, resp);
        }
        if (addresses == null) {
            req.setAttribute("infoErr", "Получатель не найден");
            req.getRequestDispatcher("views/message.jsp").forward(req, resp);
        }
    }

    public void showMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //получаем юзера из аттрибута сессии
        String login = (String) req.getSession().getAttribute("login");
        UsersStorage usersStorage = new UsersStorage();
        User user = usersStorage.getUser(login);

        //из юзера получаем все его сообщения
        List<String> messages = user.getMessages();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("views/chats.jsp").forward(req, resp);
    }
}
