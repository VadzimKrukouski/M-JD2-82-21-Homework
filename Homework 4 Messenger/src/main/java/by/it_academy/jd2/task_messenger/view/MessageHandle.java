package by.it_academy.jd2.task_messenger.view;

import by.it_academy.jd2.task_messenger.model.User;
import by.it_academy.jd2.task_messenger.storage.UsersStorage;
import by.it_academy.jd2.task_messenger.storage.api.IUsersStorage;
import by.it_academy.jd2.task_messenger.view.api.IMessageHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MessageHandle implements IMessageHandle {
    private static final MessageHandle instance = new MessageHandle();

    public static MessageHandle getInstance() {
        return instance;
    }

    private final IUsersStorage usersStorage;

    public MessageHandle() {
        this.usersStorage = UsersStorage.getInstance();
    }


    public void sendMessage(HttpServletRequest req, HttpServletResponse resp, String from, String recipient, String text) throws ServletException, IOException {

        Date date = new Date();

        //в хранилище юзеров находим получателя сообщения
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

    public void showMessage(HttpServletRequest req, HttpServletResponse resp, String login) throws ServletException, IOException {

        User user = usersStorage.getUser(login);

        //из юзера получаем все его сообщения
        List<String> messages = user.getMessages();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("views/chats.jsp").forward(req, resp);
    }
}
