package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.storage.MemoryUsersStorage;
import by.it_academy.jd2.task_messenger_load_save.storage.api.IUsersStorage;
import by.it_academy.jd2.task_messenger_load_save.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public class UserServiceOld implements IUserService {


    private static final UserServiceOld instance = new UserServiceOld();
    private final IUsersStorage usersStorage;

    private UserServiceOld() {
        this.usersStorage= MemoryUsersStorage.getInstance();
    }

    public static UserServiceOld getInstance() {
        return instance;
    }

    public void registrationUser(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getFio().isEmpty() || user.getBirthday().isEmpty()) {
            req.setAttribute("info", "Вы не заполнили все поля!");
            req.getRequestDispatcher("views/signUp.jsp").forward(req, resp);
        } else {

            if (usersStorage.addUser(user)) {
                user.setRegistration(new Date().toString());
                session.setAttribute("user", user);
                session.setAttribute("login", user.getLogin());
                req.setAttribute("user", user);
                req.getRequestDispatcher("views/profile.jsp").forward(req, resp);
            } else {
                req.setAttribute("info", "Такой пользователь уже существует");
                req.getRequestDispatcher("views/signUp.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public User get(String login) {
        return usersStorage.getUser(login);
    }

    @Override
    public Collection<User> getAllUsers() {
        return this.usersStorage.getAll();
    }
}

