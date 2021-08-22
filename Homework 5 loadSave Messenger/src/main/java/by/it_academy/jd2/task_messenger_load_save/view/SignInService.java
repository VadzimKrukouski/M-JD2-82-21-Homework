package by.it_academy.jd2.task_messenger_load_save.view;

import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.view.api.ISignInService;
import by.it_academy.jd2.task_messenger_load_save.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInService implements ISignInService {
    private static final SignInService instance = new SignInService();
    private final IUserService userService;

    private SignInService() {
        this.userService = UserService.getInstance();
    }

    public static SignInService getInstance() {
        return instance;
    }


    public void userVerification(HttpServletRequest req, HttpServletResponse resp, String login, String passwordSite) throws ServletException, IOException {

        //по полученному логину получаем юзера из хранилища юзеров
        User user = userService.get(login);
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
