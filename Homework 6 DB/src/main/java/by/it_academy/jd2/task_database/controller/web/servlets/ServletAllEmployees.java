package by.it_academy.jd2.task_database.controller.web.servlets;

import by.it_academy.jd2.task_database.model.Employee;
import by.it_academy.jd2.task_database.view.EmployeeService;
import by.it_academy.jd2.task_database.view.api.IEmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet (name = "ServletAllEmployees", urlPatterns = "/allEmployeeLimit")
public class ServletAllEmployees extends HttpServlet {
    private final IEmployeeService employeeService;

    public ServletAllEmployees() {
        this.employeeService= EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long limit = 10;
        long countAllEntries = employeeService.getCountAllEntries();
        long pageCount= (long) Math.ceil((double) countAllEntries/limit);
        String page = req.getParameter("page");
        Collection<Employee> allEmployers = employeeService.getALLEmployersLimit(limit,Long.parseLong(page));

        req.setAttribute("pageCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("allEmployers", allEmployers);
        req.getRequestDispatcher("views/allEmployeeLimit.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}