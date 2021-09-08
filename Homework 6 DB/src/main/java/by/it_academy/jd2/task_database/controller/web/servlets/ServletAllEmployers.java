package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet (name = "ServletAllEmployers", urlPatterns = "/allEmployers")
public class ServletAllEmployers extends HttpServlet {
    private final EmployeeService employeeService;

    public ServletAllEmployers() {
        this.employeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Employee> allEmployers = employeeService.getAllEmployers();

        req.setAttribute("allEmployers", allEmployers);
        req.getRequestDispatcher("views/allEmployers.jsp");
    }
}
