package by.it_academy.jd2.task_messenger_load_save.controller.web.servlets;


import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.view.UserService;
import by.it_academy.jd2.task_messenger_load_save.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet (name = "ServletUsers", urlPatterns = "/users")
public class ServletUsers extends HttpServlet {

    private final IUserService userService;

    public ServletUsers() {
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Collection<User> allUsers = this.userService.getAllUsers();
        for (User user : allUsers) {
            writer.write((user.toString()));
        }
    }
}
