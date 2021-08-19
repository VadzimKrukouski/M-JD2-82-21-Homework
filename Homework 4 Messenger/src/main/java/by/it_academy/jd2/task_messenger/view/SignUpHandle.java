package by.it_academy.jd2.task_messenger.view;

import by.it_academy.jd2.task_messenger.model.User;
import by.it_academy.jd2.task_messenger.model.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignUpHandle {


    private static final SignUpHandle instance = new SignUpHandle();

    private SignUpHandle() {
    }

    public static SignUpHandle getInstance() {
        return instance;
    }

    public void registrationUser(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        UsersStorage usersStorage = new UsersStorage();
        HttpSession session = req.getSession();

        if (usersStorage.addUser(user)) {
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

