package by.it_academy.jd2.task_messenger_load_save.controller.web.servlets;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.view.SignInService;
import by.it_academy.jd2.task_messenger_load_save.view.api.ISignInService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletSignIn", urlPatterns = "/signIn")
public class ServletSignIn extends HttpServlet {
    private final ISignInService signInService;

    public ServletSignIn() {
        this.signInService = SignInService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //получаем логин и пароль из параметров введённых на сайте
        String login = req.getParameter("login");
        String passwordSite = req.getParameter("password");

        //проводим верификацию юзера
        User user = signInService.userVerification(login);
        if (user == null) {
            req.setAttribute("infoLogin", "Неверное имя");
            req.getRequestDispatcher("views/signIn.jsp").forward(req, resp);
        } else {
            String passwordServer = user.getPassword();
            if (passwordServer.equals(passwordSite)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("login", login);
                req.setAttribute("user", user);
                req.getRequestDispatcher("views/profile.jsp").forward(req, resp);
            } else {
                req.setAttribute("infoPassword", "Неверный пароль");
                req.getRequestDispatcher("views/signIn.jsp").forward(req, resp);
            }
        }
    }
}




