package by.it_academy.jd2.task_messenger.view;

import by.it_academy.jd2.task_messenger.model.User;
import by.it_academy.jd2.task_messenger.storage.UsersStorage;
import by.it_academy.jd2.task_messenger.storage.api.IUsersStorage;
import by.it_academy.jd2.task_messenger.view.api.ISignUpHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class SignUpHandle implements ISignUpHandle {


    private static final SignUpHandle instance = new SignUpHandle();
    private final IUsersStorage usersStorage;

    private SignUpHandle() {
        this.usersStorage=UsersStorage.getInstance();
    }

    public static SignUpHandle getInstance() {
        return instance;
    }

    public void registrationUser(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Map<String, User> usersMap = usersStorage.getUSERSMap();

        if (!usersMap.containsKey(user.getLogin())) {
            usersStorage.addUser(user);
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

