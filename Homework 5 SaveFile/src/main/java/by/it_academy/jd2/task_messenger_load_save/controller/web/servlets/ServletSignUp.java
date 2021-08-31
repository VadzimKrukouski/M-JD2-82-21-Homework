package by.it_academy.jd2.task_messenger_load_save.controller.web.servlets;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.view.SignUpService;
import by.it_academy.jd2.task_messenger_load_save.view.UserService;
import by.it_academy.jd2.task_messenger_load_save.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletSignUp", urlPatterns = "/signUp")
public class ServletSignUp extends HttpServlet {
    private final IUserService userService;

    public ServletSignUp() {
        this.userService = UserService.getInstance();
    }

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


        if (user.getLogin().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getFio().isEmpty() ||
                user.getBirthday().isEmpty()) {
            req.setAttribute("info", "Вы не заполнили все поля!");
            req.getRequestDispatcher("views/signUp.jsp").forward(req, resp);
        } else {
            //регистрируем пользователя в приложении
            boolean resultRegistration = SignUpService.getInstance().registrationUser(user);
            HttpSession session = req.getSession();
            if (resultRegistration) {
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

//        userService.registrationUser(req, resp, user);
    }
}

