package jd2.task_database.controller;

import jd2.task_database.model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "ServletMainPage", urlPatterns = "/")
public class ServletMainPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/signUp.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = new Model();
        model.setName(req.getParameter("login"));
        model.setSalary(req.getParameter("salary"));

        req.getRequestDispatcher("vies/signUp.jsp").forward(req,resp);
        req.setAttribute("info", "Сотрудник добавлен");
    }
}
