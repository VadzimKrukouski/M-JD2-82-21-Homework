package jd2.task_database.controller;

import jd2.task_database.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "ServletAddEmployee", urlPatterns = "/addEmployee")
public class ServletAddEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/signUp.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setName(req.getParameter("login"));
        employee.setSalary(req.getParameter("salary"));

        req.getRequestDispatcher("vies/signUp.jsp").forward(req,resp);
        req.setAttribute("info", "Сотрудник добавлен");
    }
}
