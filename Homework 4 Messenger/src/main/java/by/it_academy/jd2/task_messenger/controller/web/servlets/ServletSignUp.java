package by.it_academy.jd2.task_messenger.controller.web.servlets;

import by.it_academy.jd2.task_messenger.model.User;
import by.it_academy.jd2.task_messenger.view.SignUpHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletSignUp", urlPatterns = "/signUp")
public class ServletSignUp extends HttpServlet {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String FIO_PARAM = "fio";
    private static final String BIRTHDAY_PARAM = "birthday";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //создаём пользователя и сохраняем данные о нём
        User user = new User();
        user.setLogin(req.getParameter(LOGIN_PARAM));
        user.setPassword(req.getParameter(PASSWORD_PARAM));
        user.setFio(req.getParameter(FIO_PARAM));
        user.setBirthday(req.getParameter(BIRTHDAY_PARAM));

        //регистрируем пользователя в приложении
        SignUpHandle signUpHandle = SignUpHandle.getInstance();
        signUpHandle.registrationUser(req, resp, user);
    }
}

