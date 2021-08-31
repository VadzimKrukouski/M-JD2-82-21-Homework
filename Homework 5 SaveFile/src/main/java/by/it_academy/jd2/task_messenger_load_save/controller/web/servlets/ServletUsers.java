package by.it_academy.jd2.task_messenger_load_save.controller.web.servlets;


import by.it_academy.jd2.task_messenger_load_save.model.User;
import by.it_academy.jd2.task_messenger_load_save.view.UsersViewService;
import by.it_academy.jd2.task_messenger_load_save.view.api.IUsersViewService;

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

    private final IUsersViewService usersViewService;

    public ServletUsers() {
        this.usersViewService = UsersViewService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Collection<User> allUsers = this.usersViewService.getAllUsers();
        for (User user : allUsers) {
            writer.write("<p>" + (user.toString()) + "<p>");
        }
    }
}
