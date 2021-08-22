package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.Message;
import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.ChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IChatsStorage;
import by.it_academy.jd2.task_messenger_load_save.view.api.IMessageService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class MessageService implements IMessageService {
    private static final MessageService instance = new MessageService();

    public static MessageService getInstance() {
        return instance;
    }

    private final IChatsStorage chatsStorage;

    public MessageService() {
        this.chatsStorage = ChatsStorage.getInstance();
    }

    @Override
    public void addMessage(String recipient, Message textMessage) throws ServletException, IOException {
        this.chatsStorage.addMessage(recipient,textMessage);
    }

    @Override
    public List<Message> get(User currentUser) {
        return this.chatsStorage.get(currentUser.getLogin());
    }
//
//    public void addMessage(HttpServletRequest req, HttpServletResponse resp, String from, String recipient, String text) throws ServletException, IOException {
//
//        Date date = new Date();
//
//        //в хранилище юзеров находим получателя сообщения
//        User addresses = usersStorage.getUser(recipient);
//
//        //если адрессат существует, успешно отправляем сообщение и добавляем сообщение к адрессату
//        if (addresses != null) {
//            String message = "От кого: " + from + " " + "Сообщение: " + text + " " + "Время отправления: " + date.toString();
//            addresses.addMessage(message);
//            req.setAttribute("infoOk", "Сообщение отправлено");
//            req.getRequestDispatcher("views/message.jsp").forward(req, resp);
//        }
//        if (addresses == null) {
//            req.setAttribute("infoErr", "Получатель не найден");
//            req.getRequestDispatcher("views/message.jsp").forward(req, resp);
//        }
//    }

}
