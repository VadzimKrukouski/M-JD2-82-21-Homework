package by.it_academy.jd2.task_messenger.view;

import by.it_academy.jd2.task_messenger.model.User;
import by.it_academy.jd2.task_messenger.model.UsersStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInHandle {
    private static final SignInHandle instance = new SignInHandle();

    private SignInHandle() {
    }

    public static SignInHandle getInstance() {
        return instance;
    }


    public void userVerification(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //получаем логин и пароль из параметров введённых на сайте
        String login = req.getParameter("login");
        String passwordSite = req.getParameter("password");

        //по полученному логину получаем юзера из хранилища юзеров
        UsersStorage usersStorage = new UsersStorage();
        User user = usersStorage.getUser(login);
        HttpSession session = req.getSession();

        //если юзер существует, сравниваем пароли
        if (user!=null) {
            String passwordServer = user.getPassword();
            if(passwordServer.equals(passwordSite)){
                session.setAttribute("user", user);
                session.setAttribute("login", login);
                req.setAttribute("user", user);
                req.getRequestDispatcher("views/profile.jsp").forward(req,resp);
            } else {
                req.setAttribute("infoPassword", "Неверный пароль");
                req.getRequestDispatcher("views/signIn.jsp").forward(req,resp);
            }

        } else {
            req.setAttribute("infoLogin", "Неверное имя");
            req.getRequestDispatcher("views/signIn.jsp").forward(req,resp);
        }
    }
}
